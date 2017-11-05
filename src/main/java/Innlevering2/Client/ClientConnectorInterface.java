package Innlevering2.Client;

import java.io.IOException;
import java.net.Socket;

public interface ClientConnectorInterface {

    void setClientConnection(String address, String port) throws IOException;

    Socket getClientConnection() throws NullPointerException;
}
