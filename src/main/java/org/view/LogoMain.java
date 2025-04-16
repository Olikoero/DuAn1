package org.view;

import org.util.XImage;

import javax.swing.*;
import java.awt.*;

public class LogoMain extends JPanel {
    public LogoMain() {
        setSize(986, 713);
        setLayout(new BorderLayout());
        JLabel lblMain = new JLabel();
        lblMain.setIcon(XImage.loadIcon("/IMG/thunder2.png"));
//        add(lblMain, BorderLayout.CENTER);
//        ImageIcon originalIcon1 = new ImageIcon("img/thunder2.png");
//        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(986, 713, Image.SCALE_SMOOTH);
//        lblMain.setIcon(new ImageIcon(scaledImage1));
//        pnlMain.add(lblMain, BorderLayout.CENTER);
        add(lblMain,BorderLayout.CENTER);
        setVisible(true);
    }
}
