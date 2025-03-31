package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

import org.DAO.*;
import org.Entity.*;
import org.util.*;

public class Login extends JPanel {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin, btnForgotPass;
    private JCheckBox chkremember;
    private Preferences prefs = Preferences.userNodeForPackage(Login.class);
    public Login(){

        setSize(200,500);
        setVisible(true);
        setLayout(null);
        setBackground(Color.white);
        //Title
        JLabel lblTitle = new JLabel("THUNDER SHOP", SwingConstants.CENTER);
        lblTitle.setIcon(new ImageIcon("img/flash.png"));
        lblTitle.setForeground(Color.ORANGE);
        Font font = new Font("Bernard MT Condensed", Font.BOLD, 24);
        Font font2 = new Font("Arial", Font.BOLD, 20);
        Font font1 = new Font("Arial", Font.PLAIN, 16);
        lblTitle.setFont(font);
        lblTitle.setBounds(10, 30, 180, 50);

        //Compoment
        JLabel lblTitleLogin = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblTitleLogin.setFont(font2);
        lblTitleLogin.setBounds(20, 90, 160, 40);
        JLabel lblUser = new JLabel("TÊN NGƯỜI DÙNG");
        lblUser.setFont(font1);
        lblUser.setBounds(20, 130, 160, 30);
        txtUser = new JTextField();
        txtUser.setBounds(20, 160, 160, 35);

        JLabel lblpass = new JLabel("MẬT KHẨU");
        lblpass.setFont(font1);
        lblpass.setBounds(20, 195, 160, 30);
        txtPass = new JPasswordField();
        txtPass.setBounds(20, 225, 160, 35);

        chkremember = new JCheckBox("GHI NHỚ ĐĂNG NHẬP");
        chkremember.setBounds(20, 270, 160, 30);
        chkremember.setBackground(Color.WHITE);

        btnLogin = new JButton();
        btnLogin.setIcon(new ImageIcon("img/loginn.png"));
        btnLogin.setBounds(80, 320, 40, 40);
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setBorder(new LineBorder(new Color(200, 200, 200), 2));

        btnForgotPass = new JButton("<html><u>KHÔNG THỂ ĐĂNG NHẬP?</u></html>");
        btnForgotPass.setFocusPainted(false);
        btnForgotPass.setContentAreaFilled(false);
        btnForgotPass.setBackground(Color.WHITE);
        btnForgotPass.setBorderPainted(false);
        btnForgotPass.setForeground(Color.BLUE);
        btnForgotPass.setBounds(5, 400, 190, 40);


        add(lblTitle);
        add(lblTitleLogin);
        add(lblUser);
        add(txtUser);
        add(lblpass);
        add(txtPass);
        add(chkremember);
        add(btnLogin);
        add(btnForgotPass);
        String savedUser = prefs.get("username", "");
        String savedPass = prefs.get("password", "");
        boolean remember = prefs.getBoolean("remember", false);

        txtUser.setText(savedUser);
        txtPass.setText(savedPass);
        chkremember.setSelected(remember);

        btnForgotPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnForgotPass.setForeground(Color.gray);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btnForgotPass.setForeground(Color.BLUE);
            }
        });
        btnForgotPass.addActionListener(e -> showPanel(new FogotPass()));
        btnLogin.addActionListener(e -> DangNhap());
    }
    NhanVienDAO nvDAO = new NhanVienDAO();
    void DangNhap() {
        new JDBCHelper();
        String manv = txtUser.getText();
        String matKhau = new String(txtPass.getPassword());
        NhanVien nv = nvDAO.selectByID(manv);

        if (nv == null) {
            MsgBox.alert(this, "Sai tên đăng nhập!");
        } else if (!matKhau.equals(nv.getMatKhau())) {
            MsgBox.alert(this, "Sai mật khẩu!");
        } else if (chkremember.isSelected()) {
            prefs.put("username", manv);
            prefs.put("password", matKhau);
            prefs.putBoolean("remember", true);
//            MsgBox.alert(this, "Đăng Nhập Thành Công!");
            Auth.user = nv;
            new MainScreen();
        } else {
            prefs.remove("username");
            prefs.remove("password");
            prefs.remove("remember");
            Auth.user = nv;
            new MainScreen();
        }

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

            Login panel = new Login();
            frame.add(panel);

            frame.setVisible(true);
        });
    }

}
