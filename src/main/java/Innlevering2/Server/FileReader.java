package Innlevering2.Server;

import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public FileReader() {}

    /**
     * Reads a file, then create an object of that file witch is then returned.
     * @param filename
     * @return converted file
     */
    public TableObjectFromFile createTableObject(String filename, TableObjectFromFile tableObjectFromFile) {
        try {
            ArrayList<String> file = readFile(filename);
            ArrayList data = new ArrayList<>();
            tableObjectFromFile.setTableName(file.get(0));
            tableObjectFromFile.setColumnNames(file.get(1).split(";"));
            tableObjectFromFile.setDataTypes(file.get(2).split(";"));
            tableObjectFromFile.setPrimaryKey(file.get(3));
            for (int i = 4; i < file.size(); i++) {
                data.add(file.get(i));
            }
            tableObjectFromFile.setLinesColumnsFromFile(data);
            return tableObjectFromFile;
        }
        catch (Exception e){
            System.out.println("No such file!");
            return null;
        }
    }

    private ArrayList<String> readFile(String filename){
        try(Scanner reader = new Scanner(new java.io.FileReader("docs/files/" + filename+".csv"))) {
            ArrayList list = new ArrayList();
            while (reader.hasNext()) list.add(reader.nextLine());
            return list;
        } catch (Exception e){
            return null;
        }

    }
}
