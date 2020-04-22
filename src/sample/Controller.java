package sample;

public class Controller {

    UdpBroadCastServer udpBroadCastServer;
    UdpConnector udpConnector;

    public void initialize(){ //this method is executed when our GUI is ready. Things that should go on in the beginning of your javafx application should go in the intialize method
       udpBroadCastServer = new UdpBroadCastServer(); //an instance of the udpbroadcastServer
        new Thread(udpBroadCastServer).start(); //start broadcastserver in thread

        udpConnector = new UdpConnector(this);
        new Thread(udpConnector).start();
    }

    //these method triggers when buttons are clicked:
    public void toggleButtonEchoServer(){
        System.out.println("toggle echo server");
    }

    public void toggleBroadcastBtn(){
        System.out.println("toggle broadcast");
    }

    public void clearTable(){
        System.out.println("clear Table");
    }

    public void handleMessage(){ //this is to handle messages
        //whatever is in this method will be executed by the echo-server instance

    }

}
