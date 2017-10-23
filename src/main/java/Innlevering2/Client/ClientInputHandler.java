//package Innlevering2.Client;
//
//import Innlevering2.Server.ClientInputManager;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ClientInputHandler implements Serializable{
//    private ArrayList<String> listOfParameters = new ArrayList<>();
//    private Scanner scanner = new Scanner(System.in);
//
//    public ClientInputHandler(){}
//
//
//    public ArrayList<String> convertUserInput(){
//        String readerInput = scanner.nextLine();
//        switch (readerInput){
//            case "1" : return askUserForParameter(1);
//            default: return null;
//        }
//    }
//
//
//
//    private ArrayList<String> askUserForParameter(int numberOfParameters){
//        for (int i = 0; i < numberOfParameters; i++) {
//            System.out.println("Enter parameter: " + (i+1) + " of " + numberOfParameters);
//            listOfParameters.add(scanner.nextLine());
//        }
//        return listOfParameters;
//    }
//
//
//    public ArrayList<String> getListOfParameters() {
//        return listOfParameters;
//    }
//}
