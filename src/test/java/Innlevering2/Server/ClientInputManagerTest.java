package Innlevering2.Server;

import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.DatabaseReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientInputManagerTest {

    private TableObjectFromDB tableObjectFromDB;
    private DatabaseReader dbReader;
    private DatabaseConnector dbConnector;
    @Before
    public void setUp() throws Exception {
        dbConnector = new DatabaseConnector("src/main/resources/DatabaseProperties.properties");
        dbReader = new DatabaseReader(dbConnector);
    }

    @Test
    public void testIfObjectIsNullIfCommandNotRecognised() throws Exception {
        //Fungerer ikke enda
        ClientInputManager inputManager = new ClientInputManager(dbReader, tableObjectFromDB);
    }
}