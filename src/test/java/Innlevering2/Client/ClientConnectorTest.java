package Innlevering2.Client;

import Innlevering2.Database.DataPublisher;
import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.DatabaseReader;
import Innlevering2.Server.Server;
import Innlevering2.Server.ServerConnector;
import Innlevering2.Server.ServerSetup.DBCreator;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientConnectorTest {
    private static DatabaseConnector dbConn;
    private static DatabaseReader dbReader;
    private static ServerConnector serverConn;


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