package socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Timer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import socket.*;
public class MultiThread extends Thread {
    long timeout;
    Socket client;
    ServerS serverS;
    
    public long getTimeout() {
        return timeout;
    }

    public ServerS getServerS() {
        return serverS;
    }

    public void setServerS(ServerS serveS) {
        this.serverS = serveS;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public MultiThread(ServerS serverS ,Socket client, long timeout) {
        this.setTimeout(timeout);
        this.setClient(client);
        this.setServerS(serverS);
    }
    
    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            String clientRequest = serverS.getRequest(objectInputStream);
            System.out.println("Request: " + clientRequest);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                serverS.sendResponse(clientRequest, objectOutputStream);
                objectInputStream.close();
                objectOutputStream.close();
                client.close();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        
    }
}