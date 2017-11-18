package Innlevering2.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputConverter {
    private BufferedReader clientCommand = new BufferedReader(new InputStreamReader(System.in));
    private String commandNumber = "1";


    /**
     * Building command to send to the server.
     * @return string of user commands
     * @throws IOException could not read user commands
     */
    public String buildCommandFromUserInput() throws IOException {
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

    /**
     * Getting column value form the user.
     * @return string of user input
     * @throws IOException not able to read user input
     */
    private String getValue() throws IOException{
        System.out.println("Enter value: ");
        return clientCommand.readLine();
    }

    /**
     * Getting column name form the user.
     * @return string of user input
     * @throws IOException not able to read user input
     */
    private String getColumnName() throws IOException{
        System.out.println("Enter column name: ");
        return clientCommand.readLine();
    }

    /**
     * Getting table name form the user.
     * * @return string of user input
     * @throws IOException not able to read user input
     */
    private String getTableName() throws IOException{
        System.out.println("Enter table name: ");
        return clientCommand.readLine();
    }

}
