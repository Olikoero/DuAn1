package org.view;

import org.DAO.SanPhamDAO;
import org.Entity.SanPham;
import org.util.MsgBox;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class ChiTietHoaDon extends JFrame {
    private JTextField txtSearch;
    private JTable tableSanPham;
    private JButton btnThem;
    public ChiTietHoaDon() {
        setTitle("Thêm Sản Phẩm");
        setSize(600, 450);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblSearch = new JLabel("Tìm kiếm:");
        txtSearch = new JTextField();
        lblSearch.setBounds(20, 20, 80, 25);
        txtSearch.setBounds(100, 20, 460, 25);

        tableSanPham = new JTable(new DefaultTableModel( new Object[][]{},
                new String[]{"Mã SP", "Tên SP", "Giá"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollSanPham = new JScrollPane(tableSanPham);
        scrollSanPham.setBounds(20, 60, 540, 280);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(243, 350, 100, 50);

        add(lblSearch);
        add(txtSearch);
        add(scrollSanPham);
        add(btnThem);
        setVisible(true);
        fillTableSanPham();
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
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
    }
    SanPhamDAO dao=new SanPhamDAO();
    public void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try {
            List<SanPham> list = dao.selectAll(); // Lấy tất cả sản phẩm
            for (SanPham sp : list) {
                Object[] row = {
                        sp.getMaSP(),
                        sp.getTenSP(),
                        sp.getGiaBan()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchAndUpdateTable() {
        String keyword = txtSearch.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        try {
            List<SanPham> list;
            if (keyword.isEmpty()) {
                list = dao.selectAll();
                for (SanPham sp : list) {
                    Object[] row = {
                            sp.getMaSP(),
                            sp.getTenSP(),
                            sp.getGiaBan()
                    };
                    model.addRow(row);
                }
            } else {
                list = dao.search(keyword);

                for (SanPham sp : list) {
                    Object[] row = {
                            sp.getMaSP(),
                            sp.getTenSP(),
                            sp.getGiaBan()
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi tìm kiếm");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ChiTietHoaDon();
    }
}
