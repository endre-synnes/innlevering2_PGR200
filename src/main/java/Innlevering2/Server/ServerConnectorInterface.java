package Innlevering2.Server;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerConnectorInterface {

    void setServerSocket(String address, String port) throws IOException;

    ServerSocket getServerSocket() throws NullPointerException;
}
