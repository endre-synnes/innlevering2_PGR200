package Innlevering2.Client;

import javax.swing.*;

public class ClientGUI {
    private JPanel panel;
    private JComboBox tableName;
    private JTextField inputTextField;
    private JTextArea textArea;
    private JButton search;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel = new JPanel();

    }


    public void setComboBox(String[] tableNames){
        tableName = new JComboBox(tableNames);
    }




    public void setTextArea(String text){
        textArea.append(text);
    }


}
