package Innlevering2.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputConverter {
    private BufferedReader clientCommand = new BufferedReader(new InputStreamReader(System.in));
    private String commandNumber = "1";


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

}
