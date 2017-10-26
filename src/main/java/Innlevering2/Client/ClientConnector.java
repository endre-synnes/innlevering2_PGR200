package Innlevering2.Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class ClientConnector implements ClientConnectorInterface{
    private Socket clientConnection;
    private String address;
    private int port;

    public ClientConnector(String properties) throws IOException{
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(properties);
        prop.load(fileInputStream);
        address = prop.getProperty("adrdess");
        port = Integer.parseInt(prop.getProperty("port"));
        setClientConnection();
    }

    public void setClientConnection() throws IOException{
        try {
            clientConnection = new Socket(address, port);
        }catch (UnknownHostException unknown){
            throw new UnknownHostException("Unknown server, check properties file.");
        }catch (IOException e){
            throw new IOException("Could not connect to server");
        }
    }

    public Socket getClientConnection() throws NullPointerException{
        return clientConnection;
    }

}
