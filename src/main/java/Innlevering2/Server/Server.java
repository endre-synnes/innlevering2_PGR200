package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private ServerConnector serverConnector;
    private DatabaseReader dbReader;
    private ArrayList<ServerThreadManager> listOfThreads = new ArrayList<>();

    public Server(ServerConnector serverConnector, DatabaseReader dbReader){
        this.dbReader = dbReader;
        this.serverConnector = serverConnector;
    }

    /**
     * Creates new threads for each user and adds it to an ArrayList
     * @throws IOException
     * @throws SQLException
     */
    public void runServer() throws IOException, SQLException{
        while (true){
            Socket socket = serverConnector.getServerSocket().accept();
            ServerThreadManager thread = new ServerThreadManager(socket, dbReader);
            thread.start();
            listOfThreads.add(thread);
            System.out.println("Clients connected: " + listOfThreads.size());
        }
    }






}
