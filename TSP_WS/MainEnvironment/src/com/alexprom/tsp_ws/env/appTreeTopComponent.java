/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexprom.tsp_ws.env;

import com.alexprom.tsp_ws.GlobalEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.alexprom.tsp_ws.env//appTree//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "appTreeTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "com.alexprom.tsp_ws.env.appTreeTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_appTreeAction",
        preferredID = "appTreeTopComponent"
)
@Messages({
    "CTL_appTreeAction=Об'єкти ділянки",
    "CTL_appTreeTopComponent=Об'єкти",
    "HINT_appTreeTopComponent=Дерево об'єктів"
})
public final class appTreeTopComponent extends TopComponent {

    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    
    public appTreeTopComponent() {
        initComponents();
        setName(Bundle.CTL_appTreeTopComponent());
        setToolTipText(Bundle.HINT_appTreeTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        updatePersistance();
    }

    public void updatePersistance(){
        GlobalEntityManager gem = new GlobalEntityManager();
        emf = gem.getEmf();
        em = gem.getEm();        
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    
    public EntityManager getEntityManager(){
        return em;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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
}
