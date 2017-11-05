package Innlevering2.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class ClientGUI {
    private JPanel panel;
    private JComboBox tableName;
    private JTextField inputTextField;
    private JTextArea textArea;
    private JButton search;
    private JComboBox ColumnName;

    public ClientGUI() {

        tableName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        ColumnName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }



}
