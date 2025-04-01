package org.view;

import org.DAO.ThongKeDAO;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

import static org.view.QLNhanVien.addCompoment;
import static org.view.QLNhanVien.setFontForTextFields;

public class ThongKe extends JPanel {
    private JLabel lblDoanhThuThang, lblDoanhThuNam, lblDoanhThuTong;
    private JComboBox<String> cboThang, cboNam;
    private JButton btnXoaLoc;
    private JTable tblDoanhThu, tblBanChay, tblTonKho, tblThanhTich;

    public ThongKe() {
        setSize(986, 713);
        setLayout(null);

        Font font= new Font("Arial",Font.BOLD,14);

        Font font1 = new Font("Arial", Font.BOLD, 16);

        JLabel lblDoanhThu = new JLabel("DOANH THU");
        lblDoanhThu.setFont(lblDoanhThu.getFont().deriveFont(20f));
        lblDoanhThu.setForeground(Color.BLUE);
        lblDoanhThu.setBounds(20, 0, 200, 30);

        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE, 1));
        pnlCapNhat.setBounds(20, 30, 946, 190);

        lblDoanhThuThang = new JLabel("Tháng: 0VND", SwingConstants.CENTER);
        lblDoanhThuThang.setBorder(new LineBorder(Color.BLACK, 3));
        lblDoanhThuThang.setBounds(30, 20, 250, 150);
        lblDoanhThuThang.setIcon(new ImageIcon("img/coins.png"));
        lblDoanhThuNam = new JLabel("Năm: 0VND", SwingConstants.CENTER);
        lblDoanhThuNam.setBorder(new LineBorder(Color.BLACK, 3));
        lblDoanhThuNam.setBounds(348, 20, 250, 150);
        lblDoanhThuNam.setIcon(new ImageIcon("img/coins.png"));
        lblDoanhThuTong = new JLabel("Tổng: 0VND", SwingConstants.CENTER);
        lblDoanhThuTong.setBorder(new LineBorder(Color.BLACK, 3));
        lblDoanhThuTong.setBounds(666, 20, 250, 150);
        lblDoanhThuTong.setIcon(new ImageIcon("img/coins.png"));
        highIcon(lblDoanhThuThang, lblDoanhThuNam, lblDoanhThuTong);

        addCompoment(pnlCapNhat, lblDoanhThuThang, lblDoanhThuNam, lblDoanhThuTong);
        setFontForTextFields(font1, lblDoanhThuNam, lblDoanhThuTong, lblDoanhThuThang);

        JLabel lblBoLoc = new JLabel("BỘ LỌC");
        lblBoLoc.setFont(lblBoLoc.getFont().deriveFont(14f));
        lblBoLoc.setForeground(Color.BLUE);
        lblBoLoc.setBounds(20, 220, 200, 30);

        JPanel pnlLoc = new JPanel(null);
        pnlLoc.setBorder(new LineBorder(Color.BLUE, 1));
        pnlLoc.setBounds(20, 250, 946, 100);

        JPanel pnlLocThang = new JPanel(null);
        pnlLocThang.setBorder(new LineBorder(Color.BLACK, 1));
        pnlLocThang.setBounds(20, 20, 350, 60);
        JLabel lblTitleThang = new JLabel("Tháng: ");
        lblTitleThang.setFont(font);
        lblTitleThang.setBounds(20, 15, 100, 30);
        cboThang = new JComboBox<>();
        cboThang.setBounds(100, 15, 230, 30);
        pnlLocThang.add(lblTitleThang);
        pnlLocThang.add(cboThang);

        JPanel pnlLocNam = new JPanel(null);
        pnlLocNam.setBorder(new LineBorder(Color.BLACK, 1));
        pnlLocNam.setBounds(390, 20, 350, 60);
        JLabel lblTitleNam = new JLabel("Năm: ");
        lblTitleNam.setFont(font);
        lblTitleNam.setBounds(20, 15, 100, 30);
        cboNam = new JComboBox<>();
        cboNam.setBounds(100, 15, 230, 30);
        pnlLocNam.add(lblTitleNam);
        pnlLocNam.add(cboNam);

        btnXoaLoc = new JButton("Xoá bộ lọc");
        btnXoaLoc.setBounds(760, 25, 166, 50);

        addCompoment(pnlLoc, pnlLocThang, pnlLocNam, btnXoaLoc);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(10, 360, 946, 330);


        JPanel pnlDoanhThu = new JPanel(null);
        tblDoanhThu = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"TÊN SẢN PHẨM", "ĐÃ BÁN", "TỔNG TIỀN BÁN", "GIÁ GỐC", "LỢI NHUẬN"}
        ));
        JScrollPane scrollPaneDT = new JScrollPane(tblDoanhThu);
        scrollPaneDT.setBounds(10, 10, 926, 285);
        pnlDoanhThu.add(scrollPaneDT);
        tabs.add(pnlDoanhThu, "Doanh thu theo tháng");

        JPanel pnlBanChay = new JPanel(null);
        tblBanChay = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"TÊN SẢN PHẨM", "LOẠI SẢN PHẨM", "ĐÃ BÁN", "CÒN LẠI TRONG KHO"}
        ));
        JScrollPane scrollPaneBC = new JScrollPane(tblBanChay);
        scrollPaneBC.setBounds(10, 10, 926, 285);
        pnlBanChay.add(scrollPaneBC);


        JPanel pnlThanhTich = new JPanel(null);
        tblThanhTich = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"MÃ NV", "TÊN NV", "SỐ ĐƠN ĐÃ BÁN"}
        ));
        JScrollPane scrollPaneTT = new JScrollPane(tblThanhTich);
        scrollPaneTT.setBounds(10, 10, 926, 285);
        pnlThanhTich.add(scrollPaneTT);

        tabs.add(pnlDoanhThu, "Doanh thu theo tháng");
        tabs.add(pnlBanChay, "Sản phẩm bán chạy");
        tabs.add(pnlThanhTich, "Thành tích nhân viên");

        add(lblDoanhThu);
        add(pnlCapNhat);
        add(lblBoLoc);
        add(pnlLoc);
        add(tabs);
        fillCboNam();

        cboNam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fillCboThang(); // Cập nhật cboThang dựa trên năm được chọn
                    updateData();   // Cập nhật dữ liệu giao diện
                }
            }
        });

        // Thêm sự kiện cho cboThang để cập nhật dữ liệu
        cboThang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateData(); // Chỉ cập nhật dữ liệu khi thay đổi tháng
                }
            }
        });

        // Sự kiện cho nút "Xóa bộ lọc"
        btnXoaLoc.addActionListener(e -> resetFilter());


    }
    ThongKeDAO dao= new ThongKeDAO();

    private void fillCboNam() {
        try {
            List<Integer> namList = dao.getDanhSachNam();
            cboNam.removeAllItems();

            for (int nam : namList) {
                cboNam.addItem(String.valueOf(nam));
            }

            if (cboNam.getItemCount() > 0) {
                cboNam.setSelectedIndex(0); // Chọn năm đầu tiên
                fillCboThang();             // Điền cboThang cho năm đầu tiên
                updateData();               // Cập nhật dữ liệu ban đầu
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách năm!");
        }
    }

    // Điền danh sách tháng dựa trên năm được chọn
    private void fillCboThang() {
        try {
            if (cboNam.getSelectedItem() != null) {
                int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
                List<Integer> thangList = dao.getDanhSachThang(nam);
                cboThang.removeAllItems();

                for (int thang : thangList) {
                    cboThang.addItem(String.valueOf(thang));
                }

                if (cboThang.getItemCount() > 0) {
                    cboThang.setSelectedIndex(0); // Chọn tháng đầu tiên
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách tháng!");
        }
    }


    public static void highIcon(JLabel... labels) {
        for (JLabel label : labels) {
            label.setHorizontalTextPosition(SwingConstants.CENTER);
            label.setVerticalTextPosition(SwingConstants.BOTTOM);
        }
    }
    private void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        try {
            int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
            int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
            List<Object[]> list = dao.getDoanhThuChiTiet(thang, nam); // Gọi đúng phương thức
            for (Object[] row : list) {
                model.addRow(row); // Thêm từng dòng dữ liệu vào bảng
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu doanh thu!");
        }
    }
    private void capNhatDoanhThu() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        try {
            int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
            int nam = Integer.parseInt(cboNam.getSelectedItem().toString());

            // Doanh thu tháng (chỉ trả về 1 cột: DoanhThuThang)
            List<Object[]> dtThangList = dao.getDoanhThuThang(thang, nam);
            double doanhThuThang = 0;
            if (!dtThangList.isEmpty() && dtThangList.get(0).length >= 1) {
                doanhThuThang = ((Number) dtThangList.get(0)[0]).doubleValue(); // Index 0 là DoanhThuThang
            }
            lblDoanhThuThang.setText("Tháng: " + df.format(doanhThuThang) + " VNĐ");

            // Doanh thu năm (chỉ trả về 1 cột: DoanhThuNam)
            List<Object[]> dtNamList = dao.getDoanhThuNam(nam);
            double doanhThuNam = 0;
            if (!dtNamList.isEmpty() && dtNamList.get(0).length >= 1) {
                doanhThuNam = ((Number) dtNamList.get(0)[0]).doubleValue(); // Index 0 là DoanhThuNam
            }
            lblDoanhThuNam.setText("Năm: " + df.format(doanhThuNam) + " VNĐ");

            // Doanh thu tổng (chỉ trả về 1 cột: DoanhThuTong)
            List<Object[]> dtTongList = dao.getDoanhThuTong();
            double doanhThuTong = 0;
            if (!dtTongList.isEmpty() && dtTongList.get(0).length >= 1) {
                doanhThuTong = ((Number) dtTongList.get(0)[0]).doubleValue(); // Index 0 là DoanhThuTong
            }
            lblDoanhThuTong.setText("Tổng: " + df.format(doanhThuTong) + " VNĐ");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật doanh thu: " + e.getMessage());
        }
    }
    private void fillTableSanPhamBanChay() {
        DefaultTableModel model = (DefaultTableModel) tblBanChay.getModel();
        model.setRowCount(0);
        try {
            int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
            int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
            List<Object[]> list = dao.getSanPhamBanChay(thang, nam);
            for (Object[] row : list) {
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu sản phẩm bán chạy!");
        }
    }
    private void fillTableThanhTich() {
        DefaultTableModel model = (DefaultTableModel) tblThanhTich.getModel();
        model.setRowCount(0);
        try {
            int thang = Integer.parseInt(cboThang.getSelectedItem().toString());
            int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
            List<Object[]> list = dao.getThanhTichNhanVien(thang, nam);
            for (Object[] row : list) {
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu thành tích nhân viên!");
        }
    }
    private void updateData() {
        fillTableDoanhThu();
        fillTableSanPhamBanChay();
        fillTableThanhTich();
        capNhatDoanhThu();
    }
    private void resetFilter() {
        if (cboNam.getItemCount() > 0) {
            cboNam.setSelectedIndex(0); // Reset về năm đầu tiên
            fillCboThang();             // Cập nhật lại tháng
        }
        updateData();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Forgot Password");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 740);
            frame.setLayout(null);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            ThongKe panel = new ThongKe();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}

