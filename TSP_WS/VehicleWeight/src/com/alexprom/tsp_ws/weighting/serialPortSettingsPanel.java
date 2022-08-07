package com.alexprom.tsp_ws.weighting;

import com.fazecast.jSerialComm.SerialPort;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

final class serialPortSettingsPanel extends javax.swing.JPanel implements Runnable{

    private final serialPortSettingsOptionsPanelController controller;
    private final String[] portSpeed;
    private final String[] stopBits;
    private final String[] portParity;
    private portReader port;
    private Thread thread;
    private Thread portThread;
    private int stepsCount=5, curStep;
    private boolean stopTest = true;
    
    serialPortSettingsPanel(serialPortSettingsOptionsPanelController controller) {
        this.portSpeed = new String[]{"9600", "19200", "47500", "56000"};
        this.stopBits = new String[]{"1", "1.5", "2"};
        this.portParity = new String[]{"Немає", "Парний", "Непарний"};
        this.controller = controller;
        initComponents();
        
        btnExchangeTest.setEnabled(true);
        btnStopTest.setEnabled(false);
        cbSerialPortSelector.removeAllItems();
        SerialPort[] availablePorts = SerialPort.getCommPorts();
        int portsCnt = availablePorts.length;
        if (portsCnt != 0){
            for (SerialPort ports : availablePorts){
                cbSerialPortSelector.addItem(ports.getSystemPortName());
            }
        }
        cbSpeedSelector.removeAllItems();
        for (String portSpeed1 : this.portSpeed) {
            cbSpeedSelector.addItem(portSpeed1);
        }
        cntBitsCount.setValue(8);
        cbStopBitsCount.removeAllItems();
        for (String stopBitsCount : this.stopBits){
            cbStopBitsCount.addItem(stopBitsCount);
        }
        cbParitySelector.removeAllItems();
        for (String parity : this.portParity){
            cbParitySelector.addItem(parity);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbSerialPortSelector = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbSpeedSelector = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cntBitsCount = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        cbStopBitsCount = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tfTimeout = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbParitySelector = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        tfMessageLength = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        taSerialDataOutput = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        btnExchangeTest = new javax.swing.JButton();
        btnStopTest = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jPanel1.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel1.text")); // NOI18N

        cbSerialPortSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel2.text")); // NOI18N

        cbSpeedSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel4.text")); // NOI18N

        cbStopBitsCount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel5.text")); // NOI18N

        tfTimeout.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tfTimeout.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfTimeout.setText(org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.tfTimeout.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel8.text")); // NOI18N

        cbParitySelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel10.text")); // NOI18N

        tfMessageLength.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tfMessageLength.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfMessageLength.setText(org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.tfMessageLength.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jLabel11.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSerialPortSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSpeedSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cntBitsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbParitySelector, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbStopBitsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(tfMessageLength, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbSerialPortSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbSpeedSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cntBitsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbStopBitsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbParitySelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfMessageLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jPanel2.border.title"))); // NOI18N

        jScrollPane1.setBorder(null);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText(org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jTextArea1.text")); // NOI18N
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.jTextArea1.border.title"))); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane3.setBorder(null);

        taSerialDataOutput.setEditable(false);
        taSerialDataOutput.setColumns(20);
        taSerialDataOutput.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        taSerialDataOutput.setRows(5);
        taSerialDataOutput.setText(org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.taSerialDataOutput.text")); // NOI18N
        taSerialDataOutput.setAutoscrolls(false);
        taSerialDataOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.taSerialDataOutput.border.title"))); // NOI18N
        jScrollPane3.setViewportView(taSerialDataOutput);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(btnExchangeTest, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.btnExchangeTest.text")); // NOI18N
        btnExchangeTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExchangeTestActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnStopTest, org.openide.util.NbBundle.getMessage(serialPortSettingsPanel.class, "serialPortSettingsPanel.btnStopTest.text")); // NOI18N
        btnStopTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopTestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExchangeTest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStopTest)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStopTest)
                    .addComponent(btnExchangeTest))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExchangeTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExchangeTestActionPerformed
        int testPort = cbSerialPortSelector.getSelectedIndex();
        int BaudRate = Integer.parseInt(cbSpeedSelector.getSelectedItem().toString());
        int DataBits = ((Integer)cntBitsCount.getValue());
        int StopBits = cbStopBitsCount.getSelectedIndex();
        int Parity = cbParitySelector.getSelectedIndex()+1;
        int dLength = Integer.parseInt(tfMessageLength.getText());
        //testPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, Integer.parseInt(tfTimeout.getText()), 0);
        port = new portReader(testPort, dLength, BaudRate, DataBits, StopBits, Parity);
        taSerialDataOutput.setText("");
        curStep=0;
        port.closePort();
        port.openPort();
        port.flush();
        btnExchangeTest.setEnabled(false);
        btnStopTest.setEnabled(true);
        portThread = new Thread(port);
        portThread.setPriority(Thread.MIN_PRIORITY);
        portThread.start();
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        stopTest = false;    
        
    }//GEN-LAST:event_btnExchangeTestActionPerformed

    private void btnStopTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopTestActionPerformed
        stopTest = true;                
        btnStopTest.setEnabled(false);
        btnExchangeTest.setEnabled(true);
    }//GEN-LAST:event_btnStopTestActionPerformed

    void load() {
        cbSerialPortSelector.setSelectedIndex(NbPreferences.forModule(serialPortSettingsPanel.class).getInt("SerialPort", 0));
        cbSpeedSelector.setSelectedIndex(NbPreferences.forModule(serialPortSettingsPanel.class).getInt("Baud rate", 0));
        cbParitySelector.setSelectedIndex(NbPreferences.forModule(serialPortSettingsPanel.class).getInt("Parity", 0));
        cbStopBitsCount.setSelectedIndex(NbPreferences.forModule(serialPortSettingsPanel.class).getInt("StopBits", 0));
        cntBitsCount.setValue(NbPreferences.forModule(serialPortSettingsPanel.class).getInt("DataBits", 8));
        tfTimeout.setText(String.valueOf(NbPreferences.forModule(serialPortSettingsPanel.class).getInt("Timeout", 1000)));
        tfMessageLength.setText(String.valueOf(NbPreferences.forModule(serialPortSettingsPanel.class).getInt("Message length", 8)));
    }

    void store() {
        NbPreferences.forModule(serialPortSettingsPanel.class).putInt("SerialPort", cbSerialPortSelector.getSelectedIndex());
        NbPreferences.forModule(serialPortSettingsPanel.class).putInt("Baud rate", cbSpeedSelector.getSelectedIndex());
        NbPreferences.forModule(serialPortSettingsPanel.class).putInt("Parity", cbParitySelector.getSelectedIndex());
        NbPreferences.forModule(serialPortSettingsPanel.class).putInt("StopBits", cbStopBitsCount.getSelectedIndex());
        NbPreferences.forModule(serialPortSettingsPanel.class).putInt("DataBits", ((Integer)cntBitsCount.getValue()));
        NbPreferences.forModule(serialPortSettingsPanel.class).putInt("Timeout", Integer.parseInt(tfTimeout.getText()));
        NbPreferences.forModule(serialPortSettingsPanel.class).putInt("Message length", Integer.parseInt(tfMessageLength.getText()));
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExchangeTest;
    private javax.swing.JButton btnStopTest;
    private javax.swing.JComboBox<String> cbParitySelector;
    private javax.swing.JComboBox<String> cbSerialPortSelector;
    private javax.swing.JComboBox<String> cbSpeedSelector;
    private javax.swing.JComboBox<String> cbStopBitsCount;
    private javax.swing.JSpinner cntBitsCount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea taSerialDataOutput;
    private javax.swing.JFormattedTextField tfMessageLength;
    private javax.swing.JFormattedTextField tfTimeout;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while(true){
        synchronized(this){
            while(!stopTest){
                if (port!=null){
                    if (port.bytesAvailable>0){
                        taSerialDataOutput.append(port.getSerialMessage()+"\n");
                        
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                }
                curStep++;
            }
            port.closePort();
            btnExchangeTest.setEnabled(true);
            btnStopTest.setEnabled(false);    
        }}
    }
}
