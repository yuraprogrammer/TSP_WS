package com.alexprom.tsp_ws.weighting;

import com.alexprom.asutpdb.MatDic;
import com.alexprom.asutpdb.Nodes;
import com.alexprom.asutpdb.Organizations;
import com.alexprom.asutpdb.PresentTrucks;
import com.alexprom.asutpdb.Trucks;
import com.alexprom.asutpdb.Weightings;
import com.alexprom.asutpdb.services.NodesJpaController;
import com.alexprom.asutpdb.services.TrucksJpaController;
import com.alexprom.asutpdb.services.WeightingsJpaController;
import com.alexprom.tsp_ws.connectionsettings.dbConnectionSettingsPanel;
import com.alexprom.tsp_ws.env.appTreeTopComponent;
import com.sun.glass.events.KeyEvent;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

@ConvertAsProperties(
        dtd = "-//com.alexprom.tsp_ws.weighting//weightingForm//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "weightingFormTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "com.alexprom.tsp_ws.weighting.weightingFormTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_weightingFormAction",
        preferredID = "weightingFormTopComponent"
)
@Messages({
    "CTL_weightingFormAction=Зважування",
    "CTL_weightingFormTopComponent=Зважування автівок",
    "HINT_weightingFormTopComponent=Вікно зважування автівок"
})
public final class weightingFormTopComponent extends TopComponent implements Runnable{

    private weightingAdditionalTopComponent wat;
    private Thread formThread;
    private EntityManagerFactory emf;
    private EntityManager em;
    private List<Organizations> customerList;
    private List<MatDic> materialList;
    private boolean startWeight;
    private boolean oldWeighting;
    private BigInteger netto, brutto, tara, netto_kalibr, volume_plotn, kalibr;
    private long p_key;
    private BigInteger w1, w2;
    private float plotn=1;
    private long oldWeightingNumber, newWeighting, lastWeighting;
    private DefaultTableModel presentTrucksModel;
    private ComboBoxModel orgsModel, materialsModel;
    
    public weightingFormTopComponent() {
        
        initComponents();
        setName(Bundle.CTL_weightingFormTopComponent());
        setToolTipText(Bundle.HINT_weightingFormTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.FALSE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_SLIDING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);
        wat = (weightingAdditionalTopComponent)WindowManager.getDefault().findTopComponent("weightingAdditionalTopComponent");
        updatePersistence();
        initStartData();
        Preferences pref = NbPreferences.forModule(dbConnectionSettingsPanel.class);
        pref.addPreferenceChangeListener(new PreferenceChangeListener(){
            @Override
            public void preferenceChange(PreferenceChangeEvent evt) {
                updatePersistence();
            }            
        });        
    }
    
    private void updatePersistence(){
        appTreeTopComponent attc = (appTreeTopComponent)WindowManager.getDefault().findTopComponent("appTreeTopComponent");
        if (attc!=null){
            emf = attc.getEntityManagerFactory();
            em = attc.getEntityManager();
            if (em!=null){
                cbCustomers.removeAllItems();
                cbChargingMaterial.removeAllItems();                               
                Query customers = em.createNamedQuery("Organizations.findAll");
                customerList = customers.getResultList();
                for (Organizations o :customerList){
                    cbCustomers.addItem(o.getName());
                }
                Query materials = em.createNamedQuery("MatDic.findAll");
                materialList = materials.getResultList();
                for (MatDic m : materialList){
                    cbChargingMaterial.addItem(m.getMatName());
                }
            }
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbCustomers = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbChargingMaterial = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        btnPresentTruks = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rcvCurrentWeight = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rcvMessage = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnNewWeighting = new javax.swing.JButton();
        btnFixWeight = new javax.swing.JButton();
        btnSaveWeighting = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfWeightNum = new javax.swing.JFormattedTextField();
        tfInDate = new javax.swing.JFormattedTextField();
        tfInTime = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        tfVehicleNum = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfVehicleCalibr = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        tfOutDate = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        tfOutTime = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        tfDensity = new javax.swing.JFormattedTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lbTara = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbBrutto = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbNetto = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbNettoCalibr = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbVolume = new javax.swing.JLabel();
        btnResetWeighting = new javax.swing.JButton();

        setAttentionHighlight(false);
        setMaximumSize(new java.awt.Dimension(1012, 458));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel16.text")); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel17.text")); // NOI18N
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel18.text")); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel19.text")); // NOI18N
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel20.text")); // NOI18N
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel17))
                .addGap(26, 26, 26)
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel9.text")); // NOI18N

        cbCustomers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jButton1.text")); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel10.text")); // NOI18N

        cbChargingMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jButton2.text")); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel11.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jTextField2.text")); // NOI18N

        btnPresentTruks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alexprom/tsp_ws/weighting/open.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnPresentTruks, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.btnPresentTruks.text")); // NOI18N
        btnPresentTruks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPresentTruksActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel12.text")); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "№ зважування", "Організація", "Вага, кг"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setEnabled(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(cbCustomers, 0, 200, Short.MAX_VALUE)
                            .addComponent(cbChargingMaterial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPresentTruks, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnPresentTruks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbChargingMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        rcvCurrentWeight.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        rcvCurrentWeight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(rcvCurrentWeight, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.rcvCurrentWeight.text")); // NOI18N
        rcvCurrentWeight.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel13.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(rcvMessage, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.rcvMessage.text")); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel15.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rcvCurrentWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(rcvMessage)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rcvMessage, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rcvCurrentWeight)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(26, 26, 26))))
        );

        org.openide.awt.Mnemonics.setLocalizedText(btnNewWeighting, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.btnNewWeighting.text")); // NOI18N
        btnNewWeighting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewWeightingActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnFixWeight, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.btnFixWeight.text")); // NOI18N
        btnFixWeight.setEnabled(false);
        btnFixWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFixWeightActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnSaveWeighting, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.btnSaveWeighting.text")); // NOI18N
        btnSaveWeighting.setEnabled(false);
        btnSaveWeighting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveWeightingActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel4.text")); // NOI18N

        tfWeightNum.setEditable(false);
        tfWeightNum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tfWeightNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tfInDate.setEditable(false);
        tfInDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        tfInDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tfInTime.setEditable(false);
        tfInTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        tfInTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel5.text")); // NOI18N

        tfVehicleNum.setText(org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.tfVehicleNum.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel6.text")); // NOI18N

        tfVehicleCalibr.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tfVehicleCalibr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfVehicleCalibrKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel7.text")); // NOI18N

        tfOutDate.setEditable(false);
        tfOutDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel8.text")); // NOI18N

        tfOutTime.setEditable(false);
        tfOutTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel21, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel21.text")); // NOI18N

        tfDensity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.000"))));
        tfDensity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfDensityFocusLost(evt);
            }
        });
        tfDensity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfDensityKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tfInDate, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tfInTime, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfWeightNum)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfVehicleNum, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfVehicleCalibr)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDensity, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tfOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(tfOutTime, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfWeightNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfInDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfInTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfVehicleNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfVehicleCalibr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfOutTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jPanel7.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel14.text")); // NOI18N

        lbTara.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTara.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lbTara, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.lbTara.text")); // NOI18N
        lbTara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel22, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel22.text")); // NOI18N

        lbBrutto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbBrutto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lbBrutto, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.lbBrutto.text")); // NOI18N
        lbBrutto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel23, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel23.text")); // NOI18N

        lbNetto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbNetto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lbNetto, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.lbNetto.text")); // NOI18N
        lbNetto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel24, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel24.text")); // NOI18N

        lbNettoCalibr.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbNettoCalibr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lbNettoCalibr, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.lbNettoCalibr.text")); // NOI18N
        lbNettoCalibr.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel25, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.jLabel25.text")); // NOI18N

        lbVolume.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbVolume.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lbVolume, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.lbVolume.text")); // NOI18N
        lbVolume.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbBrutto)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel14))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel22))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbNettoCalibr)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbNetto)
                                        .addComponent(lbTara))
                                    .addComponent(lbVolume)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(10, 10, 10)
                .addComponent(lbBrutto)
                .addGap(7, 7, 7)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTara)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNetto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNettoCalibr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbVolume)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(btnResetWeighting, org.openide.util.NbBundle.getMessage(weightingFormTopComponent.class, "weightingFormTopComponent.btnResetWeighting.text")); // NOI18N
        btnResetWeighting.setEnabled(false);
        btnResetWeighting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetWeightingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addComponent(btnNewWeighting)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFixWeight)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSaveWeighting)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnResetWeighting))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewWeighting)
                    .addComponent(btnFixWeight)
                    .addComponent(btnSaveWeighting)
                    .addComponent(btnResetWeighting))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewWeightingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewWeightingActionPerformed
        //New weighting
        startWeight = true;
        btnNewWeighting.setEnabled(false);
        btnFixWeight.setEnabled(true);
        btnResetWeighting.setEnabled(true);
        btnPresentTruks.setEnabled(false);
        jLabel20.setVisible(false);
        lbBrutto.setText("0 кг");
        lbNetto.setText("0 кг");
        lbTara.setText("0 кг");
        if (!oldWeighting){
            jLabel19.setText("0 кг");
        }
        lbVolume.setText("0 л");
        
    }//GEN-LAST:event_btnNewWeightingActionPerformed

    private void btnFixWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFixWeightActionPerformed
        netto = BigInteger.ZERO;
        brutto = BigInteger.ZERO;
        tara = BigInteger.ZERO;
        btnSaveWeighting.setEnabled(true);
        if (!oldWeighting){
            jLabel19.setText(String.valueOf(wat.getRcvCurrentWeight())+" кг");
            w1 = BigInteger.valueOf(wat.getRcvCurrentWeight());
        }else{
            jLabel17.setText(String.valueOf(wat.getRcvCurrentWeight())+" кг");
            w2 = BigInteger.valueOf(wat.getRcvCurrentWeight());
            if (em!=null){
                Query query = em.createNamedQuery("Trucks.findByWeightingNumber");
                query.setParameter("weightingNumber", p_key);
                List<Trucks> truck = query.getResultList();
                if (!truck.isEmpty()){
                    w1 = truck.get(0).getWeighting1();
                    if (w1.longValue()>w2.longValue()){
                        brutto = w1;
                        netto = w1.subtract(w2);
                        tara = w2;
                    }
                    if (w2.longValue()>w1.longValue()){
                        brutto = w2;
                        netto = w2.subtract(w1);
                        tara = w1;
                    }
                    kalibr = BigInteger.valueOf(getKalibr());
                    netto_kalibr = BigInteger.valueOf(Math.round(kalibr.longValue()*plotn));
                    volume_plotn = BigInteger.valueOf(Math.round(netto.longValue()/plotn));
                    lbBrutto.setText(String.valueOf(brutto)+" кг");
                    lbNetto.setText(String.valueOf(netto)+" кг");
                    lbTara.setText(String.valueOf(tara)+" кг");
                    lbNettoCalibr.setText(String.valueOf(netto_kalibr)+" кг");
                    lbVolume.setText(String.valueOf(volume_plotn)+" л");
                }
            }
        }
    }//GEN-LAST:event_btnFixWeightActionPerformed

    private void btnResetWeightingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetWeightingActionPerformed
        btnResetWeighting.setEnabled(false);
        btnNewWeighting.setEnabled(true);
        btnFixWeight.setEnabled(false);
        btnSaveWeighting.setEnabled(false);
        startWeight = false;
        btnPresentTruks.setEnabled(true);        
        jLabel20.setVisible(false);
        lbBrutto.setText("0 кг");
        lbNetto.setText("0 кг");
        lbTara.setText("0 кг");
        jLabel19.setText("0 кг");
        lbVolume.setText("0 л");
        cbCustomers.setSelectedIndex(0);
        cbChargingMaterial.setSelectedIndex(0);
        hideTrucksTable();
    }//GEN-LAST:event_btnResetWeightingActionPerformed

    private void showTrucksTable(){
        
        oldWeighting = true;
        
        Query query = em.createNamedQuery("PresentTrucks.findByInDate");
        query.setParameter("inDate", "01.10.2021");
        List<PresentTrucks> trucks = query.getResultList();
        presentTrucksModel = new DefaultTableModel();
        presentTrucksModel.setColumnIdentifiers(new String[]{"№ зважування", "Організація", "Вага, кг"});
        
        for (PresentTrucks truck : trucks){
            presentTrucksModel.addRow(new Object[]{truck.getWeightingNumber(), truck.getOrgName(), truck.getWeighting1()});
        }
        jTable1.setModel(presentTrucksModel);
        if (presentTrucksModel.getRowCount()!=0){
            p_key = (Long)presentTrucksModel.getValueAt(0, 0);
            fillFieldsPresentTruck(p_key);
        }
        jScrollPane1.setVisible(true);
    }
        
    private void hideTrucksTable(){
        jScrollPane1.setVisible(false);
        oldWeighting = false;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd:MM:YYYY");
        DateFormat tf = new SimpleDateFormat("hh:mm:ss");
        tfInDate.setText(df.format(date));
        tfInTime.setText(tf.format(date));
        tfWeightNum.setText(String.valueOf(newWeighting));
        tfVehicleNum.setText("");
        tfVehicleCalibr.setText("");
        tfOutDate.setText("");
        tfOutTime.setText("");
        jLabel20.setVisible(false);
        lbBrutto.setText("0 кг");
        lbNetto.setText("0 кг");
        lbTara.setText("0 кг");
        jLabel19.setText("0 кг");
        lbVolume.setText("0 л");
        cbCustomers.setSelectedIndex(0);
        cbChargingMaterial.setSelectedIndex(0);
            
    }
    
    private void btnPresentTruksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPresentTruksActionPerformed
        boolean visible = jScrollPane1.isVisible();
        if (visible){
            hideTrucksTable();
        }else{
            showTrucksTable();
        }        
        disableControls(oldWeighting);
    }//GEN-LAST:event_btnPresentTruksActionPerformed

    private void btnSaveWeightingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveWeightingActionPerformed
        
        lastWeighting = newWeighting;
        
        String timeStr;
        String dateStr;
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat ddf = new SimpleDateFormat("dd:MM:YYYY");
        dateStr = ddf.format(time);
        timeStr = sdf.format(time);
        if (timeStr.length()!=8){
            timeStr = "0"+timeStr;
        }
                
        if (!oldWeighting){
            if (emf!=null){                
                try {
                    Query query = em.createQuery("SELECT MAX(n.rootNode) FROM NODES n");
                    Long id;
                    Object maxId = query.getSingleResult();
                    if (maxId!=null){
                        id = (Long)maxId;
                    }else{
                        id = Long.valueOf("0");
                    }
                    id = id + 1;
                    
                    Weightings newWeightings = new Weightings();
                    WeightingsJpaController weightController = new WeightingsJpaController(emf);
                    newWeightings.setRootNode(id);
                    newWeightings.setDataNode(2);
                    newWeightings.setWeightNumber(lastWeighting);//Has to be checked
                    
                    newWeightings.setWeightTime(timeStr);
                    weightController.create(newWeightings);
                    
                    Trucks newTruck = new Trucks();
                    TrucksJpaController truckController = new TrucksJpaController(emf);
                    newTruck.setWeightingNumber(lastWeighting);
                    newTruck.setTruckCode(0);
                    newTruck.setInDate(dateStr);
                    newTruck.setInTime(timeStr);
                    newTruck.setWeighting1(w1);
                    newTruck.setWeighting2(BigInteger.ZERO);
                    newTruck.setPresent(Short.valueOf("1"));
                    newTruck.setTruckNumber(Integer.parseInt(tfVehicleNum.getText()));
                    newTruck.setOrgId(Short.valueOf(String.valueOf(cbCustomers.getSelectedIndex())));
                    newTruck.setLoadId(Short.valueOf(String.valueOf(cbChargingMaterial.getSelectedIndex())));
                    newTruck.setRemark(jTextField2.getText());
                    truckController.create(newTruck);
                    
                    Nodes newNode = new Nodes();
                    NodesJpaController nodesController = new NodesJpaController(emf);
                    newNode.setRootNode(id);
                    newNode.setYearNode(time.getYear());
                    String monthName=getMonthName(time.getMonth());                    
                    newNode.setMonthNode(monthName);
                    newNode.setDayNode(Short.valueOf(String.valueOf(Calendar.DAY_OF_MONTH)));
                    nodesController.create(newNode);
                    
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }                
            }
        }else{
            try {                
                TrucksJpaController truckController = new TrucksJpaController(emf);
                Trucks updateTruck = truckController.findTrucks(p_key);
                updateTruck.setWeighting2(w2);
                updateTruck.setNetto1(netto);
                updateTruck.setBrutto1(brutto);
                updateTruck.setTara(tara);
                updateTruck.setOrgId(Short.valueOf(String.valueOf(cbCustomers.getSelectedIndex())));
                updateTruck.setOutDate(dateStr);
                updateTruck.setOutTime(timeStr);
                updateTruck.setPresent(Short.valueOf("0"));
                updateTruck.setNettoKalibr(netto_kalibr);
                updateTruck.setVolumePlotn(volume_plotn);
                updateTruck.setRemark(jTextField2.getText());
                truckController.edit(updateTruck);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        btnSaveWeighting.setEnabled(false);
        initStartData();
    }//GEN-LAST:event_btnSaveWeightingActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
            int row = jTable1.getSelectedRow();
            p_key = (Long)jTable1.getModel().getValueAt(row, 0);
            fillFieldsPresentTruck(p_key);
    }//GEN-LAST:event_jTable1MouseClicked

    private void tfDensityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDensityKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            plotn = getDensity();
        }
    }//GEN-LAST:event_tfDensityKeyPressed

    private void tfDensityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDensityFocusLost
        plotn = getDensity();
    }//GEN-LAST:event_tfDensityFocusLost

    private void tfVehicleCalibrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfVehicleCalibrKeyPressed
                // TODO add your handling code here:
    }//GEN-LAST:event_tfVehicleCalibrKeyPressed

    private Long getKalibr(){
        long k = 0;
        String strCalibr = tfVehicleCalibr.getText().trim();
        if (!strCalibr.isEmpty()){
            k = Long.parseLong(strCalibr);
        }
        return k;
    }
    
    private float getDensity(){
        float d = 1;
        String trimmedValue = tfDensity.getText().trim();
        if (!trimmedValue.isEmpty()){
            String delimiter = trimmedValue.replace(",", ".");
            d = Float.parseFloat(delimiter);
        }
        return d;
    }
    
    private void fillFieldsPresentTruck(Long wNum){
        TrucksJpaController pTruckCtrl = new TrucksJpaController(emf);
        Trucks pTruck = pTruckCtrl.findTrucks(wNum);
        int orgId = pTruck.getOrgId();
        int matId = pTruck.getLoadId();
        String inDate = pTruck.getInDate();
        tfInDate.setText(inDate);
        String inTime = pTruck.getInTime();
        tfInTime.setText(inTime);
        int truckNum = pTruck.getTruckNumber();
        tfVehicleNum.setText(String.valueOf(truckNum));
        tfWeightNum.setText(String.valueOf(p_key));
        for (Organizations o : customerList){
            if (o.getId()==orgId){
                cbCustomers.setSelectedIndex(customerList.indexOf(o));
                break;
            }
        }
        for (MatDic m : materialList){
            if (m.getMatId()==matId){
                cbChargingMaterial.setSelectedIndex(materialList.indexOf(m));
            }
        }
        jLabel19.setText(pTruck.getWeighting1().toString() + " кг");
        lbBrutto.setText(pTruck.getBrutto1().toString() + " кг");
        lbTara.setText(pTruck.getTara().toString() + " кг");
        lbNetto.setText(pTruck.getNetto1().toString() + " кг");
        lbNettoCalibr.setText(pTruck.getNakladId().toString() + " кг");
        lbVolume.setText(pTruck.getVolumePlotn().toString() + " л");        
        //tfVehicleCalibr.setText(String.valueOf(pTruck.get));
    }
    
    private String getMonthName(int num){
        String monthName = "";
        switch (num){
            case Calendar.JANUARY:{monthName = "Січень";break;}
            case Calendar.FEBRUARY:{monthName = "Лютий";break;}
            case Calendar.MARCH:{monthName = "Березень";break;}
            case Calendar.APRIL:{monthName = "Квітень";break;}
            case Calendar.MAY:{monthName = "Травень";break;}
            case Calendar.JUNE:{monthName = "Червень";break;}
            case Calendar.JULY:{monthName = "Липень";break;}
            case Calendar.AUGUST:{monthName = "Серпень";break;}
            case Calendar.SEPTEMBER:{monthName = "Вересень";break;}
            case Calendar.OCTOBER:{monthName = "Жовтень";break;}
            case Calendar.NOVEMBER:{monthName = "Листопад";break;}
            case Calendar.DECEMBER:{monthName = "Грудень";break;}
        }        
        return monthName;
    }
    
    private void disableControls(boolean value){
        tfVehicleNum.setEnabled(!value);
        //tfVehicleCalibr.setEnabled(!value);
        cbCustomers.setEnabled(!value);
        cbChargingMaterial.setEnabled(!value);
    }
    
    private Long getNewWeightingNum(){
        Long id = null;
        if (em!=null){
            Query query = em.createQuery("select MAX(w.weightNumber) from Weightings w");
            Object maxID = query.getSingleResult();
            if (maxID!=null){
                id = (Long)maxID+1;
            }else{
                id = null;
            }
        }
        return id;
    }
    
    private String getCurrentOperator(){
        String name = "";
        if (em!=null){
            Query query = em.createNamedQuery("Dutyoperator.findByOpId");
            query.setParameter(1, 2);
            Object fio = query.getSingleResult();
            if (fio!=null){
                name = (String)fio;
            }else{
                name="";
            }
        }        
        return name;
    }
    
    private void initStartData(){
        Long wNum = getNewWeightingNum();
        newWeighting = wNum;
        //String fio = getCurrentOperator();
        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        tfInDate.setText(dateFormat.format(curDate));
        tfInTime.setText(timeFormat.format(curDate));
        tfWeightNum.setText(String.valueOf(wNum));
        jLabel20.setVisible(false);
        btnPresentTruks.setEnabled(true);
        btnNewWeighting.setEnabled(true);
        btnSaveWeighting.setEnabled(false);
        btnFixWeight.setEnabled(false);
        btnResetWeighting.setEnabled(false);
        jScrollPane1.setVisible(false);
        jTable1.setEnabled(true);
        oldWeighting = false;
        disableControls(oldWeighting);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFixWeight;
    private javax.swing.JButton btnNewWeighting;
    private javax.swing.JButton btnPresentTruks;
    private javax.swing.JButton btnResetWeighting;
    private javax.swing.JButton btnSaveWeighting;
    private javax.swing.JComboBox<String> cbChargingMaterial;
    private javax.swing.JComboBox<String> cbCustomers;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbBrutto;
    private javax.swing.JLabel lbNetto;
    private javax.swing.JLabel lbNettoCalibr;
    private javax.swing.JLabel lbTara;
    private javax.swing.JLabel lbVolume;
    private javax.swing.JLabel rcvCurrentWeight;
    private javax.swing.JLabel rcvMessage;
    private javax.swing.JFormattedTextField tfDensity;
    private javax.swing.JFormattedTextField tfInDate;
    private javax.swing.JFormattedTextField tfInTime;
    private javax.swing.JFormattedTextField tfOutDate;
    private javax.swing.JFormattedTextField tfOutTime;
    private javax.swing.JFormattedTextField tfVehicleCalibr;
    private javax.swing.JTextField tfVehicleNum;
    private javax.swing.JFormattedTextField tfWeightNum;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        
        formThread = new Thread(this);
        formThread.setPriority(Thread.MIN_PRIORITY);
        formThread.start();
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

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    
    
    @Override
    public void run() {        
            while(true){
                rcvMessage.setText(wat.getRcvMessage());
                rcvCurrentWeight.setText(String.valueOf(wat.getRcvCurrentWeight())+" кг");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
            }
            
        
    }
}
