package Innlevering2.Server;

import Innlevering2.Client.ClientApplication;
import Innlevering2.Client.ClientConnector;
import Innlevering2.Database.DataPublisher;
import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.DatabaseReader;
import Innlevering2.Server.ServerSetup.DBCreator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServerTest {

    private static DatabaseReader dbReader;
    private static DatabaseConnector dbConn;
    private static ServerConnector serverConn;
    private static ClientConnector clientConnector;


    @BeforeClass
    public static void before() throws IOException, SQLException {
        dbConn = new DatabaseConnector("src/main/resources/DatabaseProperties.properties");
        dbReader = new DatabaseReader(dbConn);
        DataPublisher publisher = new DataPublisher(dbConn);
        DBCreator creator = new DBCreator(publisher);
        creator.run();
        serverConn = new ServerConnector("src/main/resources/ServerProperties.properties");
        clientConnector = new ClientConnector("src/main/resources/ServerProperties.properties");

    }

//    @Test
//    public void isListOfThreadsGettingBiggerForMoreClientsConnected() throws Exception {
//        Server server = new Server(serverConn, dbReader);
//        server.runServer();
//
//        //Adding two clients
//        ClientApplication c1 = new ClientApplication(clientConnector);
//        c1.inputAndOutputFromServer();
//
//        int threads = server.getListOfThreads().size();
//
//        server = null;
//
//        assertEquals(1, threads);
//    }
}