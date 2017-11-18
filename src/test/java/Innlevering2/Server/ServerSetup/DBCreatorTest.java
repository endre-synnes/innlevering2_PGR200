package Innlevering2.Server.ServerSetup;

import Innlevering2.Database.DataPublisher;
import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.DatabaseReader;

import static org.junit.Assert.*;

import Innlevering2.Server.TableObjectFromDB;
import org.junit.*;

import java.io.IOException;
import java.sql.SQLException;

public class DBCreatorTest {

    private static DatabaseConnector dbConn;
    private static DataPublisher dbPublisher;
    private DatabaseReader dbReader;

    @BeforeClass
    public static void setUp() throws IOException, SQLException {
        dbConn = new DatabaseConnector("src/main/resources/TestDatabaseProperties.properties");
        dbPublisher = new DataPublisher(dbConn);
    }

    @Before
    public void setup() throws Exception {
        dbReader = new DatabaseReader(dbConn);
    }


    @Test
    public void TestIfCreatorCanCreateTables() throws Exception {
        DBCreator creator = new DBCreator(dbPublisher);
        creator.run();
        TableObjectFromDB objectFromDB = new TableObjectFromDB();
        objectFromDB = dbReader.getAllTables(objectFromDB);
        assertEquals(9, objectFromDB.getContentOfTable().size());
    }

    @Test(expected = NullPointerException.class)
    public void ThrowsNullPointerExceptionWhenDataPublisherIsNull() throws Exception {
        DBCreator creator = new DBCreator(null);
        creator.run();
    }

    @After
    public void tearDown() throws Exception {
        dbReader = null;
    }

    @AfterClass
    public static void teardown() throws Exception{
        dbConn = null;
        dbPublisher = null;
    }

}