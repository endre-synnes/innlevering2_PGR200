package Innlevering2.Server;

import Innlevering2.Database.DatabaseConnector;
import Innlevering2.Database.SQLExceptionHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ServerApplication implements Runnable{

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
            DBCreator creator = new DBCreator(dbConnector);
            creator.run();
        }catch (SQLException e){
            System.out.println(SQLExceptionHandler.sqlErrorCode(e.getErrorCode()));
        }catch (FileNotFoundException noFile){
            System.out.println("No file with that name found in directory!");
        }catch (NullPointerException nul){
            System.out.println("Object Not initialised");
        }

    }



    public static void main(String[] args) {
        try {
            ServerConnector serverConnector = new ServerConnector("src/main/resources/ServerProperties.properties");
            DatabaseConnector dbConnector = new DatabaseConnector("src/main/resources/DatabaseProperties.properties");
            ServerApplication application = new ServerApplication(serverConnector, dbConnector);
            application.startServer();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error while reading while connecting to db or server, check properties files");
        }
    }

    @Override
    public void run() {

    }
}
