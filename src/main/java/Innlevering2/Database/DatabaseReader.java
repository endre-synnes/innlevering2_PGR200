package Innlevering2.Database;

import Innlevering2.Server.TableObjectFromDB;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseReader{
    private DatabaseConnector dbConnector;

    public DatabaseReader(DatabaseConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    /**
     *Getting al tables from Database
     * @param tableObjectFromDB table object
     * @return Populated table from Database
     * @throws SQLException sqlexception
     */
    public TableObjectFromDB getAllTables(TableObjectFromDB tableObjectFromDB) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.
                     prepareStatement("SELECT Table_Name as TableName  " +
                             "FROM information_schema.tables " +
                             "where table_schema='" + connection.getCatalog() +"'")) {
            ResultSet result = statement.executeQuery();
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }
    }

    /**
     * Getting all content of one table
     * @param tableName table name
     * @param tableObjectFromDB table object
     * @return Populated table from Database
     * @throws SQLException sqlexception
     */
    public TableObjectFromDB getAllFromOneTable(String tableName, TableObjectFromDB tableObjectFromDB) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")) {
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }
    }

    /**
     * Getting rows with specific column value.
     * @param tableName table name
     * @param columnName column name
     * @param parameter column value
     * @param tableObjectFromDB table object
     * @return Populated table from Database
     * @throws SQLException SQLException
     */
    public TableObjectFromDB getLinesThatHasOneParameter(String tableName, String columnName,
                                              String parameter, TableObjectFromDB tableObjectFromDB) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("")){
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName
            + " WHERE " + columnName + " LIKE '" + parameter + "';");
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }
    }


    /**
     * Getting number of rows in a table
     * @param tableName table name
     * @param tableObjectFromDB table object
     * @return Populated table from Database
     * @throws SQLException
     */
    public TableObjectFromDB countRowsInTable(String tableName, TableObjectFromDB tableObjectFromDB) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select count(*) as rows from " + tableName)) {
            ResultSet result = statement.executeQuery();
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }
    }

    /**
     *Getting metadata from one table
     * @param tableName table name
     * @param tableObjectFromDB table object
     * @return Populated table from Database
     * @throws SQLException SQLException
     * @throws NullPointerException not initialised
     */
    public TableObjectFromDB getMetaDataFromTable(String tableName, TableObjectFromDB tableObjectFromDB)
            throws SQLException, NullPointerException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName)) {
            ResultSet result = statement.executeQuery();
            setTableMetadata(result, tableObjectFromDB);
            return tableObjectFromDB;
        }catch (NullPointerException e){
            throw new NullPointerException("Table object was not initialised");
        }
    }

    /**
     * Setting content of table object.
     * @param result result set
     * @param tableObjectFromDB table object
     * @return Populated table from Database
     * @throws NullPointerException not initialised
     * @throws SQLException SQLException
     */
    private TableObjectFromDB setContentOfTableFromDB(ResultSet result, TableObjectFromDB tableObjectFromDB)
        throws NullPointerException, SQLException{
        try {
            int columnCount = result.getMetaData().getColumnCount();
            ArrayList<String[]> content = new ArrayList<>();

            tableObjectFromDB.setTableName(result.getMetaData().getTableName(1));
            while (result.next()){
                String[] line = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    line[i] = result.getString(i + 1);
                }
                content.add(line);
            }
            tableObjectFromDB.setContentOfTable(content);
            return setTableMetadata(result, tableObjectFromDB);

        }catch (NullPointerException e){
            throw new NullPointerException();
        } catch (SQLException e){
            throw new SQLException();
        }

    }

    /**
     * Setting metadata of an table object
     * @param result result set
     * @param tableObjectFromDB table object
     * @return Populated table from Database
     * @throws NullPointerException not initalised
     * @throws SQLException sqlException
     */
    private TableObjectFromDB setTableMetadata(ResultSet result, TableObjectFromDB tableObjectFromDB)
            throws NullPointerException, SQLException {
        try {
            ResultSetMetaData metadata = result.getMetaData();
            int columnCount = metadata.getColumnCount();

            String[] columnNames = new String[columnCount];
            String[] columnDisplaySize = new String[columnCount];
            String[] columnTypeName = new String[columnCount];

            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = formatColumnName(metadata.getColumnName(i + 1));
                columnDisplaySize[i] = String.valueOf(metadata.getColumnDisplaySize(i + 1));
                columnTypeName[i] = metadata.getColumnTypeName(i + 1);
            }
            tableObjectFromDB.setColumnName(columnNames);
            tableObjectFromDB.setColumnDisplaySize(columnDisplaySize);
            tableObjectFromDB.setColumnTypeName(columnTypeName);
            return tableObjectFromDB;
        }catch (NullPointerException e){
            throw new NullPointerException();
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    /**
     * Removing underscore form column name if necessary.
     * @param columnName column name
     * @return Formatted String
     */
    private String formatColumnName(String columnName) throws NullPointerException{
        columnName = columnName.toLowerCase().replaceAll("_", " ");
        return columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
    }
}
