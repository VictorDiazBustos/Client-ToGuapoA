package toguapoaclient;

public class ReaderDaemon extends Thread{
    ClientManager manager;
    boolean exit;

    public ReaderDaemon(ClientManager manager){
        this.manager = manager;
        this.exit = false;
        setDaemon(true);
        start();
    }
    
    @Override
    public void run(){
        String errorMessage = "Server connection failed";
        String message;
        while(!this.exit){
            if(!(message = this.manager.read()).equals("")){
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

}
