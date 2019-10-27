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

import java.net.*;

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
                    Conversation.cd.receive(received.substring(10, received.length()));
                } else if (received.equals("RECEIVED")) {
                    Conversation.time = java.lang.System.currentTimeMillis();
                    Conversation.connected = true;
                    Conversation.cd.lblStatus.setText(Conversation.bundle.getString("CONNECTED"));
                } else if (received.startsWith("ACK")) {
                    Conversation.sender.acknowledged();
                } else {
                    //receivedMsg += received + "\n";
                    String[] separated = received.split(" : ");
                    received = separated[0] + " : " + Encryption.decrypt(separated[1], Conversation.key);

                    boolean unintelligible = false;
                    if (Conversation.filterUnintelligible) {
                        // test if the message is on the same channel
                        char[] receivedChar = received.toCharArray();
                        for (char c : receivedChar) {
                            if ((int) c <= 8 || ((int) c >= 14 && (int) c <= 31)) {
                                unintelligible = true;
                            }
                        }
                    }

                    if (!unintelligible) {
                        Conversation.cd.receive(received);
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
