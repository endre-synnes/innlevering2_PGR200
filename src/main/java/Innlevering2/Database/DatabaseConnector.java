package Innlevering2.Database;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnector implements DatabaseInterface{
    private static String hostName;
    private static String dbName;
    private static String userName;
    private static String password;
    private static int port;
    private MysqlDataSource dataSource;

    /**
     * Reads the property file
     */
    public DatabaseConnector(String properties) {
        try {
            Properties prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(properties);
            prop.load(fileInputStream);
            hostName = prop.getProperty("hostName");
            dbName = prop.getProperty("dbName");
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
            port = Integer.parseInt(prop.getProperty("port"));
            dropDatabaseIfExist(getConnection());
        }catch (Exception e){
            System.out.println("Could not createTableObject property file correctly!");
        }
    }


    /**
     *
     * @return connections to server
     */
    @Override
    public Connection getConnection(){
        try {
            dataSource = new MysqlDataSource();
            dataSource.setServerName(hostName);
            dataSource.setUser(userName);
            dataSource.setPassword(password);
            dataSource.setPort(port);
            createAndSetDatabase(dataSource.getConnection());
            return dataSource.getConnection();
        }catch (SQLException dbException){
            System.out.println("Could not connect to server, please check the DB Name file or your username/password!");
            System.exit(0);
            return null;
        }
    }

    /**
     * Creats and set the database name
     * @param connection
     */
    private void createAndSetDatabase(Connection connection){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            dataSource.setDatabaseName(dbName);
        }catch (SQLException e){
            System.out.println("Could not set or create Database");
        }
    }

    private void dropDatabaseIfExist (Connection connection){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE IF EXISTS " + dbName);
        }catch (SQLException e){
            System.out.println("Failed to drop Database");
        }
    }

}