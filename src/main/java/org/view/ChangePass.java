package org.view;

import org.DAO.NhanVienDAO;
import org.util.Auth;
import org.util.MsgBox;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ChangePass extends JPanel {
    private JTextField txtMaNV;
    private JPasswordField txtMatKhau, txtMatKhau1, txtMatKhau2;
    private JButton btnDongY, btnHuyBo;

    public ChangePass() {
        setSize(986, 713);
        setLayout(null);
        setVisible(true);

        Font font = new Font("Arial", Font.BOLD, 30);
        Font font1 = new Font("Arial", Font.BOLD, 20);
        JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU");
        lblTitle.setFont(font);
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(368,30,300,50);

        JLabel lblMaNV = new JLabel("TÊN ĐĂNG NHẬP");
        lblMaNV.setBounds(368, 120, 250, 40);
        lblMaNV.setFont(font1);
        txtMaNV = new JTextField();
        txtMaNV.setBounds(368, 170, 250, 40);

        JLabel lblMatKhau = new JLabel("MẬT KHẨU HIỆN TẠI");
        lblMatKhau.setBounds(368, 220, 250, 40);
        lblMatKhau.setFont(font1);
        txtMatKhau = new JPasswordField();
        txtMatKhau.setBounds(368, 270, 250, 40);

        JLabel lblMatKhau1 = new JLabel("MẬT KHẨU MỚI");
        lblMatKhau1.setBounds(368, 320, 250, 40);
        lblMatKhau1.setFont(font1);
        txtMatKhau1 = new JPasswordField();
        txtMatKhau1.setBounds(368, 370, 250, 40);

        JLabel lblMatKhau2 = new JLabel("XÁC NHẬN MẬT KHẨU");
        lblMatKhau2.setBounds(368, 420, 250, 40);
        lblMatKhau2.setFont(font1);
        txtMatKhau2 = new JPasswordField();
        txtMatKhau2.setBounds(368, 470, 250, 40);

        // Tạo các JButton
        btnDongY = new JButton("ĐỒNG Ý");
        btnDongY.setBounds(368,550 , 120, 50);
        btnDongY.setIcon(new ImageIcon("img/Refresh.png"));
        btnHuyBo = new JButton("Hủy bỏ");
        btnHuyBo.setBounds(497, 550, 120, 50);
        btnHuyBo.setIcon(new ImageIcon("img/No.png"));
        add(lblTitle);
        add(lblMaNV);
        add(txtMaNV);
        add(lblMatKhau);
        add(txtMatKhau);
        add(lblMatKhau1);
        add(txtMatKhau1);
        add(lblMatKhau2);
        add(txtMatKhau2);
        add(btnDongY);
        add(btnHuyBo);
        btnHuyBo.addActionListener(e -> huyBo());
        btnDongY.addActionListener(e -> {
            try {
                DoiMK();
            } catch (SQLException ex) {
                ex.printStackTrace(); // In ra lỗi để gỡ lỗi
                MsgBox.alert(this, "Lỗi khi đổi mật khẩu!");
            }
        });
    }

    NhanVienDAO dao = new NhanVienDAO();

    private void DoiMK() throws SQLException {
        String manv = txtMaNV.getText();
        String matKhau = new String(txtMatKhau.getPassword());
        String matKhauMoi = new String(txtMatKhau1.getPassword());
        String matKhauMoi2 = new String(txtMatKhau2.getPassword());

        if (!manv.equalsIgnoreCase(Auth.user.getMaNv())) {
            MsgBox.alert(this, "Sai tên đăng nhập!");
        } else if (!matKhau.equals(Auth.user.getMatKhau())) {
            MsgBox.alert(this, "Sai mật khẩu!");
        } else if (!matKhauMoi.equals(matKhauMoi2)) {
            MsgBox.alert(this, "Xác nhận mật khẩu không đúng!");
        } else {
            Auth.user.setMatKhau(matKhauMoi);
            dao.update(Auth.user);
            MsgBox.alert(this, "Đổi mật khẩu thành công!");
        }
    }

    private void huyBo() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }

    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Forgot Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 740);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        ChangePass panel = new ChangePass();
        frame.add(panel);

        frame.setVisible(true);
    });
}
}