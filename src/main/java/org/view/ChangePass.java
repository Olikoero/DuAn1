package org.view;

import org.DAO.NhanVienDAO;
import org.util.Auth;
import org.util.MsgBox;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static org.view.QLNhanVien.setBooleanProperty;
import static org.view.QLNhanVien.setComponentProperty;

public class ChangePass extends JPanel {
    private JTextField txtMaNV;
    private JPasswordField txtMatKhau, txtMatKhau1, txtMatKhau2;
    private JButton btnDongY, btnHuyBo;
    private Image backgroundImage;

    public ChangePass() {
        setSize(986, 713);
        setLayout(null);
        setVisible(true);
        backgroundImage = new ImageIcon("img/background.png").getImage();

        Font font = new Font("Arial", Font.BOLD, 30);
        Font font1 = new Font("Arial", Font.BOLD, 16);
        JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU");
        lblTitle.setFont(font);
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(368,150,300,50);

//        JLabel lblMaNV = new JLabel("TÊN ĐĂNG NHẬP");
//        lblMaNV.setBounds(368, 150, 250, 30);
//        lblMaNV.setFont(font1);
//        txtMaNV = new JTextField();
//        txtMaNV.setBounds(368, 180, 250, 30);

        JLabel lblMatKhau = new JLabel("MẬT KHẨU HIỆN TẠI");
        lblMatKhau.setBounds(368, 250, 250, 30);
        lblMatKhau.setFont(font1);
        txtMatKhau = new JPasswordField();
        txtMatKhau.setBounds(368, 290, 250, 30);

        JLabel lblMatKhau1 = new JLabel("MẬT KHẨU MỚI");
        lblMatKhau1.setBounds(368, 320, 250, 30);
        lblMatKhau1.setFont(font1);
        txtMatKhau1 = new JPasswordField();
        txtMatKhau1.setBounds(368, 350, 250, 30);

        JLabel lblMatKhau2 = new JLabel("XÁC NHẬN MẬT KHẨU");
        lblMatKhau2.setBounds(368, 380, 250, 30);
        lblMatKhau2.setFont(font1);
        txtMatKhau2 = new JPasswordField();
        txtMatKhau2.setBounds(368, 410, 250, 30);

        // Tạo các JButton
        btnDongY = new JButton("ĐỒNG Ý");
        btnDongY.setBounds(368,500 , 120, 70);
        btnDongY.setIcon(new ImageIcon("img/Refresh.png"));
        btnHuyBo = new JButton("Hủy bỏ");
        btnHuyBo.setBounds(497, 500, 120, 70);
        btnHuyBo.setIcon(new ImageIcon("img/No.png"));
        AbstractButton[] comp={btnDongY,btnHuyBo};

        setBooleanProperty(comp, AbstractButton::setFocusPainted, false);
        setBooleanProperty(comp, AbstractButton::setBorderPainted, false);
        setComponentProperty(comp, c -> c.setBackground(Color.WHITE) );
        add(lblTitle);
//        add(lblMaNV);
//        add(txtMaNV);
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
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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