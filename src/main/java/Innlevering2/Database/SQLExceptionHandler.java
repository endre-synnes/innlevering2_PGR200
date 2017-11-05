package Innlevering2.Database;

public class SQLExceptionHandler extends Throwable {


    public static String sqlErrorCode(int errorCode){
        switch (errorCode){
            case 1217 : return unableToDrop();
            case 1022 : return "This constraint already exist!";
            case 1146 : return "Table or column name does not exist!";
            case 1054 : return "Unknown column name or value";
            case 1215 : return "Check your table or column name!";
            case 1072 : return "Check if your column name exists!";
            case 1452 : return "At least one row in the child table that references a non-existent row in the parent table";
            case 1064 : return "Wrong formatting in file";
            case 1136 : return "Error while inserting data to table, check for right amount of columns.";
            case 1366 : return "Unexpected data type in content of this file.";
            case 1045 : return "Could not connect to DB, check your properties-file";
            case 0 : return "Could not establish connection to database.";
            default: return "Unknown error: " + errorCode;


        }
    }


    private static String unableToDrop(){
        return "Can't delete table because it is " +
                "connected to another table. Restart the Application" +
                " if you want to delete this table.";
    }
}
