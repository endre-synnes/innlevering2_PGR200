package Innlevering2.Client;

import Innlevering2.Database.SQLExceptionHandler;
import Innlevering2.Server.TableObjectFromDB;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class ClientApplication {

    private ClientConnector clientConnector;
    private String userInput = "1";


    public ClientApplication(ClientConnector clientConnector){
        this.clientConnector = clientConnector;
    }


    public void inputAndOutputFromServer() throws NullPointerException, SQLException,
            ClassNotFoundException, IOException{
        try (Socket socket = clientConnector.getClientConnection()) {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            ObjectInputStream inputObject = new ObjectInputStream(socket.getInputStream());
            UserInputConverter inputConverter = new UserInputConverter();

            while (true) {
                //Printing possible user commands
                System.out.println("\n" + printCommands());

                //Getting input from user
                userInput = inputConverter.buildCommandFromUserInput();
                if (userInput == null){
                    System.out.println("Invalid command please try again!");
                    continue;
                }
                if (userInput.equals("exit")){
                    printWriter.println(userInput);
                    break;
                }

                //Sending to server
                printWriter.println(userInput);

                //Reading output from server
                handleReturnFromServer(inputObject);
            }
            System.exit(0);
        }
    }


    private void handleReturnFromServer(ObjectInputStream inputStream) throws IOException, ClassNotFoundException{
        try {
            TableObjectFromDB returnObject;
            Object objectFromServer = inputStream.readObject();

            String result;
            if (objectFromServer != null) {
                returnObject = (TableObjectFromDB) objectFromServer;
                if (userInput.substring(0,1).equals("5")) {
                    result = StringCreator.getMetaData(returnObject);
                } else result = StringCreator.getContent(returnObject);
                System.out.println("\n" + result);
            }else System.out.println("Could not find anything matching your input.");
        }catch (IOException e){
            throw new IOException("Could not handle object from server");
        }
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
        }catch (UnknownHostException unknown){
            System.out.println(unknown.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException noClass){
            System.out.println("Could not read object from Server, se if valid object type");
        }

    }
}
