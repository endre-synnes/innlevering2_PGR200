package Innlevering2.Database;

import Innlevering2.Server.ServerSetup.TableObjectFromFile;

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
     * @param tableObjectFromFileFromFile table object
     * @return String explaining if i succeeded.
     */
    public String createTableInDatabase(TableObjectFromFile tableObjectFromFileFromFile) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")){
            statement.execute("DROP TABLE IF EXISTS " + tableObjectFromFileFromFile.getTableName());
            String sqlSyntax = "CREATE TABLE " + tableObjectFromFileFromFile.getTableName() + " (";
            for (int i = 0; i < tableObjectFromFileFromFile.getColumnNames().length; i++) {
                sqlSyntax += tableObjectFromFileFromFile.getColumnNames()[i] + " "
                        + tableObjectFromFileFromFile.getDataTypes()[i] + ",";
            }
            sqlSyntax += "PRIMARY KEY(" + tableObjectFromFileFromFile.getPrimaryKey() + "));";

            statement.executeUpdate(sqlSyntax);
            return "Successfully created table!";
        }


    }

    /**
     * Inserting data into a table if it exist
     * @param tableObjectFromFileFromFile table object
     * @return String explaining if i succeeded.
     */
    public String insertDataToDatabase(TableObjectFromFile tableObjectFromFileFromFile) throws SQLException, NullPointerException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(buildingStringForInsertingDataToDatabase(tableObjectFromFileFromFile))) {

            int index = 1;
            for (int i = 0; i < tableObjectFromFileFromFile.getLinesAndColumnsFromFile().length; i++) {
                for (int j = 0; j < tableObjectFromFileFromFile.getLinesAndColumnsFromFile()[i].length; j++) {
                    statement.setString(index++, tableObjectFromFileFromFile.getLinesAndColumnsFromFile()[i][j]);
                }
            }
            int linesInserted = statement.executeUpdate();
            return "Successfully inserted " + linesInserted + " rows to table";
        }
    }

    private String buildingStringForInsertingDataToDatabase(TableObjectFromFile tableObjectFromFile) throws NullPointerException{
        StringBuilder sqlString = new StringBuilder();
        sqlString.append("INSERT INTO ");
        sqlString.append(tableObjectFromFile.getTableName());
        sqlString.append("(");
        int startColumn = 0;
        if (tableObjectFromFile.checkForAutoIncrementInTable()) startColumn = 1;
        int columnCount = tableObjectFromFile.getColumnNames().length;

        for (int i = startColumn; i < columnCount; i++) {
            sqlString.append(tableObjectFromFile.getColumnNames()[i]);
            if (i+1 < columnCount){
                sqlString.append(",");
            }
        }
        sqlString.append(")\nVALUES\n(");
        for (int i = 0; i < tableObjectFromFile.getLinesAndColumnsFromFile().length; i++) {
            for (int j = 0; j < tableObjectFromFile.getLinesAndColumnsFromFile()[i].length - 1; j++) {
                sqlString.append("?" + ", ");
            }
            sqlString.append("?" + "),\n(");
        }
        return sqlString.toString().substring(0, sqlString.length() - 3);
    }


}
