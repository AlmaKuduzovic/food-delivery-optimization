package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JButton button1;
    private JPasswordField passwordField1;
    private JFrame frame;

    public LoginPage() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textField1.getText();
                String pass = passwordField1.getText();

                ResultSet resultSet = MyJDBC.executeQuery("SELECT * FROM USERS_TABLE WHERE username='" + user + "' AND password='" + pass + "'");

                try {
                    if (resultSet.next()) {
                        String userType = resultSet.getString("users_type");

                        if ("admin".equals(userType)) {
                            new AdminHome();
                            dispose();
                        } else if ("korisnik".equals(userType)) {
                            new UserPage();
                            dispose();
                        } else if ("kuhinja".equals(userType)) {
                            new KitchenPage();
                            dispose();
                        } else if ("menadzer".equals(userType)) {
                            ManagerPage managerPage = new ManagerPage();
                            managerPage.displayOrders();
                            dispose();
                        }

                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect input. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
