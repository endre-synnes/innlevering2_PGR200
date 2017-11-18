package Innlevering2.Server.ServerSetup;

import Innlevering2.Database.DataPublisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBCreator {

    private DataPublisher dbPublisher;
    private ArrayList<TableObjectFromFile> tableObjects;

    public DBCreator(DataPublisher dbPublisher){
        this.dbPublisher = dbPublisher;
    }

    /**
     * Calling method to read files and then calling method to populate them into the Database
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws NullPointerException
     */
    public void run() throws SQLException, FileNotFoundException, NullPointerException{
        try {
            readFiles();
            if (tableObjects != null){
                for (TableObjectFromFile object : tableObjects) {
                    populateTables(object);
                }
            }
        }catch (SQLException e){
            throw new SQLException();
        }catch (FileNotFoundException noFile){
            throw new FileNotFoundException("No file with that name found in directory!");
        }catch (NullPointerException nul){
            throw new NullPointerException("Object Not initialised");
        }
    }

    /**
     * Reading files
     * @throws NullPointerException
     * @throws FileNotFoundException
     * @throws SQLException
     */
    private void readFiles() throws NullPointerException, FileNotFoundException, SQLException {
        File dir = new File("docs/files/");
        File[] directoryListing = dir.listFiles();
        tableObjects = new ArrayList<>();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                FileReader reader = new FileReader();
                TableObjectFromFile tableFromFile = new TableObjectFromFile();
                tableObjects.add(reader.createTableObject(child.getName(), tableFromFile));
            }
        }
    }

    /**
     * Creates and insert into tables
     * @param tableFromFile
     * @throws SQLException
     */
    private void populateTables(TableObjectFromFile tableFromFile) throws SQLException{
        dbPublisher.createTableInDatabase(tableFromFile);
        dbPublisher.insertDataToDatabase(tableFromFile);
    }

}
