/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpconversation;

/**
 *
 * @author njche
 */
public class Connection implements Runnable {

    Connection() {
        
    }

    @Override
    public void run() {
        while (true) {
            try {
                UDPConversation.sender.sendMessage("USERCONNECTION:" + UDPConversation.username);
                //System.out.println((java.lang.System.currentTimeMillis() - UDPConversation.time) / 1000.0);
                if (java.lang.System.currentTimeMillis() - UDPConversation.time > 3000) {
                    UDPConversation.connected = false;
                    UDPConversation.cd.lblStatus.setText(UDPConversation.bundle.getString("CONNECTING"));
                } 
                Thread.sleep(1000);
            } catch (Exception ex) {
                
            }
        }
    }

}
