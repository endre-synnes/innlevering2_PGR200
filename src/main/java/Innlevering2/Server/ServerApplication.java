package Innlevering2.Server;

import Innlevering2.Database.DataPublisher;
import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.DatabaseReader;
import Innlevering2.Database.SQLExceptionHandler;
import Innlevering2.Server.ServerSetup.DBCreator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class ServerApplication{

    private ServerConnector serverConnector;
    private DatabaseConnector dbConnector;

    public ServerApplication(ServerConnector serverConnector, DatabaseConnector dbConnector){
        if (serverConnector != null && dbConnector != null) {
            this.serverConnector = serverConnector;
            this.dbConnector = dbConnector;
        }else System.exit(0);
    }


    public void startServer(){
        try {
            //Setting up database
            System.out.println("Setting up database...");
            DatabaseReader dbReader = new DatabaseReader(dbConnector);
            DataPublisher dbPublisher = new DataPublisher(dbConnector);
            DBCreator creator = new DBCreator(dbPublisher);
            creator.run();

            //Setting up server
            System.out.println("Setting up server...");
            Server server = new Server(serverConnector, dbReader);
            server.runServer();
            System.out.println("Server is closed");
        }catch (SQLException e){
            System.out.println(SQLExceptionHandler.sqlErrorCode(e.getErrorCode()));
        }catch (FileNotFoundException noFile){
            System.out.println("No file with that name found in directory!");
        }catch (NullPointerException nul){
            System.out.println("Object Not initialised");
        }catch (IOException e){
            System.out.println("Could not start server");
        }

    }



    public static void main(String[] args) {
        try {
            ServerConnector serverConnector = new ServerConnector("src/main/resources/ServerProperties.properties");
            DatabaseConnector dbConnector = new DatabaseConnector("src/main/resources/DatabaseProperties.properties");
            ServerApplication application = new ServerApplication(serverConnector, dbConnector);
            application.startServer();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (SQLException se){
            System.out.println(SQLExceptionHandler.sqlErrorCode(se.getErrorCode()));
        }
    }

}
