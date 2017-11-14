package Innlevering2.Server.ServerSetup;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public FileReader() {}

    /**
     * Reads a file, then create an object of that file witch is then returned.
     * @param filename
     * @return converted file
     */
    public TableObjectFromFile createTableObject(String filename, TableObjectFromFile tableObjectFromFile)
        throws NullPointerException, FileNotFoundException{
        try {
            ArrayList<String> file = readFile(filename);
            ArrayList<String> data = new ArrayList<>();
            tableObjectFromFile.setTableName(file.get(0));
            tableObjectFromFile.setColumnNames(file.get(1).split(";"));
            tableObjectFromFile.setDataTypes(file.get(2).split(";"));
            tableObjectFromFile.setPrimaryKey(file.get(3));
            file
                    .stream()
                    .skip(4)
                    .forEach(data::add);
            tableObjectFromFile.setLinesColumnsFromFile(data);

            return tableObjectFromFile;
        } catch (FileNotFoundException noFile){
            throw new FileNotFoundException("No file with that name");
        }catch (NullPointerException nullExc){
            throw new NullPointerException("Table object not initialised!");
        }

    }

    private ArrayList<String> readFile(String filename) throws FileNotFoundException{
        try(Scanner reader = new Scanner(new java.io.FileReader("docs/files/" + filename))) {
            ArrayList list = new ArrayList();
            while (reader.hasNext()) list.add(reader.nextLine());
            return list;
        } catch (Exception e){
            return null;
        }

    }
}
