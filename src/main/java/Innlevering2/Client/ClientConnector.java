package Innlevering2.Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class ClientConnector {
    private Socket ClientConnection;
    private String address;
    private int port;

    public ClientConnector(String properties){
        try {
            Properties prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(properties);
            prop.load(fileInputStream);
            address = prop.getProperty("adrdess");
            port = Integer.parseInt(prop.getProperty("port"));
            setConnection();
        }catch (UnknownHostException e){
            System.out.println("Unknown server");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setConnection(){
        try {
            ClientConnection = new Socket(address, port);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public Socket getClientConnection(){
        return ClientConnection;
    }

}
