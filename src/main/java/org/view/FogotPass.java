package org.view;

import org.DAO.NhanVienDAO;
import org.util.EmailUtils;
import org.util.JDBCHelper;
import org.util.MsgBox;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class FogotPass extends JPanel {
    private JTextField txtUser;
    private JButton btnSendCode, btnBack;
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private EmailUtils emailEntity = new EmailUtils(); // Khởi tạo đối tượng EmailEntity

    public FogotPass() {
        setSize(200, 500);
        setVisible(true);
        setLayout(null);
        setBackground(Color.white);

        // Khai báo font
        Font font = new Font("Bernard MT Condensed", Font.BOLD, 24);
        Font font2 = new Font("Arial", Font.BOLD, 18);
        Font font1 = new Font("Arial", Font.PLAIN, 14);
        Font font4 = new Font("Arial", Font.ITALIC, 12);

        // Giao diện
        JLabel lblTitle = new JLabel("THUNDER SHOP", SwingConstants.CENTER);
        lblTitle.setIcon(new ImageIcon("img/flash.png"));
        lblTitle.setForeground(Color.ORANGE);
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
        txtaInfo.setBounds(20, 205, 160, 80);
        txtaInfo.setWrapStyleWord(true);
        txtaInfo.setLineWrap(true);
        txtaInfo.setOpaque(false);
        txtaInfo.setEditable(false);
        txtaInfo.setFocusable(false);

        btnSendCode = new JButton("GỬI MẬT KHẨU MỚI");
        btnSendCode.setBounds(20, 300, 160, 40);
        btnSendCode.setBackground(Color.ORANGE);
        btnSendCode.setForeground(Color.WHITE);

        btnBack = new JButton("<html><u>QUAY LẠI ĐĂNG NHẬP!</u></html>");
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBackground(Color.WHITE);
        btnBack.setBorderPainted(false);
        btnBack.setForeground(Color.BLUE);
        btnBack.setBounds(5, 400, 190, 40);



        // Thêm tất cả thành phần vào panel
        add(lblTitle);
        add(lblTitleLogin);
        add(lblUser);
        add(txtUser);
        add(txtaInfo);
        add(btnSendCode);
        add(btnBack);

        // Sự kiện nút "Gửi mật khẩu mới"
        btnSendCode.addActionListener(e -> resetPasswordAndSendEmail());

        // Sự kiện nút "Quay lại"
        btnBack.addActionListener(e -> showPanel(new Login()));
    }

    // Hàm xử lý reset mật khẩu và gửi email
    private void resetPasswordAndSendEmail() {
        String email = txtUser.getText().trim();
        if (email.isEmpty()) {
            MsgBox.alert(this,"Vui lòng nhập email!");
            return;
        }

        try {
            // Kiểm tra email có tồn tại trong database không
            if (!nhanVienDAO.checkEmailExists(email)) {
                MsgBox.alert(this,"Email không tồn tại!");
                return;
            }

            // Tạo mật khẩu mới 1
            String newPassword = generateRandomPassword(6);

            // Cập nhật mật khẩu mới vào database
            updatePasswordInDatabase(email, newPassword);

            // Gửi email chứa mật khẩu mới bằng EmailEntity
            emailEntity.sendNewPasswordEmail(email, newPassword);

            MsgBox.alert(this,"Mật khẩu mới đã được gửi qua email!");

        } catch (SQLException ex) {
            MsgBox.alert(this,"Lỗi khi cập nhật database!");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            MsgBox.alert(this,"Lỗi khi gửi email!");
            ex.printStackTrace();
        }
    }
//    ABCDEFGHIJKLMNOPQRSTUVWXYZ
    // Hàm tạo mật khẩu ngẫu nhiên
    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // Hàm cập nhật mật khẩu vào database
    private void updatePasswordInDatabase(String email, String newPassword) throws SQLException {
        String sql = "UPDATE NhanVien SET mk = ? WHERE Email = ?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        }
    }

    // Hàm chuyển panel
    private void showPanel(JPanel panel) {
        this.removeAll();
        this.add(panel, BorderLayout.CENTER);
        this.revalidate();
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
            frame.add(new FogotPass());
            frame.setVisible(true);
        });
    }
}