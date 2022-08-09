package com.alexprom.tsp_ws.weighting;

import com.alexprom.tsp_ws.connectionsettings.dbConnectionSettingsPanel;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;

@ConvertAsProperties(
        dtd = "-//com.alexprom.tsp_ws.weighting//weightingAdditional//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "weightingAdditionalTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "output", openAtStartup = true)
@ActionID(category = "Window", id = "com.alexprom.tsp_ws.weighting.weightingAdditionalTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_weightingAdditionalAction",
        preferredID = "weightingAdditionalTopComponent"
)
@Messages({
    "CTL_weightingAdditionalAction=Додаткова інформація",
    "CTL_weightingAdditionalTopComponent=Додаткова інформація",
    "HINT_weightingAdditionalTopComponent=This is a weightingAdditional window"
})
public final class weightingAdditionalTopComponent extends TopComponent implements Runnable{

    private portReader weightReader;
    private Thread weightThread;
    private Thread windowThread;
    private boolean deviceChanged;
    private boolean opened;
    private String rcvMessage;

    public String getRcvMessage() {
        return rcvMessage;
    }

    public void setRcvMessage(String rcvMessage) {
        this.rcvMessage = rcvMessage;
    }

    public int getRcvCurrentWeight() {
        return rcvCurrentWeight;
    }

    public void setRcvCurrentWeight(int rcvCurrentWeight) {
        this.rcvCurrentWeight = rcvCurrentWeight;
    }
    private int rcvCurrentWeight;
    
    public weightingAdditionalTopComponent() {
        this.deviceChanged = false;
        initComponents();
        setName(Bundle.CTL_weightingAdditionalTopComponent());
        setToolTipText(Bundle.HINT_weightingAdditionalTopComponent());
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);

    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        Preferences dbSettings = NbPreferences.forModule(dbConnectionSettingsPanel.class);
        dbSettings.addPreferenceChangeListener(new PreferenceChangeListener() {
        @Override
        public void preferenceChange(PreferenceChangeEvent evt) {                        
            System.out.println("Restarting database communication...");
            updatePersistence();
            //getTask();
            System.out.println("Database communication restarted");
        }
        });
        
        Preferences pref = NbPreferences.forModule(serialPortSettingsPanel.class);
        pref.addPreferenceChangeListener(new PreferenceChangeListener() {
        @Override
        public void preferenceChange(PreferenceChangeEvent evt) {                        
              deviceChanged=true;
              getSerialSettings();
              resumeUpdateVis();
        }
        });
        
        updatePersistence();
        getSerialSettings();
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    private void getSerialSettings(){
        int comPort = NbPreferences.forModule(serialPortSettingsPanel.class).getInt("SerialPort", 0);
        int baudRate = NbPreferences.forModule(serialPortSettingsPanel.class).getInt("Baud rate", 9600);
        int parity = NbPreferences.forModule(serialPortSettingsPanel.class).getInt("Parity", 0);
        int stopBitsCount = NbPreferences.forModule(serialPortSettingsPanel.class).getInt("StopBits", 1);
        int bitsCount = NbPreferences.forModule(serialPortSettingsPanel.class).getInt("DataBits", 8);
        //int timeout = NbPreferences.forModule(serialPortSettingsPanel.class).getInt("Timeout", 1000);
        int mLen = NbPreferences.forModule(serialPortSettingsPanel.class).getInt("Message length", 8);
        weightReader = new portReader(comPort, mLen, baudRate, bitsCount, stopBitsCount, parity);
        
        opened = weightReader.openPort();
        if (!opened){
            weightReader.closePort();
            opened = weightReader.openPort();
        }
        weightReader.flush();
        weightThread = new Thread(weightReader);
        weightThread.setPriority(Thread.MIN_PRIORITY);
        weightThread.start();
        windowThread = new Thread(this);
        windowThread.setPriority(Thread.MIN_PRIORITY);
        windowThread.start();
    }
    
    private void updatePersistence(){
        
    }
    
    private void updateVis(){
        if (weightReader!=null){
            if (weightReader.bytesAvailable>0){
                rcvMessage = weightReader.getSerialMessage();
                rcvCurrentWeight = weightReader.getValue();
            }
        }
    }
    
    synchronized void resumeUpdateVis(){
        deviceChanged = false;
        notify();
    }
    
    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public void run() {
        while(true){
            synchronized(this){
                while(deviceChanged){
                    resumeUpdateVis();
                }
            }
            
            updateVis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                            
            }
        }
    }
}
