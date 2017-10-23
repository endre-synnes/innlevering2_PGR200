package Innlevering2.Client;

import Innlevering2.Server.TableObjectFromDB;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private ClientConnector clientConnector;
    private int clientNumber;
    public Client(int clientNumber){
        this.clientConnector = new ClientConnector("src/main/resources/ServerProperties.properties");
        this.clientNumber = clientNumber;
    }


    public void inputAndOutputFromServer() throws NullPointerException, SQLException, ClassNotFoundException{
        try (Socket socket = clientConnector.getClientConnection()){
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader clientCommand = new BufferedReader(new InputStreamReader(System.in));

//            BufferedReader serverCommandList = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
//
//            ObjectOutputStream outputObject = new ObjectOutputStream(socket.getOutputStream());
//            ClientInputHandler clientInput = null;

            ObjectInputStream inputObject = new ObjectInputStream(socket.getInputStream());
            TableObjectFromDB returnObject = null;
            while (true) {
                //Read commands from server
//                System.out.println(serverCommandList.readLine());
                //System.out.println(printCommands());

                //Sending to server
                String readerInput = clientCommand.readLine();
                String[] parameters = null;
                if (readerInput.equals("2")){
                    parameters = new String[2];
                    parameters[0] = readerInput;
                    parameters[1] = clientCommand.readLine();
                }
                System.out.println(parameters[0]);
                printWriter.println(parameters[0]+","+parameters[1]);
//                clientInput = new ClientInputHandler();
//                clientInput.convertUserInput();
//                outputObject.writeObject(clientInput);
//                outputObject.flush();


                //Reading output from server
                returnObject = (TableObjectFromDB) inputObject.readObject();
                System.out.println(StringCreator.getContent(returnObject));
            }

//            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
//            output.flush();
//            DataInputStream input = new DataInputStream(connection.getInputStream());
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                String message = scanner.nextLine();
//                output.writeUTF(message);
//                output.flush();
//
//
//                String data = input.readUTF();
//                System.out.println(data);
//                if (message.equals("bye")){
//                    break;
//                }
//            }
//
//            output.close();
//            input.close();

        }catch (IOException e){
            e.printStackTrace();
        }catch (NullPointerException nu){
            nu.printStackTrace();
        }
    }

//    private int desideParameterSize(String clientCommand){
//        switch (clientCommand){
//            case "1" : return 1;
//        }
//    }


    private String printCommands(){
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
        return builder.toString();
    }



    public static void main(String[] args) {
        Client c1 = new Client(1);
        try {
            c1.inputAndOutputFromServer();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
