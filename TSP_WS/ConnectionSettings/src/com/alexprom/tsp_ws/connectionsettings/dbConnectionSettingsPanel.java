package com.alexprom.tsp_ws.connectionsettings;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.openide.util.NbPreferences;

public final class dbConnectionSettingsPanel extends javax.swing.JPanel {

    private final dbConnectionSettingsOptionsPanelController controller;
    private EntityManagerFactory managerFactory = null;
    private EntityManager manager = null;
    private boolean changed;    
        
    dbConnectionSettingsPanel(dbConnectionSettingsOptionsPanelController controller) {
        this.controller = controller;
        initComponents();        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        userPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        serverAddress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dbName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        showPassword = new javax.swing.JCheckBox();
        btnTest = new javax.swing.JButton();
        btnSetup = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.jPanel1.border.title"))); // NOI18N

        userName.setText(org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.userName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.jLabel4.text")); // NOI18N

        userPassword.setText(org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.userPassword.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.jLabel1.text")); // NOI18N

        serverAddress.setText(org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.serverAddress.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.jLabel2.text")); // NOI18N

        dbName.setText(org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.dbName.text")); // NOI18N
        dbName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbNameActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(showPassword, org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.showPassword.text")); // NOI18N
        showPassword.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                showPasswordPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnTest, org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.btnTest.text")); // NOI18N
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnSetup, org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.btnSetup.text")); // NOI18N
        btnSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(serverAddress)
                            .addComponent(jLabel2)
                            .addComponent(dbName)
                            .addComponent(jLabel3)
                            .addComponent(userName)
                            .addComponent(jLabel4)
                            .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(showPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnTest, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSetup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTest)
                    .addComponent(btnSetup))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), org.openide.util.NbBundle.getMessage(dbConnectionSettingsPanel.class, "dbConnectionSettingsPanel.jPanel2.border.title"))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dbNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dbNameActionPerformed

    private void showPasswordPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_showPasswordPropertyChange
        if (showPassword.isSelected()){
            userPassword.setEchoChar('\0');
        }else{
            userPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_showPasswordPropertyChange

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        jTextArea1.removeAll();
        try{            
            Map<String, String> persistenceMap = new HashMap<>();
            persistenceMap.put("javax.persistence.jdbc.url", "jdbc:sqlserver://"+serverAddress.getText()+":1433;databaseName="+dbName.getText());
            persistenceMap.put("javax.persistence.jdbc.user", userName.getText());
            persistenceMap.put("javax.persistence.jdbc.password", String.valueOf(userPassword.getPassword()));
            persistenceMap.put("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            managerFactory = Persistence.createEntityManagerFactory("ReportDBPU", persistenceMap);
            manager = managerFactory.createEntityManager();
            jTextArea1.append("Gotcha!!!");
        }catch(javax.persistence.PersistenceException ex){
            jTextArea1.append(ex.getLocalizedMessage());
        }
        
    }//GEN-LAST:event_btnTestActionPerformed

    private void btnSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSetupActionPerformed

    void load() {
        serverAddress.setText(NbPreferences.forModule(dbConnectionSettingsPanel.class).get("ipAddress", ""));
        dbName.setText(NbPreferences.forModule(dbConnectionSettingsPanel.class).get("dbName", ""));
        userName.setText(NbPreferences.forModule(dbConnectionSettingsPanel.class).get("userName", ""));
        userPassword.setText(NbPreferences.forModule(dbConnectionSettingsPanel.class).get("userPassword", ""));
    }

    void store() {
        NbPreferences.forModule(dbConnectionSettingsPanel.class).put("ipAddress", serverAddress.getText());
        NbPreferences.forModule(dbConnectionSettingsPanel.class).put("dbName", dbName.getText());
        NbPreferences.forModule(dbConnectionSettingsPanel.class).put("userName", userName.getText());
        NbPreferences.forModule(dbConnectionSettingsPanel.class).put("userPassword", String.valueOf(userPassword.getPassword()));
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSetup;
    private javax.swing.JButton btnTest;
    private javax.swing.JTextField dbName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField serverAddress;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JTextField userName;
    private javax.swing.JPasswordField userPassword;
    // End of variables declaration//GEN-END:variables
}
