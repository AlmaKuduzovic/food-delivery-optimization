package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodUpdate extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton saveChangesButton;
    private JTextField textField3;
    private JTextField textField4;
    private JButton backButton;
    private JButton saveButton;

    public FoodUpdate() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldFoodName = textField1.getText();
                String newFoodName = textField2.getText();
                String newFoodDescribe = textField3.getText();
                String newFoodPrice = textField4.getText();

                if (oldFoodName.isEmpty() || newFoodName.isEmpty() || newFoodDescribe.isEmpty() || newFoodPrice.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all the fields.", "Empty Field", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    double price = Double.parseDouble(newFoodPrice);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for the price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String selectQuery = "SELECT * FROM food_table WHERE name='" + oldFoodName + "'";
                ResultSet resultSet = MyJDBC.executeQuery(selectQuery);

                try {
                    if (resultSet.next()) {

                        String updateQuery = "UPDATE food_table SET name='" + newFoodName + "', `description`='" + newFoodDescribe + "', price='" + newFoodPrice + "' WHERE name='" + oldFoodName + "'";
                        int rowsAffected = MyJDBC.executeUpdate(updateQuery);

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Food details successfully updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            new AdminHome();
                        } else {
                            JOptionPane.showMessageDialog(null, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                            dispose();
                            new FoodUpdate();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Food not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminHome();
                dispose();
            }
        });
    }
}

