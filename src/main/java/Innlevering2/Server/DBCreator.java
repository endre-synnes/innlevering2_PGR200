package Innlevering2.Server;

import Innlevering2.Database.DataPublisher;
import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.SQLExceptionHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class DBCreator {

    private DatabaseConnector dbConnector;


    public DBCreator(DatabaseConnector dbConnector){
        this.dbConnector = dbConnector;
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
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
    }


    private void populateTables(TableObjectFromFile tableFromFile) throws SQLException{
        DataPublisher publisher = new DataPublisher(dbConnector);
        publisher.createTableInDatabase(tableFromFile);
        publisher.insertDataToDatabase(tableFromFile);
    }

}
