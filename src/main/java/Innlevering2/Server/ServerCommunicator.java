package Innlevering2.Server;

import java.io.*;
import java.net.Socket;

public class ServerCommunicator implements Serializable{

    private ServerConnector serverConnector;

    public ServerCommunicator(ServerConnector serverConnector){
        this.serverConnector = serverConnector;
    }

    public void listenForConnection() throws ClassNotFoundException {
        try (Socket serverSocket = serverConnector.getServer().accept()) {

            ObjectInputStream inputObject = new ObjectInputStream(serverSocket.getInputStream());

//            TableObjectFromDB dbTableIn = (TableObjectFromDB) input.readObject();


            DataOutputStream outputString = new DataOutputStream(serverSocket.getOutputStream());
            outputString.flush();
            DataInputStream inputString = new DataInputStream(serverSocket.getInputStream());

            String message = printCommands().toString();
            outputString.writeUTF(message);
            outputString.flush();
            while (true) {
                String data = input.readUTF();
                System.out.println(data);
                if (data.equals("bye")) {
                    break;
                }
            }
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException noClass){
            throw new ClassNotFoundException("Could not ");
        }
    }



    private StringBuilder printCommands(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 60; i++) { builder.append("#"); }
        builder.append(String.format("\n%-10s %-50s\n", "command:", "Description:"));
        builder.append(String.format("%-10s %-50s\n", "1", "Get tables in database"));
        builder.append(String.format("%-10s %-50s\n", "2", "Get everything from one table"));
        builder.append(String.format("%-10s %-50s\n", "3", "Get all lines from a table that has column equal to your input value."));
        builder.append(String.format("%-10s %-50s\n", "4", "Get all lines that have column value greater or less then your input value."));
        builder.append(String.format("%-10s %-50s\n", "5", "Count rows in a table."));
        builder.append(String.format("%-10s %-50s\n", "6", "Get metadata from table."));
        builder.append(String.format("%-10s %-50s\n", "exit", "Exit program"));
        for (int i = 0; i < 60; i++) { builder.append("#"); }
        builder.append("\nPress the command value and hit 'Enter':");
        return builder;
    }
}
