package Innlevering2.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {
    private DatabaseConnector connector;

    public TableManager(DatabaseConnector connector){
        this.connector = connector;
    }

    /**
     * Deletes a table.
     * @param tableName
     * @return String explaining if i succeeded.
     */
    public String dropTable(String tableName){
        try (Connection connection = connector.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE " + tableName);
            return "Table " + tableName + " is dropped!";
        }catch (SQLException e){
            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());
        }
    }


}
