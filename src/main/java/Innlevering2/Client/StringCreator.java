package Innlevering2.Client;

import Innlevering2.Server.TableObjectFromDB;

public class StringCreator {

    public StringCreator(){ }


    /**
     * Getting content of a table object and returning it as an formatted String.
     * @param dbTable database table
     * @return String
     */
    public static String getContent(TableObjectFromDB dbTable){
        StringBuilder string = new StringBuilder();
        for (String s: dbTable.getColumnName()) {
            string.append(String.format("%-30s", s));
        }
        string.append("\n");
        for (int i = 0; i < dbTable.getColumnName().length * 25; i++) { string.append("-"); }
        string.append("\n");
        for (String[] line: dbTable.getContentOfTable()) {
            for (String s: line) {
                string.append(String.format("%-30s", s));
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Same as getContent(), but his gets the Metadata from the tableObject.
     * @param dbTable database table
     * @return String
     */
    public static String getMetaData(TableObjectFromDB dbTable){
        StringBuilder string = new StringBuilder();
        string.append(String.format("%-15s%-15s%-15s\n", "Name", "Size", "Data type"));
        for (int i = 0; i < 40; i++) { string.append("-"); }
        string.append("\n");
        for (int i = 0; i < dbTable.getColumnName().length; i++) {
            string.append(String.format("%-15s%-15s%-15s\n",
                    dbTable.getColumnName()[i],
                    dbTable.getColumnTypeName()[i],
                    dbTable.getColumnDisplaySize()[i]));
        }
        return string.toString();
    }

}
