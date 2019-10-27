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

import java.util.Random;

public class Encryption {

    private static Random generator;

    public static String encrypt(String msg, String key) {
        if (!Conversation.legacyEncryption) {
            // shift the key around
            long seed = 1;
            for (char c : key.toCharArray()) {
                seed *= (int) c + 1;
            }
            generator = new Random(seed * msg.length());
            key = "";
            for (int i = 0; i < msg.length(); i++) {
                int num = (int) (generator.nextDouble() * 95) + 32;
                key += Character.toString((char) num);
            }
        }

        char[] msgChar = msg.toCharArray();
        char[] keyChar = key.toCharArray();
        String output = "";
        int keyIndex = 0;
        for (int i = 0; i < msgChar.length; i++) {
            if (keyIndex >= keyChar.length) {
                keyIndex = 0;
            }
            int msgCharInt = (int) msgChar[i];
            msgCharInt += (int) keyChar[keyIndex];
            if (msgCharInt > 127) {
                msgCharInt -= 127;
            }
            //System.out.println(msgCharInt);
            output += Character.toString((char) msgCharInt);
            keyIndex++;
        }
        return output;
    }

    public static String decrypt(String msg, String key) {
        if (!Conversation.legacyEncryption) {
            // shift the key around
            long seed = 1;
            for (char c : key.toCharArray()) {
                seed *= (int) c + 1;
            }
            generator = new Random(seed * msg.length());
            key = "";
            for (int i = 0; i < msg.length(); i++) {
                int num = (int) (generator.nextDouble() * 95) + 32;
                key += Character.toString((char) num);
            }
        }

        char[] encryptedChar = msg.toCharArray();
        char[] keyChar = key.toCharArray();
        int keyIndex = 0;
        String output = "";
        for (int i = 0; i < encryptedChar.length; i++) {
            if (keyIndex >= keyChar.length) {
                keyIndex = 0;
            }
            int msgCharInt = (int) encryptedChar[i];
            msgCharInt -= (int) keyChar[keyIndex];
            if (msgCharInt < 0) {
                msgCharInt += 127;
            }
            //System.out.println(msgCharInt);
            output += Character.toString((char) msgCharInt);
            keyIndex++;
        }
        return output;
    }
}
