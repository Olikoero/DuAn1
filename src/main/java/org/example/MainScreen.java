package org.example;
import util.Auth;
import util.MsgBox;
import util.XImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainScreen extends JFrame {
    private JPanel pnlMain;
    private JTextField txtUser;
    private JLabel lblUser,lblStatus;
    private JButton btnLogin, btnLogout, btnChangePass,
            btnMnuSanPham, btnMnuHoaDon, btnMnuNhanSu, btnMnuKhachHang, btnMnuThongKe,btnMnuTroGiup;

    public MainScreen() {
        setTitle("Thunder Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(null);
        setResizable(false);
        setIconImage(XImage.getAppIcon("/img/basket.png"));
        setLocationRelativeTo(null);
        //Panel Menu
        JPanel pnlMenu = new JPanel();
        pnlMenu.setLayout(null);
        pnlMenu.setBounds(0, 0, 200, 713);

        //Logo
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(0, 0, 200, 200);
        //image
        ImageIcon originalIcon = new ImageIcon("img/thunder.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(scaledImage));

        Font menuFont = new Font("Arial", Font.BOLD, 20);
        Font font = new Font("Arial", Font.BOLD, 16);

        Color defaultBorderColor = Color.LIGHT_GRAY;

        // User
        JPanel pnlUser = new JPanel(null);
        pnlUser.setBounds(0, 200, 200, 230);
        lblUser = new JLabel();
        lblUser.setFont(font);
        lblUser.setBounds(10, 10, 40, 40);
        lblUser.setIcon(new ImageIcon("img/onl.png"));
        txtUser = new JTextField("ALABABA");
        txtUser.setFont(font);
        txtUser.setBounds(45, 10, 150, 40);
        txtUser.setBackground(defaultBorderColor);
        txtUser.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        txtUser.setEditable(false);
        btnMnuTroGiup = new JButton("Hướng dẫn");
        btnMnuTroGiup.setFont(menuFont);
        btnMnuTroGiup.setBounds(5, 60, 190, 40);
        btnLogin = new JButton();
        btnLogin.setFont(font);
        btnLogin.setBounds(5, 110, 90, 50);
        btnLogin.setIcon(XImage.loadImage("img/login.png",btnLogin));
        btnLogout = new JButton();
        btnLogout.setFont(font);
        btnLogout.setBounds(105, 110, 90, 50);
        btnLogout.setIcon(XImage.loadImage("img/logout.png",btnLogout));
        btnChangePass = new JButton("Đổi mật khẩu");
        btnChangePass.setFont(font);
        btnChangePass.setBounds(5, 170, 190, 50);
        btnChangePass.setIcon(new ImageIcon("img/change.png"));
        pnlUser.add(lblUser);
        pnlUser.add(txtUser);
        pnlUser.add(btnMnuTroGiup);
        pnlUser.add(btnLogin);
        pnlUser.add(btnLogout);
        pnlUser.add(btnChangePass);
        btnLogin.setFocusPainted(false);
        btnLogout.setFocusPainted(false);
        btnChangePass.setFocusPainted(false);
        btnLogin.setBackground(defaultBorderColor);
        btnLogout.setBackground(defaultBorderColor);
        btnChangePass.setBackground(defaultBorderColor);


        //menubar
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(5, 1, 2, 2));
        toolbar.setBounds(0, 430, 200, 284);
        btnMnuSanPham = new JButton("Sản phẩm");
        btnMnuSanPham.setFont(menuFont);
        btnMnuSanPham.setIcon(new ImageIcon("img/product.png"));
        btnMnuHoaDon = new JButton("Hoá đơn");
        btnMnuHoaDon.setFont(menuFont);
        btnMnuHoaDon.setIcon(new ImageIcon("img/invoice.png"));
        btnMnuNhanSu = new JButton("Nhân sự");
        btnMnuNhanSu.setFont(menuFont);
        btnMnuNhanSu.setIcon(new ImageIcon("img/employ.png"));
        btnMnuKhachHang = new JButton("Khách hàng");
        btnMnuKhachHang.setFont(menuFont);
        btnMnuKhachHang.setIcon(new ImageIcon("img/custom.png"));
        btnMnuThongKe = new JButton("Thống kê");
        btnMnuThongKe.setFont(menuFont);
        btnMnuThongKe.setIcon(new ImageIcon("img/statis.png"));

        toolbar.add(btnMnuSanPham);
        toolbar.add(btnMnuHoaDon);
        toolbar.add(btnMnuNhanSu);
        toolbar.add(btnMnuKhachHang);
        toolbar.add(btnMnuThongKe);

        btnMnuSanPham.setFocusPainted(false);
        btnMnuHoaDon.setFocusPainted(false);
        btnMnuNhanSu.setFocusPainted(false);
        btnMnuKhachHang.setFocusPainted(false);
        btnMnuThongKe.setFocusPainted(false);
        btnMnuTroGiup.setFocusPainted(false);

        btnMnuSanPham.setBorderPainted(false);
        btnMnuHoaDon.setBorderPainted(false);
        btnMnuNhanSu.setBorderPainted(false);
        btnMnuKhachHang.setBorderPainted(false);
        btnMnuThongKe.setBorderPainted(false);
        btnMnuTroGiup.setBorderPainted(false);


        Color selectedBorderColor = Color.CYAN;
        Color hoverBorderColor = Color.GRAY;
        JButton[] selectedButton = {null};

        btnMnuSanPham.setBackground(defaultBorderColor);
        btnMnuHoaDon.setBackground(defaultBorderColor);
        btnMnuNhanSu.setBackground(defaultBorderColor);
        btnMnuKhachHang.setBackground(defaultBorderColor);
        btnMnuThongKe.setBackground(defaultBorderColor);
        btnMnuTroGiup.setBackground(defaultBorderColor);

        MouseAdapter hoverEffect = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                if (selectedButton[0] != btn) { // Không đổi màu nếu đang được chọn
                    btn.setBackground(hoverBorderColor);
                    btn.setBorder(BorderFactory.createLineBorder(hoverBorderColor, 5));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                if (selectedButton[0] != btn) {
                    btn.setBackground(defaultBorderColor);
                }
            }
        };


        ActionListener clickEffect = e -> {
            JButton clickedButton = (JButton) e.getSource();
            if (selectedButton[0] != null) {
                selectedButton[0].setBackground(defaultBorderColor);
            }
            clickedButton.setBackground(selectedBorderColor);
            selectedButton[0] = clickedButton;
            if (clickedButton == btnMnuSanPham) {
                showPanel(new QLSanPham());
            } else if (clickedButton == btnMnuHoaDon) {
                showPanel(new QLHoaDon());
            } else if (clickedButton == btnMnuNhanSu) {
                showPanel(new QLNhanVien());
            } else if (clickedButton == btnMnuKhachHang) {
                showPanel(new QLKhachHang());
            } else if (clickedButton == btnMnuThongKe) {
                showPanel(new ThongKe());
            }else if (clickedButton == btnMnuTroGiup) {
                showPanel(new LogoMain());
            }
        };
        btnMnuSanPham.addMouseListener(hoverEffect);
        btnMnuHoaDon.addMouseListener(hoverEffect);
        btnMnuNhanSu.addMouseListener(hoverEffect);
        btnMnuKhachHang.addMouseListener(hoverEffect);
        btnMnuThongKe.addMouseListener(hoverEffect);
        btnMnuTroGiup.addMouseListener(hoverEffect);

        btnMnuSanPham.addActionListener(clickEffect);
        btnMnuHoaDon.addActionListener(clickEffect);
        btnMnuNhanSu.addActionListener(clickEffect);
        btnMnuKhachHang.addActionListener(clickEffect);
        btnMnuThongKe.addActionListener(clickEffect);
        btnMnuTroGiup.addActionListener(clickEffect);
        pnlMenu.add(lblLogo);
        pnlMenu.add(toolbar);
        pnlMenu.add(pnlUser);

        //Panel nội dung
        pnlMain = new LogoMain();
//        pnlMain.setLayout(new BorderLayout());
        pnlMain.setBounds(200, 0, 986, 713);

        //Time
        // Status Bar
        JPanel pnlTrangThai = new JPanel();
        pnlTrangThai.setBounds(0, 713, 1186, 50);
        pnlTrangThai.setLayout(new BorderLayout());
        pnlTrangThai.setBorder(new EmptyBorder(0, 20, 0, 20));
        pnlTrangThai.setBackground(Color.getHSBColor(0.98f, 0.13f, 0.98f));
        JLabel lblTrangThai = new JLabel("Thunder DEV TEAM 2025");
        lblTrangThai.setIcon(new ImageIcon("img/info.png"));

        JLabel lblDongHo = new JLabel();
        lblDongHo.setIcon(new ImageIcon("img/Alarm.png"));
        // Update clock
        Timer timer = new Timer(1000, e -> {
            lblDongHo.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        });
        timer.start();
        pnlTrangThai.add(lblTrangThai, BorderLayout.WEST);
        pnlTrangThai.add(lblDongHo, BorderLayout.EAST);
        btnChangePass.addActionListener(e -> showPanel(new ChangePass()));

        add(pnlMenu);
        add(pnlMain);
        add(pnlTrangThai);
        setVisible(true);
    }

    private void showPanel(JPanel panel) {
        if (!Auth.isLogin()) {
            pnlMain.removeAll(); // Xóa nội dung cũ
            pnlMain.add(panel, BorderLayout.CENTER); // Thêm panel mới
            pnlMain.revalidate(); // Cập nhật giao diện
            pnlMain.repaint();
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }

    }


    public static void main(String[] args) {
        new MainScreen();
    }
}

