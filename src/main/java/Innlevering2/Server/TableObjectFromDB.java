package Innlevering2.Server;

import java.io.Serializable;
import java.util.ArrayList;

public class TableObjectFromDB implements Serializable{
    private String tableName;
    private String[] columnName, columnDisplaySize, columnTypeName, tablesInDB;
    private ArrayList<String[]> contentOfTable;

    public TableObjectFromDB(){
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public String[] getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(String[] columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public ArrayList<String[]> getContentOfTable() {
        return contentOfTable;
    }

    public void setContentOfTable(ArrayList<String[]> contentOfTable) {
        this.contentOfTable = contentOfTable;

    }

    public String[] getTablesInDB() {
        return tablesInDB;
    }

    public void setTablesInDB(String[] tablesInDB) {
        this.tablesInDB = tablesInDB;
    }

    public String[] getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String[] columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

}
