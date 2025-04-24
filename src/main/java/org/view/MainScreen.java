package org.view;
import org.util.Auth;
import org.util.MsgBox;
import org.util.XImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.view.QLNhanVien.*;

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
        setIconImage(XImage.getAppIcon("/IMG/icon.png"));
        setLocationRelativeTo(null);


        //Panel Menu
        JPanel pnlMenu = new JPanel();
        pnlMenu.setLayout(null);
        pnlMenu.setBounds(0, 0, 200, 713);

        //Logo
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(0, 0, 200, 200);
        //image
        ImageIcon originalIcon = XImage.loadIcon("/IMG/thunder.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(scaledImage));

        Font menuFont = new Font("Arial", Font.BOLD, 20);
        Font font = new Font("Arial", Font.BOLD, 16);

        Color defaultBorderColor = Color.LIGHT_GRAY;

        // User
        JPanel pnlUser = new JPanel(null);
        pnlUser.setBounds(0, 200, 200, 230);
        lblUser = new JLabel();
        lblUser.setBounds(10, 10, 40, 40);
        lblUser.setIcon(XImage.loadIcon("/IMG/onl.png"));
        txtUser = new JTextField("a");
        txtUser.setBounds(45, 10, 150, 40);
        txtUser.setBackground(defaultBorderColor);
        txtUser.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        txtUser.setEditable(false);
        txtUser.setFont(new Font("Arial",Font.BOLD,14));
        btnMnuTroGiup = new JButton("Trang chủ");
        btnMnuTroGiup.setBounds(5, 60, 190, 40);
        btnLogin = new JButton();
        btnLogin.setBounds(5, 110, 90, 50);
        btnLogin.setIcon(XImage.loadIcon("/IMG/login.png"));
        btnLogout = new JButton();
        btnLogout.setBounds(105, 110, 90, 50);
        btnLogout.setIcon(XImage.loadIcon("/IMG/logout.png"));
        btnChangePass = new JButton("Đổi mật khẩu");
        btnChangePass.setFont(font);
        btnChangePass.setBounds(5, 170, 190, 50);
        btnChangePass.setIcon(XImage.loadIcon("/IMG/change.png"));
        addCompoment(pnlUser,lblUser,txtUser,btnMnuTroGiup,btnChangePass,btnLogin,btnLogout);
        setFontForTextFields(font,lblUser,btnChangePass);

        //menubar
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(5, 1, 2, 2));
        toolbar.setBounds(0, 430, 200, 284);
        btnMnuSanPham = new JButton("Sản phẩm");
        btnMnuSanPham.setIcon(XImage.loadIcon("/IMG/product.png"));
        btnMnuHoaDon = new JButton("Hoá đơn");
        btnMnuHoaDon.setIcon(XImage.loadIcon("/IMG/invoice.png"));
        btnMnuNhanSu = new JButton("Nhân sự");
        btnMnuNhanSu.setIcon(XImage.loadIcon("/IMG/employ.png"));
        btnMnuKhachHang = new JButton("Khách hàng");
        btnMnuKhachHang.setIcon(XImage.loadIcon("/IMG/custom.png"));
        btnMnuThongKe = new JButton("Thống kê");
        btnMnuThongKe.setIcon(XImage.loadIcon("/IMG/statis.png"));
        setFontForTextFields(menuFont,btnMnuTroGiup,btnMnuSanPham,btnMnuHoaDon,btnMnuNhanSu,
                btnMnuKhachHang,btnMnuThongKe);

        addCompoment(toolbar,btnMnuSanPham,btnMnuHoaDon,btnMnuKhachHang,btnMnuNhanSu,btnMnuThongKe);

        Color selectedBorderColor = Color.CYAN;
        Color hoverBorderColor = Color.GRAY;
        JButton[] selectedButton = {null};

        AbstractButton[] btn= {btnMnuSanPham,btnMnuHoaDon,btnMnuNhanSu,btnMnuKhachHang,
                btnMnuThongKe,btnMnuTroGiup,btnLogin,btnLogout,btnChangePass};

        setComponentProperty(btn, c -> c.setBackground(defaultBorderColor) );
        setBooleanProperty(btn, AbstractButton::setFocusPainted, false);
        setBooleanProperty(btn, AbstractButton::setBorderPainted, false);

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

        addEffects(hoverEffect,clickEffect,btnMnuSanPham,btnMnuHoaDon,btnMnuNhanSu,btnMnuKhachHang,
                btnMnuThongKe,btnMnuTroGiup,btnChangePass);

        pnlMenu.add(lblLogo);
        pnlMenu.add(toolbar);
        pnlMenu.add(pnlUser);

        //Panel nội dung
        pnlMain = new LogoMain();
        pnlMain.setBounds(200, 0, 986, 713);

        //Time
        // Status Bar
        JPanel pnlTrangThai = new JPanel();
        pnlTrangThai.setBounds(0, 713, 1186, 50);
        pnlTrangThai.setLayout(new BorderLayout());
        pnlTrangThai.setBorder(new EmptyBorder(0, 20, 0, 20));
        pnlTrangThai.setBackground(Color.getHSBColor(0.98f, 0.13f, 0.98f));
        JLabel lblTrangThai = new JLabel("Thunder DEV TEAM 2025");
        lblTrangThai.setIcon(XImage.loadIcon("/IMG/Info.png"));

        JLabel lblDongHo = new JLabel();
        lblDongHo.setIcon(XImage.loadIcon("/IMG/Alarm.png"));
        // Update clock
        Timer timer = new Timer(1000, e -> {
            lblDongHo.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        });
        timer.start();
        pnlTrangThai.add(lblTrangThai, BorderLayout.WEST);
        pnlTrangThai.add(lblDongHo, BorderLayout.EAST);
        btnChangePass.addActionListener(e -> showPanel(new ChangePass()));
        btnLogin.addActionListener(e -> DangNhap());
        btnLogout.addActionListener(e -> DangXuat());

        add(pnlMenu);
        add(pnlMain);
        add(pnlTrangThai);
        setVisible(true);
        status();
        Employees();
    }
    public static void addEffects(MouseAdapter hoverEffect, ActionListener clickEffect, JButton... buttons) {
        for (JButton button : buttons) {
            button.addMouseListener(hoverEffect);
            button.addActionListener(clickEffect);
        }
    }
    private void showPanel(JPanel panel) {
        if(Auth.isLogin()) {
            pnlMain.removeAll(); // Xóa nội dung cũ
            pnlMain.add(panel, BorderLayout.CENTER); // Thêm panel mới
            pnlMain.revalidate(); // Cập nhật giao diện
            pnlMain.repaint();
        }else {
            MsgBox.alert(this, "Chua dang nhap");
        }
    }
    void DangNhap(){
        if(Auth.isLogin()){
            MsgBox.alert(this, "Bạn đã đăng nhập");
        }else {
            new LoginScreen().setVisible(true);
        }
    }
    void DangXuat(){
        Auth.clear();
        new LoginScreen().setVisible(true);
        this.dispose();
    }
    public void status() {
        if (Auth.isLogin()) {
            txtUser.setText(Auth.user.getHoVaTen());
            lblUser.setIcon(XImage.loadIcon("/IMG/onl.png"));
        } else {
            txtUser.setText("Guest");
            lblUser.setIcon(XImage.loadIcon("/IMG/off.png"));
        }
    }
    public void Employees() {
        if (Auth.isManager()) {
            btnMnuNhanSu.setVisible(true);
            btnMnuThongKe.setVisible(true);
        } else {
            btnMnuNhanSu.setVisible(false);
            btnMnuThongKe.setVisible(false);
        }
    }


    public static void main(String[] args) {
        new MainScreen();
    }
}
