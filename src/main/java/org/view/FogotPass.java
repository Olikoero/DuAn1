package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FogotPass extends JPanel {
    private JTextField txtUser;
    private JButton btnNewPass,btnBack;
    public FogotPass(){
        setSize(200,500);
        setVisible(true);
        setLayout(null);
        setBackground(Color.white);
        //Title
        JLabel lblTitle = new JLabel("THUNDER SHOP", SwingConstants.CENTER);
        lblTitle.setIcon(new ImageIcon("img/flash.png"));
        lblTitle.setForeground(Color.ORANGE);
        Font font = new Font("Bernard MT Condensed", Font.BOLD, 24);
        Font font2 = new Font("Arial", Font.BOLD, 18);
        Font font1 = new Font("Arial", Font.PLAIN, 14);
        Font font4 = new Font("Arial", Font.ITALIC, 12);
        lblTitle.setFont(font);
        lblTitle.setBounds(10, 30, 180, 50);


        JLabel lblTitleLogin = new JLabel("QUÊN MẬT KHẨU", SwingConstants.CENTER);
        lblTitleLogin.setFont(font2);
        lblTitleLogin.setBounds(20, 90, 160, 40);
        JLabel lblUser = new JLabel("EMAIL NGƯỜI DÙNG");
        lblUser.setFont(font1);
        lblUser.setBounds(20, 130, 160, 30);
        txtUser = new JTextField();
        txtUser.setBounds(20, 160, 160, 35);

        JTextArea txtaInfo = new JTextArea("Vui lòng nhập thông tin Email bạn đã đăng kí!");
        txtaInfo.setFont(font4);
        txtaInfo.setBounds(20,205,160,80);
        txtaInfo.setWrapStyleWord(true);
        txtaInfo.setLineWrap(true);
        txtaInfo.setOpaque(false); // Làm cho nó trong suốt giống JLabel
        txtaInfo.setEditable(false);
        txtaInfo.setFocusable(false);

        btnNewPass = new JButton();
        btnNewPass.setIcon(new ImageIcon("img/loginn.png"));
        btnNewPass.setBounds(80, 300, 40, 40);
        btnNewPass.setFocusPainted(false);
        btnNewPass.setBackground(Color.WHITE);
        btnNewPass.setBorder(new LineBorder(new Color(200, 200, 200), 2));

        btnBack = new JButton("<html><u>QUAY LẠI ĐĂNG NHẬP!</u></html>");
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBackground(Color.WHITE);
        btnBack.setBorderPainted(false);
        btnBack.setForeground(Color.BLUE);
        btnBack.setBounds(5, 400, 190, 40);


        add(lblTitle);
        add(lblTitleLogin);
        add(lblUser);
        add(txtUser);
        add(txtaInfo);
        add(btnNewPass);
        add(btnBack);
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnBack.setForeground(Color.gray);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btnBack.setForeground(Color.BLUE);
            }
        });
        btnBack.addActionListener(e -> showPanel(new Login()));
    }

    private void showPanel(JPanel panel) {
        this.removeAll(); // Xóa nội dung cũ
        this.add(panel, BorderLayout.CENTER); // Thêm panel mới
        this.revalidate(); // Cập nhật giao diện
        this.repaint();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Forgot Password");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(214, 537);
            frame.setLayout(null);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            FogotPass panel = new FogotPass();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
