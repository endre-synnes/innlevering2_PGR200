package Innlevering2.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerApplication {

    private ServerConnector server;

    public ServerApplication(){
        this.server = new ServerConnector("src/main/resources/properties.properties");
    }

    public void listenForConnection(){
        try (Socket clientConnection = server.getServer().accept()){
            DataOutputStream output = new DataOutputStream(clientConnection.getOutputStream());
            output.flush();
            DataInputStream input = new DataInputStream(clientConnection.getInputStream());

            String message = "Velkomen til min server";
            output.writeUTF(message);
            output.flush();
            while (true){
                String data = input.readUTF();
                System.out.println(data);
                if (data.equals("bye")) {
                    break;
                }
            }
            output.close();
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerApplication application = new ServerApplication();
        application.listenForConnection();
    }
}
