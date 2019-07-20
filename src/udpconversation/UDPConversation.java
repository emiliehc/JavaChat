package udpconversation;

import gui.ChatDialog;
import java.net.*;
import javax.swing.JOptionPane;
import java.util.ResourceBundle;

public class UDPConversation {

    // Preferences
    public static boolean filterUnintelligible = true;
    public static boolean legacyEncryption = false;
    public static String host = null;
    public static ResourceBundle bundle = ResourceBundle.getBundle("Resources.en-US");

    // Declarations
    public static gui.ChatDialog cd;
    public static String username;
    public static MessageSender sender;
    public static MessageReceiver receiver;
    public static String key;
    public static long time = 0;
    public static boolean connected = false;

    public static void main(String args[]) {
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
        cd = new gui.ChatDialog();
        host = JOptionPane.showInputDialog(null, "Please enter the server IP address", "108.61.182.134");
        username = JOptionPane.showInputDialog("Please enter your username.");
        key = JOptionPane.showInputDialog("Please enter the code you want to use for this session.");
        try {
            //String host = null;
            if (args.length >= 1) {
                host = args[0];
            } 
            DatagramSocket socket = new DatagramSocket();
            receiver = new MessageReceiver(socket);
            sender = new MessageSender(socket, host);
            Connection connection = new Connection();
            Thread receivingThread = new Thread(receiver);
            Thread sendingThread = new Thread(sender);
            Thread connectionThread = new Thread(connection);
            receivingThread.start();
            sendingThread.start();
            connectionThread.start();
            // start gui
            cd.pack();
            cd.setVisible(true);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void Relaunch(String localeName) {
        cd.dispose();
        bundle = ResourceBundle.getBundle("Resources." + localeName);
        cd = new ChatDialog();
        cd.pack();
        cd.setVisible(true);
    }
}
