
package udpconversation;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPConversation {

    public static void main(String args[]) {
        try {
            String host = null;
            args = new String[]{"108.61.182.134"};
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
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
