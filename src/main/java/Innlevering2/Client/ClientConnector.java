package Innlevering2.Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class ClientConnector implements ClientConnectorInterface{
    private Socket clientConnection;
    private String address, port;

    /**
     * Reading properties file and calling method to set connection.
     * @param properties name of properties file
     * @throws IOException could not read file
     */
    public ClientConnector(String properties) throws IOException{
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(properties);
        prop.load(fileInputStream);
        address = prop.getProperty("adrdess");
        port = prop.getProperty("port");
        setClientConnection(address, port);
    }

    /**
     * Setting client connection to server.
     * @param address server address
     * @param port port number
     * @throws IOException unable to ser connection
     */
    public void setClientConnection(String address, String port) throws IOException{
        try {
            int portInt = Integer.parseInt(port);
            clientConnection = new Socket(address, portInt);
        }catch (UnknownHostException unknown){
            throw new UnknownHostException("Unknown server, check properties file.");
        }catch (IOException e){
            throw new IOException("Could not connect to server");
        }
    }

    /**
     * Getting client connection to server.
     * @return socket
     * @throws NullPointerException object not initialised
     */
    public Socket getClientConnection() throws NullPointerException{
        return clientConnection;
    }

}
