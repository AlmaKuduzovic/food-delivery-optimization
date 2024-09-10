package com.almakuduzovicipia.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class FoodPage extends JFrame {
    private JPanel panel1;
    private JButton button3;
    private JButton button1;
    private JButton button2;
    private JButton button4;
    private JLabel description1;
    private JLabel description2;
    private JLabel description4;
    private JLabel description3;
    private JLabel priceLabel1;
    private JLabel priceLabel2;
    private JLabel priceLabel3;
    private JLabel priceLabel4;
    private JButton backButton;
    private JButton nextButton;
    private JButton button5;
    private JLabel description5;
    private JLabel description6;
    private JButton button6;
    private JLabel priceLabel5;
    private JLabel priceLabel6;

    public FoodPage(String[] foodNames, float[] prices, String[] imagePaths, String[] descriptions) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        JButton[] buttons = {button1, button2, button3, button4, button5, button6};
        JLabel[] descriptionLabels = {description1, description2, description3, description4, description5, description6};
        JLabel[] pricesLabels = {priceLabel1, priceLabel2, priceLabel3, priceLabel4, priceLabel5, priceLabel6};
        int numItems = foodNames.length;
        for (int i = 0; i < numItems; i++) {

            try {
                if (foodNames[i] != null && prices[i] != 0.0 && descriptions[i] != null && imagePaths[i] != null) {
                    buttons[i].setVisible(true);
                    buttons[i].setEnabled(true);

                    URL imageURL = getClass().getResource(imagePaths[i]);

                    if (imageURL != null) {
                        BufferedImage image = ImageIO.read(imageURL);
                        ImageIcon imageIcon = new ImageIcon(image);
                        buttons[i].setIcon(imageIcon);
                    } else {
                        System.err.println("Error " + imagePaths[i]);
                    }

                    descriptionLabels[i].setVisible(true);
                    descriptionLabels[i].setText(foodNames[i]);
                    pricesLabels[i].setText(String.format("%.2f", prices[i]));
                }
            } catch (IOException e) {
                new UserPage();
            }
            final int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    openNewItemPage(foodNames[finalI], prices[finalI], imagePaths[finalI], descriptions[finalI]);


                }
            });
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserPage();
                dispose();

            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new myCart();
                dispose();

            }
        });
    }
    private void openNewItemPage(String foodName, float price, String imagePath, String description) {
        ItemPage itemPage = new ItemPage(this, foodName, price, imagePath, description);
        itemPage.setVisible(true);
        dispose();
    }
}