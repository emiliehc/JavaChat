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
                if (received.startsWith("WHOSONLINE")) {
                    //System.out.println(received);
                    UDPConversation.cd.receive(received.substring(10, received.length()));
                } else if (!received.equals("RECEIVED")) {
                    //receivedMsg += received + "\n";
                    String[] separated = received.split(" : ");
                    received = separated[0] + " : " + Encryption.decrypt(separated[1], UDPConversation.key);
                    
                    boolean unintelligible = false;
                    if (UDPConversation.filterUnintelligible) {
                        // test if the message is on the same channel
                        char[] receivedChar = received.toCharArray();
                        for (char c : receivedChar) {
                            if ((int) c <= 8 || ((int) c >= 14 && (int) c <= 31)) {
                                unintelligible = true;
                            }
                        }
                    }

                    if (!unintelligible) {
                        UDPConversation.cd.receive(received);
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
