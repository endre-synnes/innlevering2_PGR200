package Innlevering2.Database;

import Innlevering2.Server.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataPublisher {
    private DatabaseConnector dbConnector;

    public DataPublisher(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Creating a table or overwrites it if the table already exists.
     * @param tableFromFile
     * @return String explaining if i succeeded.
     */
    public String createTableInDatabase(Table tableFromFile) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")){
            statement.execute("DROP TABLE IF EXISTS " + tableFromFile.getTableName());
            String sqlSyntax = "CREATE TABLE " + tableFromFile.getTableName() + " (";
            for (int i = 0; i < tableFromFile.getColumnNames().length; i++) {
                sqlSyntax += tableFromFile.getColumnNames()[i] + " "
                        + tableFromFile.getDataTypes()[i] + ",";
            }
            sqlSyntax += "PRIMARY KEY(" + tableFromFile.getPrimaryKey() + "));";

            statement.executeUpdate(sqlSyntax);
            return "Successfully created table!";
        } catch (SQLException e) {
            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());
        }


    }

    /**
     * Inserting data into a table if it exist
     * @param tableFromFile
     * @return String explaining if i succeeded.
     */
    public String insertDataToDatabase(Table tableFromFile) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(stringBuildingForInsertingDataToDatabase(tableFromFile))) {

            int index = 1;
            for (int i = 0; i < tableFromFile.getLinesAndColumnsFromFile().length; i++) {
                for (int j = 0; j < tableFromFile.getLinesAndColumnsFromFile()[i].length; j++) {
                    statement.setString(index++, tableFromFile.getLinesAndColumnsFromFile()[i][j]);
                }
            }
            int linesInserted = statement.executeUpdate();
            return "Successfully inserted " + linesInserted + " rows to table";
        } catch (SQLException e){
            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());
        }
    }

    private String stringBuildingForInsertingDataToDatabase(Table table){
        StringBuilder sqlString = new StringBuilder();
        sqlString.append("INSERT INTO ");
        sqlString.append(table.getTableName());
        sqlString.append("(");
        int startColumn = 0;
        if (table.checkForAutoIncrementInTable()) startColumn = 1;
        int columnCount = table.getColumnNames().length;

        for (int i = startColumn; i < columnCount; i++) {
            sqlString.append(table.getColumnNames()[i]);
            if (i+1 < columnCount){
                sqlString.append(",");
            }
        }
        sqlString.append(")\nVALUES\n(");
        for (int i = 0; i < table.getLinesAndColumnsFromFile().length; i++) {
            for (int j = 0; j < table.getLinesAndColumnsFromFile()[i].length - 1; j++) {
                sqlString.append("?" + ", ");
            }
            sqlString.append("?" + "),\n(");
        }
        return sqlString.toString().substring(0, sqlString.length() - 3);
    }


}
