package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

        JPanel pnlTonKho = new JPanel(null);
        tblTonKho = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"TÊN SẢN PHẨM", "LOẠI SẢN PHẨM", "ĐÃ BÁN", "CÒN LẠI TRONG KHO"}
        ));
        JScrollPane scrollPaneTK = new JScrollPane(tblTonKho);
        scrollPaneTK.setBounds(10, 10, 926, 285);
        pnlTonKho.add(scrollPaneTK);

        JPanel pnlThanhTich = new JPanel(null);
        tblThanhTich = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"MÃ NV", "TÊN NV", "SỐ ĐƠN ĐÃ BÁN"}
        ));
        JScrollPane scrollPaneTT = new JScrollPane(tblThanhTich);
        scrollPaneTT.setBounds(10, 10, 926, 285);
        pnlThanhTich.add(scrollPaneTT);

        tabs.add(pnlDoanhThu, "Doanh thu theo tháng");
        tabs.add(pnlBanChay, "Bán chạy");
        tabs.add(pnlTonKho, "Tồn kho");
        tabs.add(pnlThanhTich, "Thành tích nhân viên");

        add(lblDoanhThu);
        add(pnlCapNhat);
        add(lblBoLoc);
        add(pnlLoc);
        add(tabs);
    }

    public static void highIcon(JLabel... labels) {
        for (JLabel label : labels) {
            label.setHorizontalTextPosition(SwingConstants.CENTER);
            label.setVerticalTextPosition(SwingConstants.BOTTOM);
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

            ThongKe panel = new ThongKe();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}

