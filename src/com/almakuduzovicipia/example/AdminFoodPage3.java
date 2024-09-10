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

public class AdminFoodPage3 extends JFrame {

    private JPanel panel1;
    private JLabel picLabel;
    private JLabel textLabel;
    private JLabel priceLabel;
    private JButton backButton;

    public AdminFoodPage3(String foodName, float price, String imagePath, String description) {
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


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });
    }
}