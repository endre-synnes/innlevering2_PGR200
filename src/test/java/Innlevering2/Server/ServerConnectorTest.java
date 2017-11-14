package Innlevering2.Server;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerConnectorTest {


    @Test
    public void ableToGetConnectionToServer() throws Exception {
        ServerConnector connector = new ServerConnector("src/main/resources/ServerProperties.properties");
        assertNotNull(connector.getServerSocket());
    }
}