package Innlevering2.Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class ServerConnector {
    private Socket serverConnection;
    private String adress;
    private int port;

    public ServerConnector(String properties){
        try {
            Properties prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(properties);
            prop.load(fileInputStream);
            adress = prop.getProperty("adress");
            port = Integer.parseInt(prop.getProperty("port"));
            setConnection();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void setConnection(){
        try {
            serverConnection = new Socket(adress, port);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public Socket getServerConnection(){
        return serverConnection;
    }

}
