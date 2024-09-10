package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerPage extends JFrame {


    private JPanel panel1;
    private JList list1;
    private JButton backButton;
    private JLabel earnings;

    public ManagerPage() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });

        displayOrders();
        displayEarnings();
    }


    public void displayOrders() {
        DefaultListModel<String> listModel = new DefaultListModel<>();


        ResultSet resultSet = MyJDBC.executeQuery("SELECT ime FROM allorders_table");

        try {
            int counter = 1;
            while (resultSet.next()) {
                String imeNarudzbe = resultSet.getString("ime");
                listModel.addElement(counter + ". " + imeNarudzbe);
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        list1.setModel(listModel);
    }

    public void displayEarnings() {

        ResultSet resultSet = MyJDBC.executeQuery("SELECT SUM(zarada) AS total_earnings FROM earnings_table");

        try {
            if (resultSet.next()) {
                float totalEarnings = resultSet.getFloat("total_earnings");
                earnings.setText("Total Earnings: $" + String.format("%.2f", totalEarnings));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}