package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;
import Innlevering2.Database.SQLExceptionHandler;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

public class ServerThread extends Thread {

    private Socket socket;
    private DatabaseReader dbReader;
    private Boolean clientIsConnected = true;


    public ServerThread(Socket socket, DatabaseReader dbReader){
        this.socket = socket;
        this.dbReader = dbReader;
    }


    /**
     * Main loop for the Thread. This method is responsible for keeping the thread alive.
     * It delegate tasks to methods for reading and writing to client.
     */
    public void run() {
        try {
            ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("\nClient have connected to: " + this.getName());
            while (clientIsConnected){
                String message = readUserInput();
                if (message != null) {
                    if (message.equals("exit")){
                        clientIsConnected = false;
                        continue;
                    }
                    System.out.println(this.getName() + " got command: " + message);
                    outputObject.writeObject(handleUserCommand(message));
                    outputObject.flush();
                }
                else clientIsConnected = false;
            }
            System.out.println("Client on " + this.getName() + " closed connection");
            this.join();



        } catch (SocketException se){
            System.out.println("Lost connection to client");
        }catch (IOException e) {
            System.out.println("IO Exception");
        } catch (InterruptedException ie){
            System.out.println("Failed to close thread");
        }
    }

    /**
     * Reading input from user.
     * @return String
     * @throws IOException error while reading
     */
    private String readUserInput() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        return bufferedReader.readLine();
    }

    /**
     * Translate commands from user to something the server understands.
     * @param message message from user
     * @return table object
     */
    private TableObjectFromDB handleUserCommand(String message) {
        try {
            TableObjectFromDB dbTable = new TableObjectFromDB();
            ClientInputManager inputManager = new ClientInputManager(dbReader, dbTable);
            String[] clientInput = message.split(",");
            return inputManager.clientInputTranslator(clientInput);
        }catch (SQLException se){
            System.out.println(SQLExceptionHandler.sqlErrorCode(se.getErrorCode()));
            return null;
        }

    }

}
