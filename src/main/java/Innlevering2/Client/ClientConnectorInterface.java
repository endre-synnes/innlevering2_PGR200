package Innlevering2.Client;

import java.io.IOException;
import java.net.Socket;

public interface ClientConnectorInterface {

    /**
     * Set Connection
     * @param address adrresss
     * @param port port number
     * @throws IOException not able to set socket
     */
    void setClientConnection(String address, String port) throws IOException;

    /**
     * Get Connection
     * @return socket
     * @throws NullPointerException object not initialised
     */
    Socket getClientConnection() throws NullPointerException;
}
