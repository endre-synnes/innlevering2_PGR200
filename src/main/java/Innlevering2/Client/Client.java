package Innlevering2.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private ClientConnector clientConnector;
    public Client(){
        this.clientConnector = new ClientConnector("src/main/resources/properties.properties");
    }


    public void inputAndOutputFromServer(){
        try (Socket connection = clientConnector.getClientConnection()){
            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            output.flush();
            DataInputStream input = new DataInputStream(connection.getInputStream());
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                output.writeUTF(message);
                output.flush();


                String data = input.readUTF();
                System.out.println(data);
                if (message.equals("bye")){
                    break;
                }
            }

            output.close();
            input.close();

        }catch (IOException e){

        }
    }


    public static void main(String[] args) {
        Client c1 = new Client();
        c1.inputAndOutputFromServer();
    }
}
