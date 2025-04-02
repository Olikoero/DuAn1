package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.util.JDBCHelper.getConnection;

public class QLNhanVien extends JPanel {
    private JTable tblNhanVien;
    private JTextField txtMaNV, txtHoTen, txtEmail, txtTimKiem;
    private JPasswordField txtMatKhau, txtMatKhau2;
    private JRadioButton rdoTruongPhong, rdoNhanVien;
    private JButton btnThem, btnSua, btnXoa, btnMoi,
            btnFirst, btnPrev, btnNext, btnLast;
    private DefaultTableModel tableModel;
    private int row = -1;


    public QLNhanVien() {
        setSize(986, 713);
        setLayout(null);

        Font font = new Font("Arial", Font.BOLD, 14);

        // Tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setFont(lblTitle.getFont().deriveFont(30f));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(50, 30, 500, 50);

        // Tab cập nhật
        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE, 1));
        pnlCapNhat.setBounds(20, 100, 370, 443);

        JLabel lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setBounds(20, 10, 150, 30);
        txtMaNV = new JTextField();
        txtMaNV.setBounds(20, 40, 330, 30);
        txtMaNV.setEditable(false); // Không cho chỉnh sửa mã nhân viên

        JLabel lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setBounds(20, 70, 150, 30);
        txtMatKhau = new JPasswordField();
        txtMatKhau.setBounds(20, 100, 330, 30);

        JLabel lblMatKhau2 = new JLabel("Xác nhận mật khẩu");
        lblMatKhau2.setBounds(20, 130, 170, 30);
        txtMatKhau2 = new JPasswordField();
        txtMatKhau2.setBounds(20, 160, 330, 30);

        JLabel lblHoTen = new JLabel("Họ và tên");
        lblHoTen.setBounds(20, 190, 100, 30);
        txtHoTen = new JTextField();
        txtHoTen.setBounds(20, 220, 330, 30);

        JLabel lblEmail = new JLabel("Địa chỉ Email");
        lblEmail.setBounds(20, 250, 170, 30);
        txtEmail = new JTextField();
        txtEmail.setBounds(20, 280, 330, 30);

        JLabel lblVaiTro = new JLabel("Vai trò");
        lblVaiTro.setBounds(20, 310, 100, 30);
        rdoTruongPhong = new JRadioButton("Trưởng phòng");
        rdoTruongPhong.setBounds(20, 340, 150, 30);
        rdoNhanVien = new JRadioButton("Nhân viên");
        rdoNhanVien.setBounds(200, 340, 150, 30);
        ButtonGroup vaiTroGroup = new ButtonGroup();
        vaiTroGroup.add(rdoTruongPhong);
        vaiTroGroup.add(rdoNhanVien);

        JPanel pnlBtn2 = new JPanel();
        pnlBtn2.setLayout(new GridLayout(1, 4, 10, 10));
        pnlBtn2.setBounds(10, 400, 350, 30);
        btnFirst = new JButton(new ImageIcon("img/first.png"));
        btnPrev = new JButton(new ImageIcon("img/left.png"));
        btnNext = new JButton(new ImageIcon("img/right.png"));
        btnLast = new JButton(new ImageIcon("img/last.png"));
        pnlBtn2.add(btnFirst);
        pnlBtn2.add(btnPrev);
        pnlBtn2.add(btnNext);
        pnlBtn2.add(btnLast);

        addCompoment(pnlCapNhat, lblMaNV, lblMatKhau, lblMatKhau2, lblHoTen, lblVaiTro, lblEmail, pnlBtn2,
                txtMaNV, txtMatKhau, txtMatKhau2, txtHoTen, rdoTruongPhong, rdoNhanVien, txtEmail);

        setFontForTextFields(font, lblMaNV, lblMatKhau, lblMatKhau2, lblHoTen, lblEmail, lblVaiTro, rdoNhanVien, rdoTruongPhong);

        // Buttons chức năng
        JPanel pnlBtn1 = new JPanel();
        pnlBtn1.setLayout(new GridLayout(2, 2, 30, 20));
        pnlBtn1.setBounds(20, 553, 370, 130);
        btnMoi = new JButton(new ImageIcon("img/new.png"));
        btnXoa = new JButton(new ImageIcon("img/delete.png"));
        btnSua = new JButton(new ImageIcon("img/save.png"));
        btnThem = new JButton(new ImageIcon("img/add.png"));
        pnlBtn1.add(btnMoi);
        pnlBtn1.add(btnXoa);
        pnlBtn1.add(btnSua);
        pnlBtn1.add(btnThem);

        AbstractButton[] components = {btnMoi, btnXoa, btnSua, btnThem, btnFirst, btnPrev, btnNext, btnLast};
        setBooleanProperty(components, AbstractButton::setFocusPainted, false);
        setBooleanProperty(components, AbstractButton::setBorderPainted, false);
        setComponentProperty(components, c -> c.setBackground(Color.LIGHT_GRAY));

        // Tab danh sách
        JPanel pnlDanhSach = new JPanel(null);
        pnlDanhSach.setBounds(410, 100, 556, 583);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE, 1));

        // Khung tìm kiếm
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        lblTimKiem.setBounds(10, 10, 80, 30);
        lblTimKiem.setFont(font);
        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(90, 10, 456, 30);

        // Bảng danh sách
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"MÃ NV", "MẬT KHẨU", "HỌ VÀ TÊN", "VAI TRÒ", "EMAIL"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblNhanVien = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        scrollPane.setBounds(10, 50, 536, 523);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        pnlDanhSach.add(lblTimKiem);
        pnlDanhSach.add(txtTimKiem);
        pnlDanhSach.add(scrollPane);

        // Thêm các thành phần vào panel chính
        add(pnlCapNhat);
        add(pnlBtn1);
        add(pnlDanhSach);
        add(lblTitle);

        // Load dữ liệu ban đầu từ database
        loadDataToTable();

        // Xử lý sự kiện tìm kiếm động
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                timKiemNhanVien();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                timKiemNhanVien();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                timKiemNhanVien();
            }
        });

        // Xử lý sự kiện nhấp vào bảng để hiển thị thông tin nhân viên
        tblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = tblNhanVien.getSelectedRow();
                if (row >= 0) {
                    edit();
                    updateStatus();
                }
            }
        });

        // Xử lý sự kiện cho các nút điều hướng
        btnFirst.addActionListener(e -> first());
        btnPrev.addActionListener(e -> prev());
        btnNext.addActionListener(e -> next());
        btnLast.addActionListener(e -> last());

        // Cập nhật trạng thái ban đầu
        updateStatus();

        setVisible(true);
    }

    // Load dữ liệu từ database vào bảng
    private void loadDataToTable() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM NhanVien")) {
            tableModel.setRowCount(0); // Xóa dữ liệu cũ
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("manv"),
                        rs.getString("mk"),
                        rs.getString("tennv"),
                        rs.getString("vaitro"),
                        rs.getString("email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Tìm kiếm nhân viên từ database
    private void timKiemNhanVien() {
        String keyword = txtTimKiem.getText().trim();
        String query = "SELECT * FROM NhanVien WHERE manv LIKE ? OR tennv LIKE ? OR email LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Xóa dữ liệu cũ
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("manv"),
                        rs.getString("mk"),
                        rs.getString("tennv"),
                        rs.getString("vaitro"),
                        rs.getString("email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Hiển thị thông tin nhân viên lên form khi nhấp vào bảng
    private void edit() {
        String maNV = (String) tblNhanVien.getValueAt(row, 0);
        String matKhau = (String) tblNhanVien.getValueAt(row, 1);
        String hoTen = (String) tblNhanVien.getValueAt(row, 2);
        String vaiTro = (String) tblNhanVien.getValueAt(row, 3);
        String email = (String) tblNhanVien.getValueAt(row, 4);

        // Hiển thị thông tin lên các trường nhập liệu
        txtMaNV.setText(maNV);
        txtMatKhau.setText(matKhau);
        txtMatKhau2.setText(matKhau);
        txtHoTen.setText(hoTen);
        txtEmail.setText(email);

        // Hiển thị vai trò chính xác trên radio buttons
        if (vaiTro != null) {
            if (vaiTro.trim().equalsIgnoreCase("Trưởng phòng")) {
                rdoTruongPhong.setSelected(true);
                rdoNhanVien.setSelected(false);
            } else if (vaiTro.trim().equalsIgnoreCase("Nhân viên")) {
                rdoNhanVien.setSelected(true);
                rdoTruongPhong.setSelected(false);
            } else {
                // Nếu vai trò không khớp, bỏ chọn cả hai
                rdoTruongPhong.setSelected(false);
                rdoNhanVien.setSelected(false);
            }
        } else {
            // Nếu vai trò là null, bỏ chọn cả hai
            rdoTruongPhong.setSelected(false);
            rdoNhanVien.setSelected(false);
        }
    }

    // Điều hướng: Đi đến dòng đầu tiên
    private void first() {
        if (tblNhanVien.getRowCount() > 0) {
            row = 0;
            tblNhanVien.setRowSelectionInterval(row, row);
            edit();
            updateStatus();
        }
    }

    // Điều hướng: Lùi lại một dòng
    private void prev() {
        if (row > 0) {
            row--;
            tblNhanVien.setRowSelectionInterval(row, row);
            edit();
            updateStatus();
        }
    }

    // Điều hướng: Tiến lên một dòng
    private void next() {
        if (row < tblNhanVien.getRowCount() - 1) {
            row++;
            tblNhanVien.setRowSelectionInterval(row, row);
            edit();
            updateStatus();
        }
    }

    // Điều hướng: Đi đến dòng cuối cùng
    private void last() {
        if (tblNhanVien.getRowCount() > 0) {
            row = tblNhanVien.getRowCount() - 1;
            tblNhanVien.setRowSelectionInterval(row, row);
            edit();
            updateStatus();
        }
    }

    // Cập nhật trạng thái các nút điều hướng
    private void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblNhanVien.getRowCount() - 1);

        btnFirst.setEnabled(tblNhanVien.getRowCount() > 0 && !first);
        btnPrev.setEnabled(tblNhanVien.getRowCount() > 0 && !first);
        btnNext.setEnabled(tblNhanVien.getRowCount() > 0 && !last);
        btnLast.setEnabled(tblNhanVien.getRowCount() > 0 && !last);

        // Cập nhật trạng thái các nút chức năng
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnMoi.setEnabled(true);
    }

    // Các phương thức hỗ trợ
    public static void setFontForTextFields(Font font, JComponent... fields) {
        for (JComponent field : fields) {
            field.setFont(font);
        }
    }

    public static void addCompoment(JComponent component, JComponent... comps) {
        for (JComponent comp : comps) {
            component.add(comp);
        }
    }

    public static void setBooleanProperty(AbstractButton[] buttons, BiConsumer<AbstractButton, Boolean> setter, boolean value) {
        for (AbstractButton button : buttons) {
            setter.accept(button, value);
        }
    }

    public static void setComponentProperty(JComponent[] components, Consumer<JComponent> setter) {
        for (JComponent component : components) {
            setter.accept(component);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Nhân Viên");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 740);
            frame.setLayout(null);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            QLNhanVien panel = new QLNhanVien();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
//Test