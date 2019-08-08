package conversation;
import java.net.*;
import java.io.*;
/**
 *
 * @author njche
 */
public class Connection implements Runnable {
    private byte[] data;
    
    
    Connection() {
        data = ("USERCONNECTION:" + Conversation.username).getBytes();
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Conversation.sender.sendMessage("USERCONNECTION:" + Conversation.username);
                
                Conversation.sender.sock.send(new DatagramPacket(data, data.length, InetAddress.getByName(Conversation.sender.hostname), Conversation.sender.PORT));
                //System.out.println((java.lang.System.currentTimeMillis() - Conversation.time) / 1000.0);
                if (java.lang.System.currentTimeMillis() - Conversation.time > 3000) {
                    Conversation.connected = false;
                    Conversation.cd.lblStatus.setText(Conversation.bundle.getString("CONNECTING"));
                } 
                Thread.sleep(1000);
            } catch (Exception ex) {
                
            }
        }
    }

}
