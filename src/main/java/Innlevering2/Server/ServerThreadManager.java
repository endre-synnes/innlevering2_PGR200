package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

public class ServerThreadManager extends Thread {

    private Socket socket;
    private DatabaseReader dbReader;

    public ServerThreadManager(Socket socket, DatabaseReader dbReader){
        this.socket = socket;
        this.dbReader = dbReader;
    }

    private void readUserInput(){

    }

    public void run() {
        try {
            Boolean clientIsConnected = true;
            String message = null;
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            //output object
            TableObjectFromDB dbTable = null;
            ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());


            System.out.println("ClientApplication have connected to server");
            //input translator
            while (clientIsConnected){
                message = bufferedReader.readLine();
                if (message != null) {
                    dbTable = new TableObjectFromDB();
                    ClientInputManager inputManager = new ClientInputManager(dbReader, dbTable);
                    String[] clientInput = message.split(",");

                    if (clientInput[0].equals("exit")){
                        clientIsConnected = false;
                        continue;
                    }

                    dbTable = inputManager.clientInputTranslator(clientInput);
                    outputObject.writeObject(dbTable);
                    outputObject.flush();
                }
            }
            socket.close();
            System.out.println("Client closed connection");
        }  catch (SQLException e){
            System.out.println(e.getErrorCode());
        } catch (SocketException se){
            System.out.println("Lost connection to client");
        }catch (IOException e) {
            System.out.println("Lost connection to client");
        }
    }


}
