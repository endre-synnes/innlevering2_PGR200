package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientInputManager {

    private DatabaseReader dbReader;
    private TableObjectFromDB dbTable;

    public ClientInputManager(DatabaseReader dbReader, TableObjectFromDB dbTable){
        this.dbReader = dbReader;
        this.dbTable = dbTable;
    }

    /**
     * Getting table object from database based on client command.
     * @param parameters
     * @return
     * @throws SQLException
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    public TableObjectFromDB clientInputTranslator(String[] parameters) throws SQLException, NullPointerException, IndexOutOfBoundsException{
        switch (parameters[0]){
            case "1" : getAllTables();
                break;
            case "2" : getOneTable(parameters[1]);
                break;
            case "3" : getLinesWithParameter(parameters[1], parameters[2], parameters[3]);
                break;
            case "4" : countRowsInTable(parameters[1]);
                break;
            case "5" : getMetadataFromTable(parameters[1]);
                break;
            default: return dbTable;
        }
        return dbTable;
    }

    /**
     * Getting metadata
     * @param tableName
     * @throws SQLException
     */
    private void getMetadataFromTable(String tableName) throws SQLException{
        dbTable = dbReader.getMetaDataFromTable(tableName, dbTable);
    }

    /**
     * getting number of rows
     * @param tableName
     * @throws SQLException
     */
    private void countRowsInTable(String tableName) throws SQLException{
        dbTable = dbReader.countRowsInTable(tableName, dbTable);
    }

    /**
     * Getting rows with column value
     * @param tableName
     * @param columnName
     * @param value
     * @throws SQLException
     */
    private void getLinesWithParameter(String tableName, String columnName, String value) throws SQLException{
        dbTable = dbReader.getLinesThatHasOneParameter(tableName, columnName, value, dbTable);
    }

    /**
     * Getting table
     * @param tableName
     * @throws SQLException
     */
    private void getOneTable(String tableName) throws SQLException {
        dbTable = dbReader.getAllFromOneTable(tableName, dbTable);
    }

    /**
     * Getting al tables
     * @throws SQLException
     */
    private void getAllTables() throws SQLException{
        dbTable = dbReader.getAllTables(dbTable);
    }


}
