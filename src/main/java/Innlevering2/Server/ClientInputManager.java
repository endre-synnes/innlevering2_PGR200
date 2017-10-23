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

    private void getMetadataFromTable(String tableName) throws SQLException{
        dbTable = dbReader.getMetaDataFromTable(tableName, dbTable);
    }

    private void countRowsInTable(String tableName) throws SQLException{
        dbTable = dbReader.countRowsInTable(tableName, dbTable);
    }

    private void getLinesWithParameter(String tableName, String columnName, String value) throws SQLException{
        dbTable = dbReader.getLinesThatHasOneParameter(tableName, columnName, value, dbTable);
    }

    private void getOneTable(String tableName) throws SQLException {
        dbTable = dbReader.getAllFromOneTable(tableName, dbTable);
    }

    private void getAllTables() throws SQLException{
        dbTable = dbReader.getAllTables(dbTable);
    }


}
