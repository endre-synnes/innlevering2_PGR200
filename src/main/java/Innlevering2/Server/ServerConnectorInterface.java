package Innlevering2.Server;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerConnectorInterface {

    /**
     * Setting server soket
     * @param address
     * @param port
     * @throws IOException
     */
    void setServerSocket(String address, String port) throws IOException;

    /**
     * Getting server socket
     * @return
     * @throws NullPointerException
     */
    ServerSocket getServerSocket() throws NullPointerException;
}
