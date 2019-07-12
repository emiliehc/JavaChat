/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpconversation;

import java.util.Arrays;


public class Encryption {
    public static String encrypt(String msg, String key) {
        char[] msgChar = msg.toCharArray();
        char[] keyChar = key.toCharArray();
        String output = "";
        int keyIndex = 0;
        for (int i = 0; i < msgChar.length; i++) {
            if (keyIndex >= keyChar.length) {
                keyIndex = 0;
            }
            int msgCharInt = (int)msgChar[i];
            msgCharInt += (int)keyChar[keyIndex];
            if (msgCharInt > 127) {
                msgCharInt -= 127;
            }
            //System.out.println(msgCharInt);
            output += Character.toString((char)msgCharInt);
            keyIndex++;
        }
        return output;
    }
    
    public static String decrypt(String msg, String key) {
        char[] encryptedChar = msg.toCharArray();
        char[] keyChar = key.toCharArray();
        int keyIndex = 0;
        String output = "";
        for (int i = 0; i < encryptedChar.length; i++) {
            if (keyIndex >= keyChar.length) {
                keyIndex = 0;
            }
            int msgCharInt = (int)encryptedChar[i];
            msgCharInt -= (int)keyChar[keyIndex];
            if (msgCharInt < 0) {
                msgCharInt += 127;
            }
            //System.out.println(msgCharInt);
            output += Character.toString((char)msgCharInt);
            keyIndex++;
        }
        return output;
    }
}
