/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpconversation;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
    // try aes 256
    public Encryption() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
        keyGen.init(256);
        SecretKey key = keyGen.generateKey();
        
        for (int x : key.getEncoded()) {
            System.out.println(x + " ");
        }
    }

    byte[] encrypt(byte[] plainText, SecretKey key) throws Exception {
        byte[] nonceBytes = new byte[12];
        int counter = 5;
        Cipher cipher = Cipher.getInstance("ChaCha20");
        ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, counter);
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "ChaCha20");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
        byte[] cipherText = cipher.doFinal(plainText);

        return cipherText;
    }
}
