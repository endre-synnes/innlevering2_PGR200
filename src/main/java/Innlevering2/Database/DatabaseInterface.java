package Innlevering2.Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseInterface {

    /**
     * Getting connection to Database.
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException;


}
