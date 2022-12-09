package compile;

import java.net.ServerSocket;
import java.net.Socket;
import socket.*;
import socket.MultiThread;

public class ServerMain {
    public static void main(String args[]) {
        try {
            ServerS ServerS = new ServerS();
            int port = 5000;
            ServerSocket server = ServerS.startServer(port);
            boolean oui = true;
            while(true){
                Socket client = server.accept();
                System.out.println("CLIENT ... ");
                MultiThread thread = new MultiThread(ServerS ,client, 999999999);
                thread.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
    }
}
