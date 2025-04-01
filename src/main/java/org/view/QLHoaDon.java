package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static org.view.QLNhanVien.*;
import static org.view.QLNhanVien.setBooleanProperty;

public class QLHoaDon extends JPanel {
    private JTable tableHoaDon, tableChiTiet;
    private JTextField txtMaHD, txtMaKH, txtNgayLap, txtTongTien;
    private JButton btnThem, btnPrint, btnPrev, btnNext, btnLast, btnFirst, btnThemSP, btnLuuHoaDon;

    public QLHoaDon() {
        setLayout(null);
        setSize(986, 713);
        setVisible(true);

        Font font= new Font("Arial",Font.BOLD,14);


        JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN");
        lblTitle.setFont(lblTitle.getFont().deriveFont(30f));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(50,15,500,30);

        JPanel panelTop = new JPanel(null);
        panelTop.setBorder(new LineBorder(Color.BLUE,1));
        panelTop.setBounds(20, 60, 946, 200);

        // Cột 1: 3 dòng thông tin
        JLabel lblMaHD = new JLabel("Mã HD");
        txtMaHD = new JTextField();
        lblMaHD.setBounds(20, 10, 100, 30);
        txtMaHD.setBounds(20, 40, 250, 30);

        JLabel lblMaKH = new JLabel("Mã KH");
        txtMaKH = new JTextField();
        lblMaKH.setBounds(20, 70, 100, 30);
        txtMaKH.setBounds(20, 100, 250, 30);

        JLabel lblNgayLap = new JLabel("Ngày lập:");
        txtNgayLap = new JTextField();
        lblNgayLap.setBounds(330, 10, 100, 30);
        txtNgayLap.setBounds(330, 40, 250, 30);

        JLabel lblTongTien = new JLabel("Tổng tiền:");
        txtTongTien = new JTextField();
        lblTongTien.setBounds(330, 70, 100, 30);
        txtTongTien.setBounds(330, 100, 250, 30);

        JPanel pnlBtn1 = new JPanel();
        pnlBtn1.setLayout(new GridLayout(2,1,10,30));
        pnlBtn1.setBounds(650,40,250,120);
        btnPrint = new JButton(new ImageIcon("img/save.png"));
        btnThem = new JButton(new ImageIcon("img/add.png"));
        pnlBtn1.add(btnThem);
        pnlBtn1.add(btnPrint);


        JPanel pnlBtn2 = new JPanel();
        pnlBtn2.setLayout(new GridLayout(1,4,60,10));
        pnlBtn2.setBounds(20,160,560,30);

        btnFirst = new JButton(new ImageIcon("img/first.png"));
        btnPrev = new JButton(new ImageIcon("img/left.png"));
        btnNext = new JButton(new ImageIcon("img/right.png"));
        btnLast = new JButton(new ImageIcon("img/last.png"));
        pnlBtn2.add(btnFirst);
        pnlBtn2.add(btnPrev);
        pnlBtn2.add(btnNext);
        pnlBtn2.add(btnLast);
        addCompoment(panelTop,lblMaHD,lblMaKH,lblNgayLap,lblTongTien,txtMaHD,txtMaKH,
                txtNgayLap,txtTongTien,pnlBtn1,pnlBtn2);

        JPanel pnlTable = new JPanel(null);
        pnlTable.setBounds(20,280,946,403);
        pnlTable.setBorder(new LineBorder(Color.BLUE,1));

        JLabel lblTimKiem= new JLabel("Tìm kiếm:");
        lblTimKiem.setBounds(15,10,100,30);
        JTextField txtTimKiem= new JTextField();
        txtTimKiem.setBounds(100,10,455,30);

        tableHoaDon = new JTable(new DefaultTableModel( new Object[][]{},
                new String[]{"Mã HD", "Mã KH", "Mã NV", "Ngày lập", "Tổng tiền"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });
        JScrollPane scrollHoaDon = new JScrollPane(tableHoaDon);
        scrollHoaDon.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        scrollHoaDon.setBounds(10, 40, 555, 353);

        tableChiTiet = new JTable(new DefaultTableModel( new Object[][]{},
                new String[]{"Tên SP", "Số lượng","Đơn giá", "Thành tiền"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });

        JScrollPane scrollChiTiet = new JScrollPane(tableChiTiet);
        scrollChiTiet.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        scrollChiTiet.setBounds(575, 10, 351, 343);
        pnlTable.add(scrollHoaDon);
        pnlTable.add(scrollChiTiet);
        pnlTable.add(lblTimKiem);
        pnlTable.add(txtTimKiem);

        btnThemSP=new JButton("Thêm sản phẩm");
        btnThemSP.setBounds(590,363,150,30);
        btnThemSP.addActionListener(e -> new ChiTietHoaDon());

        btnLuuHoaDon= new JButton("Lưu thay đổi");
        btnLuuHoaDon.setBounds(760,363,150,30);
        pnlTable.add(btnThemSP);
        pnlTable.add(btnLuuHoaDon);
        setFontForTextFields(font,lblMaHD,lblMaKH,lblNgayLap,lblTongTien,lblTimKiem);
        AbstractButton[] btn={btnPrev,btnThemSP,btnLast,btnNext,btnLuuHoaDon,btnFirst,btnPrint,btnThem};
        Color defaultBorderColor = Color.LIGHT_GRAY;
        setComponentProperty(btn, c -> c.setBackground(defaultBorderColor) );
        setBooleanProperty(btn, AbstractButton::setFocusPainted, false);
        setBooleanProperty(btn, AbstractButton::setBorderPainted, false);

        add(lblTitle);
        add(panelTop);
        add(pnlTable);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Forgot Password");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 740);
            frame.setLayout(null);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            QLHoaDon panel = new QLHoaDon();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}

