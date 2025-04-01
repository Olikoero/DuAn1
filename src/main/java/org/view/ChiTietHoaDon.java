package org.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    }

    public static void main(String[] args) {
        new ChiTietHoaDon();
    }
}
