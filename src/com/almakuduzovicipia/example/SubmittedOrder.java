package com.almakuduzovicipia.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmittedOrder extends JFrame {
    private JPanel panel1;
    private JButton backButton;
    private JButton exitButton;


    public SubmittedOrder() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserPage();
                dispose();
            }
        });
    }
}
