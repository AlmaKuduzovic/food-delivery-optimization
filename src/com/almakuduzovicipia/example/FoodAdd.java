package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodAdd extends JFrame {
    private JPanel panel1;
    private JTextField enterNameTextField;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField pngTextField;
    private JButton button1;
    private JButton backButton;

    public FoodAdd() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodName = enterNameTextField.getText();
                String foodDescription = textField1.getText();
                String foodPrice = textField2.getText();
                String typeOfFood = (String) comboBox1.getSelectedItem();
                String foodImage = pngTextField.getText();


                if (foodName.isEmpty() || foodDescription.isEmpty() || foodPrice.isEmpty() || foodImage.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all the fields.", "Empty Field", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    float price = Float.parseFloat(foodPrice);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for the price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!(foodImage.toLowerCase().endsWith(".png") || foodImage.toLowerCase().endsWith(".jpg"))) {
                    JOptionPane.showMessageDialog(null, "Image must be in PNG or JPG format.", "Invalid Image Format", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                MyJDBC.executeUpdate("INSERT INTO food_table (name, description, price, restaurant, image) " +
                        "VALUES ('" + foodName + "', '" + foodDescription + "', '" + foodPrice + "', '" + typeOfFood + "', '" + foodImage + "')");
                JOptionPane.showMessageDialog(null, "Food successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new AdminHome();
                dispose();

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
