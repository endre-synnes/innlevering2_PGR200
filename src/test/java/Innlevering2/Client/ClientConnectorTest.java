package Innlevering2.Client;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientConnectorTest {


    @Test
    public void ableToGetConnectionToServer() throws Exception {
        try {
            ClientConnector connector = new ClientConnector("src/main/resources/ServerProperties.properties");
            assertNotNull(connector.getClientConnection());
        }catch (Exception e){
            System.out.println("Could not connect, server has to be running fist");
        }

    }

}