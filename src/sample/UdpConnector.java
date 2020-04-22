package sample;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpConnector implements Runnable { //this is gonna use the datagram socket
    private DatagramSocket socket;
    private int udpPort = 7000; //this is because i will listen on port 7000
    private int udpPortEcho; //this is because i will send on port 7007
    private Controller controller; //need an instance of my controller to be able to communicate
    private boolean receiveMessages = true; //tells whether or not we'll receive messages

    public UdpConnector(Controller controller){ //so when i make an instance of udpconnetor it takes the controller along so they can receive messages
        this.controller = controller; //my controller(l9) is set to whatever i get in the constructor (l12)
        setupSocket(); //to make sure this is done
    }

    public void setupSocket(){
        try {
            socket = new DatagramSocket(udpPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }



    public UdpMessage receiveMessage(){ //this is to be able to receive messages
        byte[] buffer = new byte[256]; //256 is the biggest amount of info u can have in one packet in udp
        //based on that i create a packet:
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(packet);
            UdpMessage udpMessage = new UdpMessage(packet.getData(), packet.getLength(), packet.getAddress(), packet.getPort());
            System.out.println(udpMessage);
            return udpMessage;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void run() {
        while(true){
            receiveMessage();
        }
    }
}
