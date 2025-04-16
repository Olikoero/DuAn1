package org.view;

import org.DAO.SanPhamDAO;
import org.Entity.SanPham;
import org.util.Auth;
import org.util.MsgBox;
import org.util.XImage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import static org.view.QLNhanVien.*;
import static org.view.QLNhanVien.setBooleanProperty;

public class QLSanPham extends JPanel {
    private JTabbedPane tabs;
    private JLabel lblAnh;
    private JTable tblSanPham;
    private JTextArea txtGhiChu;
    private JTextField txtMaSP, txtSL, txtTenSP, txtGiaBan, txtGiaNhap, txtNgayNhap, txtTimKiem;
    private JFileChooser fileChooser;
    private JButton btnMoi, btnXoa, btnSua, btnThem, btnFirst, btnPrev, btnNext, btnLast;
    private DefaultTableModel model;

    SanPhamDAO dao = new SanPhamDAO();

    int row = -1;

    public QLSanPham() {
        setSize(986, 713);
        setLayout(null);

        Font font = new Font("Arial", Font.BOLD, 14);

        JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
        lblTitle.setFont(lblTitle.getFont().deriveFont(30f));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(50, 15, 500, 30);

        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE, 1));
        pnlCapNhat.setBounds(20, 60, 946, 320);

        JLabel lblMaSP = new JLabel("Mã SP");
        lblMaSP.setBounds(10, 10, 100, 30);
        txtMaSP = new JTextField();
        txtMaSP.setBounds(10, 40, 200, 30);
        txtMaSP.setEditable(false); // Disable editing for MaSP

        JLabel lblTenSP = new JLabel("Tên SP");
        lblTenSP.setBounds(10, 70, 100, 30);
        txtTenSP = new JTextField();
        txtTenSP.setBounds(10, 100, 200, 30);

        JLabel lblSL = new JLabel("Số lượng");
        lblSL.setBounds(10, 130, 100, 30);
        txtSL = new JTextField();
        txtSL.setBounds(10, 160, 200, 30);

        JLabel lblGiaNhap = new JLabel("Giá nhập");
        lblGiaNhap.setBounds(10, 190, 100, 30);
        txtGiaNhap = new JTextField();
        txtGiaNhap.setBounds(10, 220, 200, 30);

        JLabel lblGiaBan = new JLabel("Giá bán");
        lblGiaBan.setBounds(250, 10, 100, 30);
        txtGiaBan = new JTextField();
        txtGiaBan.setBounds(250, 40, 200, 30);

        JLabel lblNgayNhap = new JLabel("Ngày nhập");
        lblNgayNhap.setBounds(250, 70, 100, 30);
        txtNgayNhap = new JTextField("Ngày/Tháng/Năm");
        txtNgayNhap.setForeground(Color.GRAY);
        txtNgayNhap.setBounds(250, 100, 200, 30);

        JLabel lblGhiChu = new JLabel("Ghi chú");
        lblGhiChu.setBounds(250, 130, 100, 30);
        txtGhiChu = new JTextArea();
        txtGhiChu.setBorder(new LineBorder(Color.BLACK, 1));
        txtGhiChu.setBounds(250, 160, 200, 90);

        JLabel lblHinh = new JLabel("Hình ảnh");
        lblHinh.setBounds(490, 10, 100, 30);

        lblAnh = new JLabel();
        lblAnh.setBounds(490, 40, 210, 260);
        lblAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblAnh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chonAnh();
            }
        });
        fileChooser = new JFileChooser();

        JPanel pnlBtn1 = new JPanel();
        pnlBtn1.setLayout(new GridLayout(2, 2, 10, 30));
        pnlBtn1.setBounds(720, 40, 206, 260);
        btnMoi = new JButton(XImage.loadIcon("/IMG/new.png"));
        btnXoa = new JButton(XImage.loadIcon("/IMG/Delete.png"));
        btnSua = new JButton(XImage.loadIcon("/IMG/Save.png"));
        btnThem = new JButton(XImage.loadIcon("/IMG/add.png"));
        pnlBtn1.add(btnMoi);
        pnlBtn1.add(btnXoa);
        pnlBtn1.add(btnSua);
        pnlBtn1.add(btnThem);

        JPanel pnlBtn2 = new JPanel();
        pnlBtn2.setLayout(new GridLayout(1, 4, 10, 10));
        pnlBtn2.setBounds(10, 270, 440, 30);
        btnFirst = new JButton(XImage.loadIcon("/IMG/First.png"));
        btnPrev = new JButton(XImage.loadIcon("/IMG/Left.png"));
        btnNext = new JButton(XImage.loadIcon("/IMG/Right.png"));
        btnLast = new JButton(XImage.loadIcon("/IMG/Last.png"));
        pnlBtn2.add(btnFirst);
        pnlBtn2.add(btnPrev);
        pnlBtn2.add(btnNext);
        pnlBtn2.add(btnLast);

        addCompoment(pnlCapNhat, lblMaSP, txtMaSP, lblTenSP, txtTenSP, lblSL, txtSL, lblGiaNhap, txtGiaNhap, lblGiaBan,
                txtGiaBan, lblNgayNhap, txtNgayNhap, txtGhiChu, lblGhiChu, lblHinh, lblAnh, fileChooser, pnlBtn1, pnlBtn2);

        //danhsach
        JPanel pnlDanhSach = new JPanel(null);

        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        lblTimKiem.setBounds(20, 10, 100, 30);
        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(120, 10, 500, 30);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE, 1));
        pnlDanhSach.setBounds(20, 390, 946, 300);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE, 1));
        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"MÃ SP", "TÊN SP", "SỐ LƯỢNG", "GIÁ NHẬP", "GIÁ BÁN", "NGÀY NHẬP", "ẢNH"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblSanPham = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblSanPham);
        scrollPane.setBounds(10, 40, 926, 250);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        pnlDanhSach.add(scrollPane);
        pnlDanhSach.add(lblTimKiem);
        pnlDanhSach.add(txtTimKiem);
        setFontForTextFields(font, lblMaSP, lblTenSP, lblSL, lblGiaNhap, lblGiaBan, lblNgayNhap, lblGhiChu, lblHinh, lblTimKiem);

        add(lblTitle);
        add(pnlCapNhat);
        add(pnlDanhSach);

        btnThem.addActionListener(e -> them());
        btnSua.addActionListener(e -> sua());
        btnXoa.addActionListener(e -> xoa());
        btnMoi.addActionListener(e -> moi());
        btnFirst.addActionListener(e -> first());
        btnPrev.addActionListener(e -> prev());
        btnNext.addActionListener(e -> next());
        btnLast.addActionListener(e -> last());

        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    row = tblSanPham.getSelectedRow();
                    if (row >= 0) {
                        edit();
                    }
                }
            }
        });

        fillTable();
        updateStatus();

        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndUpdateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndUpdateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndUpdateTable();
            }
        });
        AbstractButton[] btn={btnPrev,btnLast,btnNext,btnFirst,btnThem,btnXoa,btnSua,btnMoi};
        Color defaultBorderColor = Color.LIGHT_GRAY;
        setComponentProperty(btn, c -> c.setBackground(defaultBorderColor) );
        setBooleanProperty(btn, AbstractButton::setFocusPainted, false);
        setBooleanProperty(btn, AbstractButton::setBorderPainted, false);
        txtNgayNhap.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtNgayNhap.getText().equals("Ngày/Tháng/Năm")) {
                    txtNgayNhap.setText("");
                    txtNgayNhap.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtNgayNhap.getText().isEmpty()) {
                    txtNgayNhap.setForeground(Color.GRAY);
                    txtNgayNhap.setText("Ngày/Tháng/Năm");
                }
            }
        });
    }

    private void fillTable() {
        model.setRowCount(0);

        try {
            List<SanPham> list = dao.selectAll();
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào để hiển thị!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (SanPham sp : list) {
                Object[] row = {
                        sp.getMaSP(),
                        sp.getTenSP(),
                        sp.getSoLuong(),
                        sp.getGiaNhap(),
                        sp.getGiaBan(),
                        sp.getNgayNhap() != null ? sdf.format(sp.getNgayNhap()) : "",
                        sp.getAnh()
                };
                model.addRow(row); // Thêm dữ liệu mới vào bảng
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        return !txtTenSP.getText().isEmpty() &&
                validateNumber(txtSL.getText(), "Số lượng") &&
                validateNumber(txtGiaNhap.getText(), "Giá nhập") &&
                validateNumber(txtGiaBan.getText(), "Giá bán") &&
                validateDate(txtNgayNhap.getText());
    }

    private boolean validateNumber(String value, String fieldName) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, fieldName + " phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean validateDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ! Định dạng đúng: dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private SanPham getForm() throws ParseException {

        SanPham sp = new SanPham();

        sp.setTenSP(txtTenSP.getText());
        sp.setSoLuong(Integer.parseInt(txtSL.getText()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sp.setNgayNhap(sdf.parse(txtNgayNhap.getText()));

        sp.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
        sp.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
        sp.setGhiChu(txtGhiChu.getText());
        sp.setAnh(lblAnh.getToolTipText());

        return sp;
    }

    private void them() {
        if (!validateFields()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            SanPham sp = getForm();
            dao.insert(sp);
            fillTable();
            moi();
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi thêm sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void xoa() {
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer masp = (Integer) tblSanPham.getValueAt(selectedRow, 0);
        if(!Auth.isManager()){
            MsgBox.alert(this,"Bạn không có quyền xóa sản phẩm!");
        }else {

            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    dao.delete(masp);
                    fillTable();  // Cập nhật lại bảng
                    moi();  // Làm mới form
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi xóa sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }

    private void sua() {
        if (!validateFields()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            SanPham sp = getForm();
            sp.setMaSP((Integer) tblSanPham.getValueAt(selectedRow, 0));
            dao.update(sp);
            fillTable();
            MsgBox.alert(this, "Cập nhật sản phẩm thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi cập nhật sản phẩm!");
            e.printStackTrace();
        }
    }

    private void moi() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtSL.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtNgayNhap.setText("");
        txtGhiChu.setText("");
        lblAnh.setIcon(null);
        lblAnh.setToolTipText(null);
        this.row = -1;
        updateStatus();
    }

    private void chonAnh() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Lấy kích thước của lblAnh
                int width = lblAnh.getWidth();
                int height = lblAnh.getHeight();

                // Nếu kích thước chưa có (chưa vẽ giao diện), dùng giá trị mặc định
                if (width == 0 || height == 0) {
                    width = 210;  // Rộng mặc định từ setBounds
                    height = 260; // Cao mặc định từ setBounds
                }

                // Tải và điều chỉnh kích thước ảnh
                ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                // Đặt ảnh vào lblAnh
                lblAnh.setIcon(scaledIcon);
                lblAnh.setToolTipText(selectedFile.getName()); // Lưu tên file
            } catch (Exception e) {
                MsgBox.alert(this, "Lỗi khi tải ảnh: " + e.getMessage());
            }
        }
    }

    private void searchAndUpdateTable() {
        String keyword = txtTimKiem.getText().trim();
        try {
            List<SanPham> list = dao.search(keyword);
            model.setRowCount(0);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for (SanPham sp : list) {
                    Object[] row = {
                            sp.getMaSP(),
                            sp.getTenSP(),
                            sp.getSoLuong(),
                            sp.getGiaNhap(),
                            sp.getGiaBan(),
                            sp.getNgayNhap() != null ? sdf.format(sp.getNgayNhap()) : "",
                            sp.getAnh()
                    };
                    model.addRow(row);
                }
        } catch (Exception e) {
            MsgBox.alert(this,"Lỗi tìm kiếm");
            e.printStackTrace();
        }
    }

    void setForm(SanPham sp) {
        txtMaSP.setText(String.valueOf(sp.getMaSP()));
        txtTenSP.setText(sp.getTenSP());
        txtSL.setText(String.valueOf(sp.getSoLuong()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtNgayNhap.setText(sp.getNgayNhap() != null ? sdf.format(sp.getNgayNhap()) : "");
        txtGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
        txtGiaBan.setText(String.valueOf(sp.getGiaBan()));
        txtGhiChu.setText(sp.getGhiChu());
        int width = lblAnh.getWidth();
        int height = lblAnh.getHeight();
        if (width == 0 || height == 0) {
            width = 210;  // Kích thước mặc định từ setBounds
            height = 260;
        }

        if (sp.getAnh() != null) {
            lblAnh.setToolTipText(sp.getAnh());

            URL url = getClass().getResource("/IMG/" + sp.getAnh());
            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                lblAnh.setIcon(new ImageIcon(scaledImage));
            } else {
                lblAnh.setIcon(null);
                System.out.println("Không tìm thấy ảnh: " + sp.getAnh());
            }
        } else {
            lblAnh.setIcon(null);
            lblAnh.setToolTipText(null);
        }
    }

    void edit() {
        int masp = (Integer) tblSanPham.getValueAt(this.row, 0);
        SanPham sp = dao.selectByID(masp);
        this.setForm(sp);
        updateStatus();
    }

    void first() {
        if (tblSanPham.getRowCount() > 0) {
            row = 0;
            tblSanPham.setRowSelectionInterval(row, row);
            edit();
        }
    }

    void prev() {
        if (row > 0) {
            row--;
            tblSanPham.setRowSelectionInterval(row, row);
            edit();
        }
    }

    void next() {
        if (row < tblSanPham.getRowCount() - 1) {
            row++;
            tblSanPham.setRowSelectionInterval(row, row);
            edit();
        }
    }

    void last() {
        if (tblSanPham.getRowCount() > 0) {
            row = tblSanPham.getRowCount() - 1;
            tblSanPham.setRowSelectionInterval(row, row);
            edit();
        }
    }

    void updateStatus(){
        boolean edit=(this.row>=0);
        boolean first=(this.row==0);
        boolean last=(this.row==tblSanPham.getRowCount()-1);
        txtMaSP.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Forgot Password");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 740);
            frame.setLayout(null);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            QLSanPham panel = new QLSanPham();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}