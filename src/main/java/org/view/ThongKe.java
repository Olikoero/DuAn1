package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static org.view.QLNhanVien.addCompoment;

public class ThongKe extends JPanel {
    public ThongKe(){
        setSize(986, 713);
        setLayout(null);

        Font font=new Font("Arial",Font.BOLD,30);

        JLabel lblDoanhThu = new JLabel("DOANH THU");
        lblDoanhThu.setFont(lblDoanhThu.getFont().deriveFont(20f));
        lblDoanhThu.setForeground(Color.BLUE);
        lblDoanhThu.setBounds(20,0,200,30);

        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE,1));
        pnlCapNhat.setBounds(20,30,946,190);

        JLabel lblDoanhThuThang = new JLabel("Tháng: 0VND",SwingConstants.CENTER);
        lblDoanhThuThang.setBorder(new LineBorder(Color.BLACK,3));
        lblDoanhThuThang.setBounds(30,20,200,150);
        JLabel lblDoanhThuNam = new JLabel("Năm: 0VND",SwingConstants.CENTER);
        lblDoanhThuNam.setBorder(new LineBorder(Color.BLACK,3));
        lblDoanhThuNam.setBounds(375,20,200,150);
        JLabel lblDoanhThuTong = new JLabel("Tổng: 0VND",SwingConstants.CENTER);
        lblDoanhThuTong.setBorder(new LineBorder(Color.BLACK,3));
        lblDoanhThuTong.setBounds(715,20,200,150);

        addCompoment(pnlCapNhat,lblDoanhThuThang,lblDoanhThuNam,lblDoanhThuTong);

        JLabel lblBoLoc = new JLabel("BỘ LỌC");
        lblBoLoc.setFont(lblBoLoc.getFont().deriveFont(14f));
        lblBoLoc.setForeground(Color.BLUE);
        lblBoLoc.setBounds(20,220,200,30);

        JPanel pnlLoc= new JPanel(null);
        pnlLoc.setBorder(new LineBorder(Color.BLUE,1));
        pnlLoc.setBounds(20,250,946,100);

        JPanel pnlLocThang = new JPanel(null);
        pnlLocThang.setBorder(new LineBorder(Color.BLACK,1));
        pnlLocThang.setBounds(20,10,350,80);
        JLabel lblTitleThang = new JLabel("Theo tháng: ");
        lblTitleThang.setBounds(20,20,80,40);
        JComboBox<String> cboThang = new JComboBox<>();
        cboThang.setBounds(100,20,230,40);
        pnlLocThang.add(lblTitleThang);
        pnlLocThang.add(cboThang);

        JPanel pnlLocNam = new JPanel(null);
        pnlLocNam.setBorder(new LineBorder(Color.BLACK,1));
        pnlLocNam.setBounds(390,10,350,80);
        JLabel lblTitleNam = new JLabel("Theo năm: ");
        lblTitleNam.setBounds(20,20,80,40);
        JComboBox<String> cboNam = new JComboBox<>();
        cboNam.setBounds(100,20,230,40);
        pnlLocNam.add(lblTitleNam);
        pnlLocNam.add(cboNam);

        JButton btnXoaLoc=new JButton("Xoá bộ lọc");
        btnXoaLoc.setBounds(760,25,166,50);

        addCompoment(pnlLoc,pnlLocThang,pnlLocNam,btnXoaLoc);

        JTabbedPane tabs=new JTabbedPane();
        tabs.setBounds(20,360,946,330);


        JPanel pnlDoanhThu = new JPanel(null);
        JTable tblBangDiem = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"TÊN SẢN PHẨM", "ĐÃ BÁN", "TỔNG TIỀN BÁN", "GIÁ GỐC","LỢI NHUẬN"}
        ));
        JScrollPane scrollPaneDT = new JScrollPane(tblBangDiem);
        scrollPaneDT.setBounds(10, 10, 926, 290);
        pnlDoanhThu.add(scrollPaneDT);
        tabs.add(pnlDoanhThu,"Doanh thu theo tháng");

        JPanel pnlBanChay = new JPanel(null);
        JTable tblBanChay = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"TÊN SẢN PHẨM", "LOẠI SẢN PHẨM", "ĐÃ BÁN", "CÒN LẠI TRONG KHO"}
        ));
        JScrollPane scrollPaneBC = new JScrollPane(tblBanChay);
        scrollPaneBC.setBounds(10, 10, 926, 290);
        pnlBanChay.add(scrollPaneBC);

        JPanel pnlTonKho = new JPanel(null);
        JTable tblTonKho = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"TÊN SẢN PHẨM", "LOẠI SẢN PHẨM", "ĐÃ BÁN", "CÒN LẠI TRONG KHO"}
        ));
        JScrollPane scrollPaneTK = new JScrollPane(tblTonKho);
        scrollPaneTK.setBounds(10, 10, 926, 290);
        pnlTonKho.add(scrollPaneTK);

        JPanel pnlThanhTich = new JPanel(null);
        JTable tblThanhTich = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"MÃ NV", "TÊN NV", "SỐ ĐƠN ĐÃ BÁN"}
        ));
        JScrollPane scrollPaneTT = new JScrollPane(tblThanhTich);
        scrollPaneTT.setBounds(10, 10, 926, 290);
        pnlThanhTich.add(scrollPaneTT);

        tabs.add(pnlDoanhThu,"Doanh thu theo tháng");
        tabs.add(pnlBanChay,"Bán chạy");
        tabs.add(pnlTonKho,"Tồn kho");
        tabs.add(pnlThanhTich,"Thành tích nhân viên");

        add(lblDoanhThu);
        add(pnlCapNhat);
        add(lblBoLoc);
        add(pnlLoc);
        add(tabs);
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

