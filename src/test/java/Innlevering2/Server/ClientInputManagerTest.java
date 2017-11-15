package Innlevering2.Server;

import Innlevering2.Database.DataPublisher;
import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.DatabaseReader;
import Innlevering2.Server.ServerSetup.DBCreator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientInputManagerTest {

    private TableObjectFromDB tableObjectFromDB;
    private static DatabaseReader dbReader;
    private static DatabaseConnector dbConnector;
    private ClientInputManager inputManager;

    @BeforeClass
    public static void setUpBefore() throws Exception{
        dbConnector = new DatabaseConnector("src/main/resources/DatabaseProperties.properties");
        DataPublisher publisher = new DataPublisher(dbConnector);
        DBCreator creator = new DBCreator(publisher);
        creator.run();
        dbReader = new DatabaseReader(dbConnector);
    }
    @Before
    public void setUp() throws Exception {
        tableObjectFromDB = new TableObjectFromDB();
        inputManager = new ClientInputManager(dbReader, tableObjectFromDB);
    }

    @Test
    public void testIfObjectIsNullIfCommandNotRecognised() throws Exception {
        String[] allTablesCommand = new String[1];
        allTablesCommand[0] = "1";
        tableObjectFromDB = inputManager.clientInputTranslator(allTablesCommand);

        assertNotNull(tableObjectFromDB);
        assertEquals(9, tableObjectFromDB.getContentOfTable().size());
    }

    @Test
    public void ableToGetOneTableByName() throws Exception {
        String[] getTableCommand = new String[2];
        getTableCommand[0] = "2";
        getTableCommand[1] = "room";

        tableObjectFromDB = inputManager.clientInputTranslator(getTableCommand);

        assertNotNull(tableObjectFromDB);
        assertEquals("room", tableObjectFromDB.getTableName());
    }

    @Test
    public void ableToGetRowWithValue() throws Exception {
        String[] getTableCommand = new String[4];
        getTableCommand[0] = "3";
        getTableCommand[1] = "room";
        getTableCommand[2] = "id";
        getTableCommand[3] = "F101";

        tableObjectFromDB = inputManager.clientInputTranslator(getTableCommand);

        assertEquals(1, tableObjectFromDB.getContentOfTable().size());
        assertEquals("F101", tableObjectFromDB.getContentOfTable().get(0)[0]);
    }


    @Test
    public void countRowInTable() throws Exception {
        String[] getTableCommand = new String[2];
        getTableCommand[0] = "4";
        getTableCommand[1] = "room";

        tableObjectFromDB = inputManager.clientInputTranslator(getTableCommand);

        assertEquals("7", tableObjectFromDB.getContentOfTable().get(0)[0]);
    }

    @Test
    public void testMetadata() throws Exception {
        String[] getTableCommand = new String[2];
        getTableCommand[0] = "5";
        getTableCommand[1] = "room";

        tableObjectFromDB = inputManager.clientInputTranslator(getTableCommand);

        assertEquals(3, tableObjectFromDB.getColumnName().length);
        assertEquals("Id", tableObjectFromDB.getColumnName()[0]);
    }
}