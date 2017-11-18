package Innlevering2.Client;

import java.io.IOException;
import java.net.Socket;

public interface ClientConnectorInterface {

    /**
     * Set Connection
     * @param address
     * @param port
     * @throws IOException
     */
    void setClientConnection(String address, String port) throws IOException;

    /**
     * Get Connection
     * @return
     * @throws NullPointerException
     */
    Socket getClientConnection() throws NullPointerException;
}
