package compile;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import socket.*;
import table.Table;
import uses.Functions;
import writing.Request;
import javax.swing.JOptionPane;

import frames.Frame;
public class ClientMain {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            ClientS clientS = new ClientS(); 
            String Ip_addr = JOptionPane.showInputDialog("Enter the IP number of the server to connect: ");
            String host = Ip_addr;
            int port = 5000;
            Functions functions = new Functions();
            Socket socket = null;
            ObjectOutputStream objectOutputStream = null;
            ObjectInputStream objectInputStream = null;
            int done = 0;
            boolean a = true;
            while(done != 1) {
                socket = clientS.connect(host, port);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                System.out.print("1767SQL>");
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
                String request = bufferedreader.readLine();
                if(request.equalsIgnoreCase("exit") == true){
                    System.out.println("See you next time ...");
                    socket.close();
                    break;
                }
                clientS.sendRequest(request, objectOutputStream);
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Table response =(Table) clientS.getResponse(objectInputStream);
                    Functions affichage = new Functions();
                    affichage.showDatas(response);
                    objectInputStream.close();
                    objectOutputStream.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
