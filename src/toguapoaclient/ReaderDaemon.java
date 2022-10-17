package toguapoaclient;

public class ReaderDaemon extends Thread{
    public ReaderDaemon(){
        setDaemon(true);
        start();
    }
    
    @Override
    public void run(){
        ClientManager manager = new ClientManager();
        String errorMessage = "Server connection failed";

        if(!manager.connect()){
            try {
                throw new ConnectException(errorMessage);
            } catch (ConnectException ex) {
                System.out.println(errorMessage);
            }
        }
        
        String message;
        while(true){
            if(!(message = manager.read()).equals("")){
                System.out.println(message);
            }
        }
    }
}
