package Innlevering2.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI {
    private JPanel panel;
    private JComboBox tableName;
    private JTextField inputTextField;
    private JTextArea textArea;
    private JButton search;

    public ClientGUI() {
        tableName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

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
