package Innlevering2.Server;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerConnectorInterface {

    void setServerSocket() throws IOException;

    ServerSocket getServerSocket() throws NullPointerException;
}
