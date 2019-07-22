package gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import udpconversation.Encryption;
import udpconversation.UDPConversation;

/**
 *
 * @author njche
 */
public class ChatDialog extends javax.swing.JFrame {

    /**
     * Creates new form ChatDialog
     */
    public ChatDialog() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtReceived = new javax.swing.JTextArea();
        txtSend = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        txtRecipient = new javax.swing.JTextField();
        lblRecipient = new javax.swing.JLabel();
        lblReceiver1 = new javax.swing.JLabel();
        pnlStatusBar = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuWhosOnline = new javax.swing.JMenuItem();
        menuFileExit = new javax.swing.JMenuItem();
        menuOptions = new javax.swing.JMenu();
        menuFilter = new javax.swing.JCheckBoxMenuItem();
        menuLegacyEncryption = new javax.swing.JCheckBoxMenuItem();
        menuLanguages = new javax.swing.JMenu();
        btnEnglish = new javax.swing.JMenuItem();
        btnChinese = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(UDPConversation.bundle.getString("JAVA_CHAT"));
        setName("main"); // NOI18N

        txtReceived.setColumns(20);
        txtReceived.setRows(5);
        jScrollPane1.setViewportView(txtReceived);

        txtSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSendActionPerformed(evt);
            }
        });

        btnSend.setText(UDPConversation.bundle.getString("SEND"));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        lblRecipient.setText(UDPConversation.bundle.getString("RECIPIENT"));

        lblReceiver1.setText(UDPConversation.bundle.getString("TEXT"));

        pnlStatusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        lblStatus.setText(UDPConversation.bundle.getString("CONNECTING"));

        javax.swing.GroupLayout pnlStatusBarLayout = new javax.swing.GroupLayout(pnlStatusBar);
        pnlStatusBar.setLayout(pnlStatusBarLayout);
        pnlStatusBarLayout.setHorizontalGroup(
            pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlStatusBarLayout.setVerticalGroup(
            pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        menuFile.setText(UDPConversation.bundle.getString("PROGRAM"));

        menuWhosOnline.setText(UDPConversation.bundle.getString("SHOW_WHOS_ONLINE"));
        menuWhosOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWhosOnlineActionPerformed(evt);
            }
        });
        menuFile.add(menuWhosOnline);

        menuFileExit.setText(UDPConversation.bundle.getString("EXIT"));
        menuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileExitActionPerformed(evt);
            }
        });
        menuFile.add(menuFileExit);

        menuBar.add(menuFile);

        menuOptions.setText(UDPConversation.bundle.getString("OPTIONS"));

        menuFilter.setSelected(true);
        menuFilter.setText(UDPConversation.bundle.getString("TEXT_FILTER"));
        menuFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFilterActionPerformed(evt);
            }
        });
        menuOptions.add(menuFilter);

        menuLegacyEncryption.setText(UDPConversation.bundle.getString("LEGACY_ENCRYPTION"));
        menuLegacyEncryption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLegacyEncryptionActionPerformed(evt);
            }
        });
        menuOptions.add(menuLegacyEncryption);

        menuLanguages.setText(UDPConversation.bundle.getString("LANGUAGES"));

        btnEnglish.setText("English");
        btnEnglish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnglishActionPerformed(evt);
            }
        });
        menuLanguages.add(btnEnglish);

        btnChinese.setText("中文 (简体)");
        btnChinese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChineseActionPerformed(evt);
            }
        });
        menuLanguages.add(btnChinese);

        menuOptions.add(menuLanguages);

        menuBar.add(menuOptions);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRecipient, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRecipient))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSend)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSend))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblReceiver1)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(pnlStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRecipient)
                    .addComponent(lblReceiver1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSend, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRecipient, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        bufferedOut = Encryption.encrypt(txtSend.getText(), UDPConversation.key);
        txtSend.setText("");
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnWhosOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWhosOnlineActionPerformed
        try {
            // TODO add your handling code here:
            UDPConversation.sender.sendMessage("WHOSONLINE");
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_btnWhosOnlineActionPerformed

    private void menuFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_menuFileExitActionPerformed

    private void menuFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFilterActionPerformed
        // TODO add your handling code here:
        UDPConversation.filterUnintelligible = !UDPConversation.filterUnintelligible;
    }//GEN-LAST:event_menuFilterActionPerformed

    private void menuLegacyEncryptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLegacyEncryptionActionPerformed
        // TODO add your handling code here:
        UDPConversation.legacyEncryption = !UDPConversation.legacyEncryption;
    }//GEN-LAST:event_menuLegacyEncryptionActionPerformed

    private void txtSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSendActionPerformed

    private void btnEnglishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnglishActionPerformed
        // TODO add your handling code here:
        try (FileWriter fw = new FileWriter(new File("preferences.pref"))) {
            fw.write("en-US");
        } catch (IOException e) {
            System.err.println(e);
        }
        UDPConversation.Relaunch("en-US");
    }//GEN-LAST:event_btnEnglishActionPerformed

    private void btnChineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChineseActionPerformed
        // TODO add your handling code here:
        try (FileWriter fw = new FileWriter(new File("preferences.pref"))) {
            fw.write("zh-CN");
        } catch (IOException e) {
            System.err.println(e);
        }
        UDPConversation.Relaunch("zh-CN");
    }//GEN-LAST:event_btnChineseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatDialog().setVisible(true);
            }
        });

    }

    public String getText() throws IOException {
        if (bufferedOut.equals("")) {
            // there is nothing to send
            throw new java.io.IOException();
        } else {
            String out = bufferedOut;
            bufferedOut = "";
            return out;
        }
    }

    public String getRecipient() {
        return txtRecipient.getText();
    }

    public void receive(String msg) {
        txtReceived.setText(msg + "\n" + txtReceived.getText());
    }

    private String bufferedOut = "";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnChinese;
    private javax.swing.JMenuItem btnEnglish;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblReceiver1;
    private javax.swing.JLabel lblRecipient;
    public javax.swing.JLabel lblStatus;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFileExit;
    private javax.swing.JCheckBoxMenuItem menuFilter;
    private javax.swing.JMenu menuLanguages;
    private javax.swing.JCheckBoxMenuItem menuLegacyEncryption;
    private javax.swing.JMenu menuOptions;
    private javax.swing.JMenuItem menuWhosOnline;
    private javax.swing.JPanel pnlStatusBar;
    private javax.swing.JTextArea txtReceived;
    private javax.swing.JTextField txtRecipient;
    private javax.swing.JTextField txtSend;
    // End of variables declaration//GEN-END:variables
}
