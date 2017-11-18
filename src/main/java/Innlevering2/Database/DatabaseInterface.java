package Innlevering2.Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseInterface {

    /**
     * Getting connection to Database.
     * @return connection to database
     * @throws SQLException could not get connection
     */
    public Connection getConnection() throws SQLException;


}
