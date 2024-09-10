package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KitchenPage extends JFrame {

    private JPanel panel1;
    private JButton zaIsporukuButton;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton backButton;

    public KitchenPage() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        JLabel[] orderLabels = {label1, label2, label3, label4};
        String[] foodNames = new String[4];

        ResultSet resultSet = MyJDBC.executeQuery("SELECT * FROM kitchen_table");


        try {
            int index = 0;
            while (resultSet.next() && index < 4) {

                String foodName = resultSet.getString("ime");

                foodNames[index] = foodName;
                index++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < 4; i++) {
            orderLabels[i].setText(foodNames[i]);

        }

        zaIsporukuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyJDBC.executeUpdate("DELETE FROM kitchen_table");
                new DeliveryPage();
                dispose();
            }
        });

    }
}