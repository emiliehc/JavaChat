/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpconversation;

import java.util.Arrays;
import java.util.Random;

public class Encryption {

    private static Random generator;

    public static String encrypt(String msg, String key) {
        if (!UDPConversation.legacyEncryption) {
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
        if (!UDPConversation.legacyEncryption) {
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
