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
import javax.sound.sampled.*;

/**
 *
 * @author njche
 */
public class VoIP implements Runnable {

    private byte[] buffer;
    private int port;
    private InetAddress address;
    private TargetDataLine line;
    private DatagramPacket packet;
    private AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
    private float rate = 44100.0f;
    private int channels = 2;
    private int sampleSize = 16;
    private boolean bigEndian = true;
    private AudioFormat format = new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
    private DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

    public VoIP(InetAddress ia, int _port) {
        address = ia;
        port = _port;
    }

    @Override
    public void run() {
        
    }
    
    public static void start(InetAddress ia, int port) {
        VoIP voip = new VoIP(ia, port);
        Thread t = new Thread(voip);
        t.start();
    }

}
