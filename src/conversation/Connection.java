package conversation;

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
                Conversation.sender.sendMessage("USERCONNECTION:" + Conversation.username);
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
