
package udpconversation;


import java.net.*;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

public class UDPConversation {
    public static gui.ChatDialog cd = new gui.ChatDialog();
    public static String username = JOptionPane.showInputDialog("Please enter your username");
    
    public static void main(String args[]) throws NoSuchAlgorithmException {
        try {
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
            MessageReceiver receiver = new MessageReceiver(socket);
            MessageSender sender = new MessageSender(socket, host);
            Thread receivingThread = new Thread(receiver);
            Thread sendingThread = new Thread(sender);
            receivingThread.start();
            sendingThread.start();
            
            // start gui
            cd.pack();
            cd.setVisible(true);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
