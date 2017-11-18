package Innlevering2.Server;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Properties;

public class ServerConnector implements ServerConnectorInterface{

    private ServerSocket server;
    private String port, address;

    /**
     * reading properties file and calling method to set server socket.
     * @param properties file name
     * @throws IOException error while reading properties file
     */
    public ServerConnector(String properties) throws IOException{
        try {
            Properties prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(properties);
            prop.load(fileInputStream);
            port = prop.getProperty("port");
            address = prop.getProperty("address");
            setServerSocket(address, port);
        }catch (IOException e){
            throw new IOException();
        }

    }

    /**
     * Setting server socket.
     * @param address address
     * @param port port
     * @throws IOException error while setting connection
     */
    public void setServerSocket(String address, String port) throws IOException{
        try {
            int portNumber = Integer.parseInt(port);
            server = new ServerSocket(portNumber, 0, InetAddress.getByName(address));
        }catch (IOException e){
            throw new IOException("Could not set server socket, check properties file.");
        }
    }

    /**
     * Getting server socket.
     * @return server socket
     * @throws NullPointerException not initialised
     */
    public ServerSocket getServerSocket() throws NullPointerException{
        return server;
    }
}
