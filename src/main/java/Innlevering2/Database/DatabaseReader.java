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
     *
     * @param tableObjectFromDB
     * @return Populated table from Database
     * @throws SQLException
     */
    public TableObjectFromDB getAllTables(TableObjectFromDB tableObjectFromDB) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.
                     prepareStatement("SELECT Table_Name as TableName  " +
                             "FROM information_schema.tables " +
                             "where table_schema='pgr200innlevering1'")) {
            ResultSet result = statement.executeQuery();
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }
    }

    /**
     *
     * @param tableName
     * @param tableObjectFromDB
     * @return Populated table from Database
     * @throws Exception
     */
    public TableObjectFromDB getAllFromOneTable(String tableName, TableObjectFromDB tableObjectFromDB) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")) {
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @param parameter
     * @param tableObjectFromDB
     * @return Populated table from Database
     * @throws SQLException
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
     *
     * @param tableName
     * @param columnName
     * @param greaterOrLess
     * @param value
     * @param tableObjectFromDB
     * @return Populated table from Database
     * @throws SQLException
     */
    public TableObjectFromDB getLinesWithValuesGreaterOrLessThen(String tableName,
                                                      String columnName, String greaterOrLess,
                                                      String value, TableObjectFromDB tableObjectFromDB)
                                                      throws SQLException{
        try(Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("")){
            String sqlSyntax = "SELECT * FROM " + tableName + " WHERE " + columnName;
            if (greaterOrLess.equals("greater")){
                sqlSyntax += " > " + value + ";";

            }
            else if (greaterOrLess.equals("less")){
                sqlSyntax += " < " + value + ";";

            }
            else throw new IllegalArgumentException("Enter a valid input argument ('greater' or 'less')");
            ResultSet result = statement.executeQuery(sqlSyntax);
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }
    }

    /**
     *
     * @param tableName
     * @param tableObjectFromDB
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
     *
     * @param tableName
     * @param tableObjectFromDB
     * @return Populated table from Database
     * @throws SQLException
     * @throws NullPointerException
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
     *
     * @param result
     * @param tableObjectFromDB
     * @return Populated table from Database
     * @throws NullPointerException
     * @throws SQLException
     */
    private TableObjectFromDB setContentOfTableFromDB(ResultSet result, TableObjectFromDB tableObjectFromDB)
        throws NullPointerException, SQLException{
        try {
            int columnCount = result.getMetaData().getColumnCount();
            ArrayList<String[]> content = new ArrayList<>();

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
     *
     * @param result
     * @param tableObjectFromDB
     * @return Populated table from Database
     * @throws NullPointerException
     * @throws SQLException
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
     *
     * @param columnName
     * @return Formatted String
     */
    private String formatColumnName(String columnName){
        columnName = columnName.toLowerCase().replaceAll("_", " ");
        return columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
    }
}
