/*
 * The MIT License
 *
 * Copyright 2019 njche.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
