package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


public class Server {

    private ServerConnector serverConnector;
    private DatabaseReader dbReader;
    private ArrayList<ServerThread> listOfThreads = new ArrayList<>();
    private int threadName = 1;

    public Server(ServerConnector serverConnector, DatabaseReader dbReader){
        this.dbReader = dbReader;
        this.serverConnector = serverConnector;
    }

    /**
     * Creates new threads for each user and adds it to an ArrayList
     * @throws IOException Error wile reading or writing
     * @throws SQLException SQL
     */
    public void runServer() throws IOException, SQLException{
        System.out.println("Server is running!\n");
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            Socket socket = serverConnector.getServerSocket().accept();
            ServerThread thread = new ServerThread(socket, dbReader);
            thread.start();
            listOfThreads.add(thread);
            thread.setName("Thread " + threadName);
            threadName++;
            System.out.println("Number of clients connected: " + listOfThreads.size());
          //  if (reader.readLine().equals("stop")) break;
        }

    }

    public ArrayList<ServerThread> getListOfThreads() {
        return listOfThreads;
    }
}
