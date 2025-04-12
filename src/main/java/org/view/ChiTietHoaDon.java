package org.view;

import org.DAO.ChiTietHoaDonDAO;
import org.DAO.SanPhamDAO;
import org.Entity.CTHD;
import org.Entity.SanPham;
import org.util.MsgBox;
import org.util.SanPhamThemListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ChiTietHoaDon extends JFrame {
    private JTextField txtSearch;
    private JTable tableSanPham;
    private JButton btnThem;
    private String maHD;
    private ChiTietHoaDonDAO cthdDAO = new ChiTietHoaDonDAO();
    private SanPhamDAO spDAO = new SanPhamDAO();
    private SanPhamThemListener listener; // Listener để thông báo

    public ChiTietHoaDon(String maHD, SanPhamThemListener listener) {
        this.maHD = maHD;
        this.listener = listener; // Nhận listener từ QLHoaDon
        initComponents();
    }

    private void initComponents() {
        setTitle("Thêm Sản Phẩm vào Hóa Đơn: " + maHD);
        setSize(600, 450);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblSearch = new JLabel("Tìm kiếm:");
        txtSearch = new JTextField();
        lblSearch.setBounds(20, 20, 80, 25);
        txtSearch.setBounds(100, 20, 460, 25);

        tableSanPham = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Mã SP", "Tên SP", "Giá"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollSanPham = new JScrollPane(tableSanPham);
        scrollSanPham.setBounds(20, 60, 540, 280);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(243, 350, 100, 50);
        btnThem.addActionListener(e -> themSanPham());

        add(lblSearch);
        add(txtSearch);
        add(scrollSanPham);
        add(btnThem);
        setVisible(true);
        fillTableSanPham();

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { searchAndUpdateTable(); }
            @Override
            public void removeUpdate(DocumentEvent e) { searchAndUpdateTable(); }
            @Override
            public void changedUpdate(DocumentEvent e) { searchAndUpdateTable(); }
        });
    }

    public void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = spDAO.selectAll();
            for (SanPham sp : list) {
                Object[] row = { sp.getMaSP(), sp.getTenSP(), sp.getGiaBan() };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu: " + e.getMessage());
        }
    }

    private void searchAndUpdateTable() {
        String keyword = txtSearch.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = spDAO.search(keyword);
            for (SanPham sp : list) {
                Object[] row = { sp.getMaSP(), sp.getTenSP(), sp.getGiaBan() };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi tìm kiếm: " + e.getMessage());
        }
    }

    private void themSanPham() {
        int selectedRow = tableSanPham.getSelectedRow();
        if (selectedRow >= 0) {
            int maSP = (int) tableSanPham.getValueAt(selectedRow, 0);
            double giaBan = (double) tableSanPham.getValueAt(selectedRow, 2);

            String soLuongStr = JOptionPane.showInputDialog(this, "Nhập số lượng:");
            if (soLuongStr != null && !soLuongStr.trim().isEmpty()) {
                try {
                    int soLuong = Integer.parseInt(soLuongStr);
                    double thanhTien = soLuong * giaBan;

                    CTHD cthd = new CTHD();
                    cthd.setMaHD(maHD);
                    cthd.setMaSP(maSP);
                    cthd.setSoLuong(soLuong);
                    cthd.setThanhTien(thanhTien);

                    cthdDAO.insert(cthd); // Thêm vào database
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");

                    if (listener != null) {
                        listener.onSanPhamAdded(maHD);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm sản phẩm: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm!");
        }
    }
}