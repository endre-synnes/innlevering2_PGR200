package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ServerCommunicator{

    private ServerConnector serverConnector;
    private DatabaseReader dbReader;

    public ServerCommunicator(ServerConnector serverConnector, DatabaseReader dbReader){
        this.dbReader = dbReader;
        this.serverConnector = serverConnector;
    }


    public void runServer() throws IOException, SQLException{
        while (true){
            Socket socket = serverConnector.getServer().accept();
            new ServerThreadManager(socket, dbReader).start();
        }
    }






}
