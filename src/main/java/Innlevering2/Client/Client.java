package Innlevering2.Client;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private ServerConnector serverConnector;
    public Client(){
        this.serverConnector = new ServerConnector("properties.properties");
    }


    public void inputAndOutputFromServer(){
        try (Socket connection = serverConnector.getServerConnection()){

        }catch (IOException e){

        }
    }
}
