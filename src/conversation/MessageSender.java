package conversation;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class MessageSender implements Runnable {

    public final static int PORT = 7331;
    public DatagramSocket sock;
    public String hostname;
    public List<DatagramPacket> queue = new ArrayList<>();
    MessageSendingService mss;

    MessageSender(DatagramSocket s, String h) {
        sock = s;
        hostname = h;
        mss = new MessageSendingService();
        Thread mssThread = new Thread(mss);
        mssThread.start();
    }

    public void sendMessage(String s) throws Exception {
        //System.out.println(s);
        byte buf[] = s.getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        // queue the packet here
        queue.add(packet);
        
        //sock.send(packet);
    }
    
    public void acknowledged() {
        queue.remove(0);
    }

    public void run() {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                sendMessage(Conversation.username + "XXXSEPARATORXXX" + Conversation.cd.getText() + "XXXSEPARATORXXX" + Conversation.cd.getRecipient());
            } catch (IOException e) {
                //System.err.println(e);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
