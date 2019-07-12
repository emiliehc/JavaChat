package udpconversation;

import gui.ChatDialog;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.swing.JOptionPane;

public class UDPConversation {

    public static gui.ChatDialog cd;
    public static String username;
    public static MessageSender sender;
    public static MessageReceiver receiver;
    public static String key;

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
        username = JOptionPane.showInputDialog("Please enter your username.");
        key = JOptionPane.showInputDialog("Please enter the code you want to use for this session.");
        // hash the key
        MessageDigest digest;
        try {
            MessageDigest digestMsg = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digestMsg.digest(
                    key.getBytes(StandardCharsets.US_ASCII));
            key = encodedhash.toString();

            String host = null;
            args = new String[]{"108.61.182.134"};
            //args = new String[]{"192.168.0.3"};
            if (args.length < 1) {
                System.out.println("Please use the client like this: java ChatClient <server_hostname>");
                System.exit(1);
            } else {
                host = args[0]; // the host variable now holds the ip address of the server
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
}
