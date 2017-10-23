package Innlevering2.Server;

import Innlevering2.Database.DatabaseReader;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ServerThreadManager extends Thread {

    private Socket socket;
    private DatabaseReader dbReader;

    public ServerThreadManager(Socket socket, DatabaseReader dbReader){
        this.socket = socket;
        this.dbReader = dbReader;
    }

    public void run() {
        try {
            String message = null;
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            //output object
            TableObjectFromDB dbTable = null;
            ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());


            System.out.println("Server up and running");
            //input translator
            while ((message = bufferedReader.readLine()) != null){
                dbTable = new TableObjectFromDB();
                ClientInputManager inputManager = new ClientInputManager(dbReader, dbTable);
                String[] clientInput = message.split(",");
                dbTable = inputManager.clientInputTranslator(clientInput);
                outputObject.writeObject(dbTable);
                outputObject.flush();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
