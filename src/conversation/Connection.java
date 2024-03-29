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
