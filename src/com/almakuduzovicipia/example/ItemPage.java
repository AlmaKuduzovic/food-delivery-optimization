package com.almakuduzovicipia.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemPage extends JFrame {

    private JPanel panel1;
    private JLabel picLabel;
    private JButton addToCartButton;
    private JLabel textLabel;
    private JLabel priceLabel;
    private JButton backButton;
    private FoodPage parentFoodPage;

    public ItemPage(FoodPage parentFoodPage, String foodName, float price, String imagePath, String description) {
        this.parentFoodPage = parentFoodPage;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        try {
            InputStream stream = getClass().getResourceAsStream(imagePath);

            if (stream != null) {
                BufferedImage image = ImageIO.read(stream);
                ImageIcon imageIcon = new ImageIcon(image);
                picLabel.setIcon(imageIcon);
            } else {
                System.err.println("Slika nije pronađena: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        textLabel.setText(description);
        priceLabel.setText(String.format("$%.2f", price));


        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet resultSet = MyJDBC.executeQuery("SELECT COUNT(*) FROM cart_table");

                try {
                    if (resultSet.next()) {
                        int currentProductCount = resultSet.getInt(1);

                        if (currentProductCount < 4) {
                            MyJDBC.executeUpdate("INSERT INTO cart_table (ime, price) VALUES ('" + foodName + "', " + price + ")");
                                dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot add more items to the cart. Please remove an existing item before adding a new one.", "Cart Full", JOptionPane.WARNING_MESSAGE);
                            dispose();
                        }
                    }
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }


                new myCart();

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFoodPage.setVisible(true);
                dispose();

            }
        });
    }
}