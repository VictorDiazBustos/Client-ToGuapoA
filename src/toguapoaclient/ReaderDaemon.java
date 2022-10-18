package toguapoaclient;

public class ReaderDaemon extends Thread{
    ClientManager manager;
    public ReaderDaemon(ClientManager manager){
        this.manager = manager;
        setDaemon(true);
        start();
    }
    
    @Override
    public void run(){
        String errorMessage = "Server connection failed";
        String message;
        while(true){
            if(!(message = this.manager.read()).equals("")){
                System.out.println(message);
            }
        }
    }
}
