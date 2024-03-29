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

import gui.ChatDialog;
import gui.Welcome;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import javax.swing.JOptionPane;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Conversation {

    // Preferences
    public static boolean filterUnintelligible = true;
    public static boolean legacyEncryption = false;
    public static String host = null;
    public static ResourceBundle bundle;

    // Declarations
    public static gui.ChatDialog cd;
    public static String username;
    public static MessageSender sender;
    public static MessageReceiver receiver;
    public static String key;
    public static long time = 0;
    public static boolean connected = false;

    public static void main(String args[]) {
        // use the top menu bar on mac os devices
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        // read preference file
        // create one if it does not exist
        File preferences = new File("preferences.pref");
        if (!preferences.exists()) {
            try {
                preferences.createNewFile();
                FileWriter fw = new FileWriter(preferences);
                fw.write("en-US");
                fw.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        String locale = "en-US";
        try {
            try ( // get user locale preferences
                    Scanner sc = new Scanner(preferences)) {
                locale = sc.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        
        bundle = ResourceBundle.getBundle("resources." + locale);
        cd = new gui.ChatDialog();
        host = JOptionPane.showInputDialog(null, bundle.getString("ENTER_IP"), "chat.nanjingchj.me");
        username = JOptionPane.showInputDialog(bundle.getString("ENTER_NAME"));
        key = JOptionPane.showInputDialog(bundle.getString("ENTER_CODE"));
        Welcome w = new Welcome();
        w.pack();
        w.setVisible(true);
        try {
            //String host = null;
            if (args.length >= 1) {
                host = args[0];
            }
            DatagramSocket socket = new DatagramSocket();
            receiver = new MessageReceiver(socket);
            sender = new MessageSender(socket, host);
            Connection connection = new Connection();
            Thread receivingThread = new Thread(receiver);
            Thread sendingThread = new Thread(sender);
            Thread connectionThread = new Thread(connection);
            receivingThread.start();
            sendingThread.start();
            connectionThread.start();
            
            while (!connected) {
                Thread.sleep(50);
            }
            w.dispose();
            // start the gui once the server has been connected
            cd.pack();
            cd.setVisible(true);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void Relaunch(String localeName) {
        cd.dispose();
        bundle = ResourceBundle.getBundle("Resources." + localeName);
        cd = new ChatDialog();
        cd.pack();
        cd.setVisible(true);
    }
    
    public static String getVersion() {
        return "2.1.0";
    }
}
