package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myCart extends JFrame {
    private JPanel panel1;
    private JButton pošaljiButton;
    private JButton button4;
    private JButton button3;
    private JButton button2;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton nazadButton;
    private JLabel totalPriceLabel;

    public myCart() {

        JLabel[] orderLabels = {label1, label2, label3, label4};
        String[] foodNames = new String[4];
        int[] foodIds = new int[4];

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        ResultSet resultSet = MyJDBC.executeQuery("SELECT * FROM cart_table");


        try {
            int index = 0;
            while (resultSet.next() && index < 4) {

                int foodId = resultSet.getInt("idkuhinja");
                String foodName = resultSet.getString("ime");


                foodIds[index] = foodId;
                foodNames[index] = foodName;
                index++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < 4; i++) {
            orderLabels[i].setText(foodNames[i]);

            final int finalI = i;
            switch (i) {
                case 0:
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!orderLabels[finalI].getText().isEmpty()) {
                                deleteFromCart(foodIds[finalI]);
                            }
                            dispose();
                        }
                    });
                    break;
                case 1:
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!orderLabels[finalI].getText().isEmpty()) {
                                deleteFromCart(foodIds[finalI]);
                            }
                            dispose();
                        }
                    });
                    break;
                case 2:
                    button3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!orderLabels[finalI].getText().isEmpty()) {
                                deleteFromCart(foodIds[finalI]);
                            }
                            dispose();
                        }
                    });
                    break;
                case 3:
                    button4.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!orderLabels[finalI].getText().isEmpty()) {
                                deleteFromCart(foodIds[finalI]);
                            }
                            dispose();
                        }
                    });
                    break;
            }
        }

        boolean anyLabelNotEmpty = false;

        /*for (JLabel orderLabel : orderLabels) {
            String labelText = orderLabel.getText();
            if (labelText != null && !labelText.isEmpty()) {
                anyLabelNotEmpty = true;
                break;
            }
        }
*/
        pošaljiButton.setEnabled(anyLabelNotEmpty);
        float totalPrice = calculateTotalPrice(foodNames);
        totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));

        pošaljiButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                boolean kitchenTableEmpty = isKitchenTableEmpty();

                if (!kitchenTableEmpty) {
                    new SorryMessage();
                    dispose();
                } else {

                    boolean anyOrderPresent = false;

                    for (String foodName : foodNames) {
                        if (foodName != null && !foodName.isEmpty()) {
                            anyOrderPresent = true;
                            break;
                        }
                    }

                    if (anyOrderPresent) {
                        for (int i = 0; i < 4; i++) {
                            if (foodNames[i] != null && !foodNames[i].isEmpty()) {
                                MyJDBC.executeUpdate("INSERT INTO kitchen_table (ime) VALUES ('" + foodNames[i] + "')");
                                MyJDBC.executeUpdate("INSERT INTO allorders_table (ime) VALUES ('" + foodNames[i] + "')");

                            }

                        }
                        MyJDBC.executeUpdate("INSERT INTO earnings_table (zarada) VALUES ('" + totalPrice + "')");
                        dispose();
                        MyJDBC.executeUpdate("DELETE FROM cart_table");

                        new SubmittedOrder();
                        dispose();
                    } else {

                        System.out.println("nema narudzubi za unos.");
                    }
                }
            }
        });
        nazadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserPage();
                dispose();


            }
        });

    }


    private void deleteFromCart(int foodId) {

        MyJDBC.executeUpdate("DELETE FROM cart_table WHERE idkuhinja = " + foodId);

        refreshCartView();
    }

    private boolean isKitchenTableEmpty() {
        ResultSet resultSet = MyJDBC.executeQuery("SELECT COUNT(*) FROM kitchen_table");

        try {
            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                return rowCount == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void refreshCartView() {
        new myCart();
        dispose();
    }

    private float calculateTotalPrice(String[] foodNames) {
        float totalPrice = 0.0f;
        for (int i = 0; i < 4; i++) {
            if (foodNames[i] != null && !foodNames[i].isEmpty()) {
                ResultSet resultSet = MyJDBC.executeQuery("SELECT price FROM cart_table WHERE ime = '" + foodNames[i] + "'");
                try {
                    if (resultSet.next()) {
                        float itemPrice = resultSet.getFloat("price");
                        totalPrice += itemPrice;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return totalPrice;
    }

}
