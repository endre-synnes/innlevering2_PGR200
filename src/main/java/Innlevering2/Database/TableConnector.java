package Innlevering2.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableConnector {
    private DatabaseConnector connector;

    public TableConnector(DatabaseConnector connector){
        this.connector = connector;
    }

    /**
     * Creats an foreign key constraint and connects two tables.
     * @param mainTable
     * @param secondTable
     * @param mainTableColumnName
     * @param secondTableColumnName
     * @return String explaining if i succeeded.
     */
    public String addConstraintToTwoTables(
            String mainTable, String secondTable, String mainTableColumnName, String secondTableColumnName){

        try (Connection connection = connector.getConnection()){
            String constraintText = "fk_" + mainTable + "_" + mainTableColumnName + "_" + secondTableColumnName;
            Statement statement = connection.createStatement();
            statement.executeUpdate("ALTER TABLE `" + mainTable + "`" +
                    "  ADD CONSTRAINT `" + constraintText + "`" +
                    " FOREIGN KEY (`" + mainTableColumnName +"`)" +
                    " REFERENCES `" + secondTable +"` (`" + secondTableColumnName +"`);");
            return "successfully connected " + mainTable + " and " + secondTable;

        }catch (SQLException e){
            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());

        }

    }

}
