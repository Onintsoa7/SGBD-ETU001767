package socket;
import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import table.Table;
import uses.Functions;
import writing.Request;
/**
 * This class implements java socket client
 * @author pankaj
 *
 */
public class ClientS{
    Socket socket;
    String host;
    public Socket getSocket() {
        return socket;
    }

    public Socket connect(String host, int port) throws Exception {
        try {
            this.host = host;
            socket = new Socket(host, port);
            return socket;
        } catch (Exception e) {
            throw new Exception(e.getMessage());        
        }
    }

    public void sendRequest(String request, ObjectOutputStream objectOutputStream) throws Exception {
        try {
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Table getResponse(ObjectInputStream objectInputStream) throws Exception{
        try {
            Table response = (Table) objectInputStream.readObject();
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}