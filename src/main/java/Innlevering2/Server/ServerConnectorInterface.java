package Innlevering2.Server;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerConnectorInterface {

    /**
     * Setting server soket
     * @param address address
     * @param port port
     * @throws IOException error while setting connection
     */
    void setServerSocket(String address, String port) throws IOException;

    /**
     * Getting server socket
     * @return server socket
     * @throws NullPointerException not initialised
     */
    ServerSocket getServerSocket() throws NullPointerException;
}
