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


    public TableObjectFromDB clientInputTranslator(String[] parameters) throws Exception{
        switch (parameters[0]){
            case "1" : getAllTables();
            case "2" : getOneTable(parameters[1]);
            default: return dbTable;

        }
    }

    private void getOneTable(String tableName) throws Exception {
        dbTable = dbReader.getAllFromOneTable(tableName, dbTable);
    }

    private void getAllTables() throws SQLException{
        dbTable = dbReader.getAllTables(dbTable);
    }


}
