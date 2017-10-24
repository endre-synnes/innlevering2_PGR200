package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    private ServerConnector serverConnector;
    private DatabaseReader dbReader;
    private int clientNumber = 0;

    public Server(ServerConnector serverConnector, DatabaseReader dbReader){
        this.dbReader = dbReader;
        this.serverConnector = serverConnector;
    }

    /**
     * Creates new threads for each user.
     * @throws IOException
     * @throws SQLException
     */
    public void runServer() throws IOException, SQLException{
        while (true){
            clientNumber++;
            Socket socket = serverConnector.getServerSocket().accept();
            System.out.println("Clients connected: " + clientNumber);
            new ServerThreadManager(socket, dbReader).start();
        }
    }






}
