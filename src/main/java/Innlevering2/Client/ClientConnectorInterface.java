package Innlevering2.Client;

import java.io.IOException;
import java.net.Socket;

public interface ClientConnectorInterface {

    void setClientConnection() throws IOException;

    Socket getClientConnection();
}
