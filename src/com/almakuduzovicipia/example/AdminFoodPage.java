package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminFoodPage extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton backButton;
    int index = 0;

    public AdminFoodPage() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        String[] foodNames = new String[6];
        float[] prices = new float[6];
        String[] imagePaths = new String[6];
        String[] descriptions = new String[6];


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet resultSet1 = MyJDBC.executeQuery("SELECT * FROM FOOD_TABLE WHERE restaurant='japanese'");


                try {
                    int index = 0;
                    while (resultSet1.next() && index < 6) {

                        String imagePath = resultSet1.getString("image");
                        String description = resultSet1.getString("description");
                        String foodName = resultSet1.getString("name");
                        float price = resultSet1.getFloat("price");


                        imagePaths[index] = imagePath;
                        descriptions[index] = description;
                        foodNames[index] = foodName;
                        prices[index] = price;

                        index++;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                openAdminFoodPage1(foodNames, prices, imagePaths, descriptions);
                dispose();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet resultSet2 = MyJDBC.executeQuery("SELECT * FROM FOOD_TABLE WHERE restaurant='veggie'");
                try {
                    while (resultSet2.next() && index < 6) {

                        String imagePath = resultSet2.getString("image");
                        String description = resultSet2.getString("description");
                        String foodName = resultSet2.getString("name");
                        float price = resultSet2.getFloat("price");


                        imagePaths[index] = imagePath;
                        descriptions[index] = description;
                        foodNames[index] = foodName;
                        prices[index] = price;


                        index++;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                openAdminFoodPage1(foodNames, prices, imagePaths, descriptions);
                dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ResultSet resultSet3 = MyJDBC.executeQuery("SELECT * FROM FOOD_TABLE WHERE restaurant='fastfood'");

                try {
                    while (resultSet3.next() && index < 6) {

                        String imagePath = resultSet3.getString("image");
                        String description = resultSet3.getString("description");
                        String foodName = resultSet3.getString("name");
                        float price = resultSet3.getFloat("price");


                        imagePaths[index] = imagePath;
                        descriptions[index] = description;
                        foodNames[index] = foodName;
                        prices[index] = price;


                        index++;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                openAdminFoodPage1(foodNames, prices, imagePaths, descriptions);
                dispose();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet resultSet4 = MyJDBC.executeQuery("SELECT * FROM FOOD_TABLE WHERE restaurant='italian'");
                try {
                    while (resultSet4.next() && index < 6) {


                        String imagePath = resultSet4.getString("image");
                        String description = resultSet4.getString("description");
                        String foodName = resultSet4.getString("name");
                        float price = resultSet4.getFloat("price");


                        imagePaths[index] = imagePath;
                        descriptions[index] = description;
                        foodNames[index] = foodName;
                        prices[index] = price;


                        index++;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                openAdminFoodPage1(foodNames, prices, imagePaths, descriptions);
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

    private void openAdminFoodPage1(String[] foodName, float[] price, String[] imagePaths, String[] descriptions) {

        for (int i = 0; i < 6; i++) {
            imagePaths[i] = "/images/" + imagePaths[i];
        }

        AdminFoodPage2 adminFoodPage2 = new AdminFoodPage2(foodName, price, imagePaths, descriptions);
        adminFoodPage2.setVisible(true);
        dispose();
    }
}