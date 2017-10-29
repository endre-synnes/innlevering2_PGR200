package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;
import Innlevering2.Database.SQLExceptionHandler;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

public class ServerThreadManager extends Thread {

    private Socket socket;
    private DatabaseReader dbReader;
    private Boolean clientIsConnected = true;


    public ServerThreadManager(Socket socket, DatabaseReader dbReader){
        this.socket = socket;
        this.dbReader = dbReader;
    }


    public void run() {
        try {
            ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Client have connected to server");
            while (clientIsConnected){
                String message = readUserInput();
                if (message != null) {
                    if (message.equals("exit")){
                        clientIsConnected = false;
                        continue;
                    }
                    outputObject.writeObject(handleUserCommand(message));
                    outputObject.flush();
                }
            }
            socket.close();
            System.out.println("Client closed connection");


        } catch (SocketException se){
            System.out.println("Lost connection to client");
        }catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    private String readUserInput() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        return bufferedReader.readLine();
    }

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
