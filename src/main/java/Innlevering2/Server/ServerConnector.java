package Innlevering2.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Properties;

public class ServerConnector implements ServerConnectorInterface{

    private ServerSocket server;
    private String port;

    public ServerConnector(String properties) {
        try {
            Properties prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(properties);
            prop.load(fileInputStream);
            port = prop.getProperty("port");
            setServerSocket();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setServerSocket() throws IOException{
        try {
            int portNumber = Integer.parseInt(port);
            server = new ServerSocket(portNumber, 0, InetAddress.getByName("localhost"));
        }catch (IOException e){
            throw new IOException("Could not set server socket, check properties file.");
        }
    }


    public ServerSocket getServerSocket() throws NullPointerException{
        return server;
    }
}
