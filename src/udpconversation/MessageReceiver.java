/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpconversation;

import java.net.*;
import java.util.Arrays;

public class MessageReceiver implements Runnable {

    DatagramSocket sock;
    byte buf[];
    String receivedMsg = "";

    MessageReceiver(DatagramSocket s) {
        sock = s;
        buf = new byte[1024];
    }

    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                if (!received.equals("RECEIVED")) {
                    //receivedMsg += received + "\n";
                    String[] separated = received.split(" : ");
                    received = separated[0] + " : " + Encryption.decrypt(separated[1], UDPConversation.key);
                    //System.out.println(Arrays.toString(separated));
                    UDPConversation.cd.receive(received);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
