package Innlevering2.Client;

import Innlevering2.Database.SQLExceptionHandler;
import Innlevering2.Server.TableObjectFromDB;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ClientApplication {

    private ClientConnector clientConnector;
    private BufferedReader clientCommand = new BufferedReader(new InputStreamReader(System.in));
    private String commandNumber = "1";


    public ClientApplication(ClientConnector clientConnector){
        this.clientConnector = clientConnector;
    }


    public void inputAndOutputFromServer() throws NullPointerException, SQLException, ClassNotFoundException, IOException{
        try (Socket socket = clientConnector.getClientConnection()) {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);


            ObjectInputStream inputObject = new ObjectInputStream(socket.getInputStream());
            TableObjectFromDB returnObject = null;
            while (true) {
                //Printing possible user commands
                System.out.println("\n" + printCommands());

                //Getting input from user
                String userInput = buildCommandFromUserInput();
                if (userInput == null){
                    System.out.println("Invalid command please try again!");
                    continue;
                }
                if (userInput.equals("exit")) break;

                //Sending to server
                printWriter.println(userInput);


                //Reading output from server
                returnObject = (TableObjectFromDB) inputObject.readObject();
                String result;
                if (commandNumber.equals("5")) {
                    result = StringCreator.getMetaData(returnObject);
                } else result = StringCreator.getContent(returnObject);
                System.out.println("\n" + result);
            }
            System.exit(0);
        }
    }

    private String buildCommandFromUserInput() throws IOException{
        commandNumber = clientCommand.readLine();
        switch (commandNumber){
            case "1" :
                return "1";
            case "2" :
                return commandNumber + "," + getTableName();
            case "3" :
                return commandNumber +
                        "," + getTableName() +
                        "," + getColumnName() +
                        "," + getValue();
            case "4" :
                return commandNumber + "," + getTableName();
            case "5" :
                return commandNumber + "," + getTableName();
            case "exit" : return "exit";
            default: return null;
        }
    }

    private String getValue() throws IOException{
        System.out.println("Enter value: ");
        return clientCommand.readLine();
    }

    private String getColumnName() throws IOException{
        System.out.println("Enter column name: ");
        return clientCommand.readLine();
    }

    private String getTableName() throws IOException{
        System.out.println("Enter table name: ");
        return clientCommand.readLine();
    }


    private String printCommands(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 60; i++) { builder.append("#"); }
        builder.append(String.format("\n%-10s %-50s\n", "command:", "Description:"));
        builder.append(String.format("%-10s %-50s\n", "1", "Get tables in database"));
        builder.append(String.format("%-10s %-50s\n", "2", "Get everything from one table"));
        builder.append(String.format("%-10s %-50s\n", "3", "Get all lines from a table that has column equal to your input value."));
        builder.append(String.format("%-10s %-50s\n", "4", "Count rows in a table."));
        builder.append(String.format("%-10s %-50s\n", "5", "Get metadata from table."));
        builder.append(String.format("%-10s %-50s\n", "exit", "Exit program"));
        for (int i = 0; i < 60; i++) { builder.append("#"); }
        builder.append("\nPress the command value and hit 'Enter':");
        return builder.toString();
    }



    public static void main(String[] args) {
        try {
            ClientConnector clientConnector = new ClientConnector("src/main/resources/ServerProperties.properties");
            ClientApplication c1 = new ClientApplication(clientConnector);
            c1.inputAndOutputFromServer();
        } catch (SQLException e) {
            System.out.println(SQLExceptionHandler.sqlErrorCode(e.getErrorCode()));
        } catch (IOException e){
            System.out.println("IO Exception check if correct input");
        } catch (ClassNotFoundException noClass){
            System.out.println("Could not read object from Server, se if valid object type");
        }

    }
}
