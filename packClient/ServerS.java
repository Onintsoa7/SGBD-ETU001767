package socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import writing.Request;
import table.Table;
import socket.*;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class ServerS {

    private ServerSocket server;
    private int port;

    public ServerSocket getServer() {
        return server;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket startServer(int port) throws Exception {
        try {
            this.server = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    return server;
    }

    public String getRequest(ObjectInputStream objectInputStream) throws Exception {
        String request = "";
        try {
            request = (String) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " getRequest dans ServerS");
        } 
    return request;
    }

    Table getResponseToSend(String request) throws Exception {
        Table reponse = new Table();
        try {
            Request request2 = new Request();
            reponse = request2.chooseRequest(request);
            return reponse;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " getResponseToSend dans ServerS");
        }
        return reponse;
       
    }

    public void sendResponse(String request, ObjectOutputStream objectOutputStream) throws Exception {
        try {
            Table response = getResponseToSend(request);
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " sendResponse dans ServerS");
        }
        
    }
    
    
    
}