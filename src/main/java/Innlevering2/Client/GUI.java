package Innlevering2.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JPanel pnlNorth, pnlCenter;
    private JTextField fldInputValue;
    private JComboBox tableNames, columnNames;
    private JButton btnSubmit;
    private JTextArea txtArea;

    public GUI(){
        super("Time");

        pnlNorth = new JPanel();
        pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.LINE_AXIS));
        pnlCenter = new JPanel();
        pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.PAGE_AXIS));

        fldInputValue = new JTextField(10);
        fldInputValue.setToolTipText("Enter value to search for here...");
        fldInputValue.setMaximumSize(new Dimension(150, 20));
        pnlNorth.add(fldInputValue);

        tableNames = new JComboBox();
        tableNames.addActionListener( e -> getTable());
        pnlNorth.add(tableNames);
        columnNames = new JComboBox();
        pnlNorth.add(columnNames);
        btnSubmit = new JButton("Search");
        btnSubmit.setToolTipText("Click to search...");
        btnSubmit.addActionListener( e -> search());
        pnlNorth.add(btnSubmit);


        txtArea = new JTextArea();
        pnlCenter.add(txtArea);




        pnlNorth.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(pnlNorth, BorderLayout.NORTH);
        add(pnlCenter, BorderLayout.CENTER);

    }

    private void getTable() {
    }

    private void search() {
        
    }

    public void setTableNames(ArrayList<String> tableNames) {
        this.tableNames.removeAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        tableNames.stream().forEach(model::addElement);
        this.tableNames.setModel(model);
    }

    public void setColumnNames(ArrayList<String> columnNames){
        this.columnNames.removeAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        columnNames.stream().forEach(model::addElement);
        this.columnNames.setModel(model);
    }
}
