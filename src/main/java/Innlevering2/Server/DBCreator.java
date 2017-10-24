package Innlevering2.Server;

import Innlevering2.Database.DataPublisher;
import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.SQLExceptionHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class DBCreator {

    //private DatabaseConnector dbConnector;
    private DataPublisher dbPublisher;

    public DBCreator(DataPublisher dbPublisher){
        this.dbPublisher = dbPublisher;
    }

    public void run() throws SQLException, FileNotFoundException, NullPointerException{
        try {
            readFiles();
        }catch (SQLException e){
            throw new SQLException();
        }catch (FileNotFoundException noFile){
            throw new FileNotFoundException("No file with that name found in directory!");
        }catch (NullPointerException nul){
            throw new NullPointerException("Object Not initialised");
        }
    }


    private void readFiles() throws NullPointerException, FileNotFoundException, SQLException {
        File dir = new File("docs/files/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                FileReader reader = new FileReader();
                TableObjectFromFile tableFromFile = new TableObjectFromFile();
                tableFromFile = reader.createTableObject(child.getName(), tableFromFile);
                populateTables(tableFromFile);
            }
        }
    }


    private void populateTables(TableObjectFromFile tableFromFile) throws SQLException{
        dbPublisher.createTableInDatabase(tableFromFile);
        dbPublisher.insertDataToDatabase(tableFromFile);
    }

}
