package toguapoaclient;

import java.util.Date;

public class ReaderDaemon extends Thread{
    ClientManager manager;
    boolean exit;
    boolean showTime;
    Date date;

    public ReaderDaemon(ClientManager manager, boolean showTime){
        this.manager = manager;
        this.showTime = showTime;
        this.exit = false;
        this.date = new Date();
        setDaemon(true);
        start();
    }
    
    @Override
    public void run(){
        String errorMessage = "Server connection failed";
        String message;
        while(!this.exit){
            if(!(message = this.manager.read()).equals("")){
                if (showTime){
                    System.out.print("[" + this.date + "]: ");
                }
                System.out.println(message);
            }
        }
    }
    
    /**
     * Stop the daemon process
     */
    public void stopProcess(){
        this.exit = true;
    }

    public void setShowTime (boolean showTime){
        this.showTime = showTime;
    }

}
