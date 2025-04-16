package org.view;

import org.util.XImage;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Đăng Nhập");
        setSize(714, 537);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setIconImage(XImage.getAppIcon("/IMG/icon.png"));
        setLocationRelativeTo(null);

        //Logo
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(0, 0, 500, 500);
        lblLogo.setIcon(XImage.loadIcon("/IMG/Thunderb.png"));

        JPanel pnlLogin = new Login();
        pnlLogin.setBounds(500, 0, 200, 500);
        pnlLogin.setBackground(Color.WHITE);
        add(lblLogo);
        add(pnlLogin);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}

