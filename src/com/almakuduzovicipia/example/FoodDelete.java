package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodDelete extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JButton backButton;

    public FoodDelete() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodName = textField1.getText();
                String deleteQuery = "DELETE FROM food_table WHERE name='" + foodName + "'";
                int rowsAffected = MyJDBC.executeUpdate(deleteQuery);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Food successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new AdminHome();
                } else {
                    JOptionPane.showMessageDialog(null, "Delete failed. Please check the entered input.", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new FoodDelete();
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
