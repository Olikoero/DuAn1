package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static org.view.QLNhanVien.addCompoment;

public class QLSanPham extends JPanel {
    private JLabel lblAnh;
    JFileChooser fileChooser;
    private JButton btnMoi,btnXoa,btnSua,btnThem,btnFirst,btnPrev,btnNext,btnLast;
    public QLSanPham(){
        setSize(986, 713);
        setLayout(null);

        JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẦM");
        lblTitle.setFont(lblTitle.getFont().deriveFont(30f));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(50,15,500,30);

        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE,1));
        pnlCapNhat.setBounds(20,60,946,320);

        JLabel lblMaSP= new JLabel("Mã SP");
        lblMaSP.setBounds(10,10,100,30);
        JTextField txtMaSP= new JTextField();
        txtMaSP.setBounds(10,40,200,30);

        JLabel lblTenSP= new JLabel("Tên SP");
        lblTenSP.setBounds(10,70,100,30);
        JTextField txtTenSP= new JTextField();
        txtTenSP.setBounds(10,100,200,30);

        JLabel lblSL= new JLabel("Số lượng");
        lblSL.setBounds(10,130,100,30);
        JTextField txtSL= new JTextField();
        txtSL.setBounds(10,160,200,30);

        JLabel lblGiaNhap= new JLabel("Giá nhập");
        lblGiaNhap.setBounds(10,190,100,30);
        JTextField txtGiaNhap= new JTextField();
        txtGiaNhap.setBounds(10,220,200,30);

        JLabel lblGiaBan= new JLabel("Giá bán");
        lblGiaBan.setBounds(250,10,100,30);
        JTextField txtGiaBan= new JTextField();
        txtGiaBan.setBounds(250,40,200,30);

        JLabel lblNgayNhap= new JLabel("Ngày nhập");
        lblNgayNhap.setBounds(250,70,100,30);
        JTextField txtNgayNhap= new JTextField();
        txtNgayNhap.setBounds(250,100,200,30);

        JLabel lblGhiChu= new JLabel("Ghi chú");
        lblGhiChu.setBounds(250,130,100,30);
        JTextArea txtGhiChu= new JTextArea();
        txtGhiChu.setBorder(new LineBorder(Color.BLACK,1));
        txtGhiChu.setBounds(250,160,200,90);



        JLabel lblHinh = new JLabel("Hình ảnh");
        lblHinh.setBounds(490, 10, 100, 30);

        lblAnh = new JLabel();
        lblAnh.setBounds(490, 40, 210, 270);
        lblAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        fileChooser = new JFileChooser();

        JPanel pnlBtn1 = new JPanel();
        pnlBtn1.setLayout(new GridLayout(2,2,10,30));
        pnlBtn1.setBounds(720,40,206,270);
        btnMoi = new JButton(new ImageIcon("img/new.png"));
        btnXoa = new JButton(new ImageIcon("img/delete.png"));
        btnSua = new JButton(new ImageIcon("img/save.png"));
        btnThem = new JButton(new ImageIcon("img/add.png"));
        pnlBtn1.add(btnMoi);
        pnlBtn1.add(btnXoa);
        pnlBtn1.add(btnSua);
        pnlBtn1.add(btnThem);

        JPanel pnlBtn2 = new JPanel();
        pnlBtn2.setLayout(new GridLayout(1,4,10,10));
        pnlBtn2.setBounds(10,280,440,30);
        btnFirst = new JButton(new ImageIcon("img/first.png"));
        btnPrev = new JButton(new ImageIcon("img/left.png"));
        btnNext = new JButton(new ImageIcon("img/right.png"));
        btnLast = new JButton(new ImageIcon("img/last.png"));
        pnlBtn2.add(btnFirst);
        pnlBtn2.add(btnPrev);
        pnlBtn2.add(btnNext);
        pnlBtn2.add(btnLast);

        addCompoment(pnlCapNhat,lblMaSP,txtMaSP,lblTenSP,txtTenSP,lblSL,txtSL,lblGiaNhap,txtGiaNhap,
                lblGiaBan,txtGiaBan,lblNgayNhap,txtNgayNhap,txtGhiChu,lblGhiChu,lblHinh,lblAnh,pnlBtn1,pnlBtn2);

        //danhsach
        JPanel pnlDanhSach = new JPanel(null);

        JLabel lblTimKiem= new JLabel("Tìm kiếm:");
        lblTimKiem.setBounds(10,10,100,30);
        JTextField txtTimKiem= new JTextField();
        txtTimKiem.setBounds(140,10,796,30);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE,1));
        pnlDanhSach.setBounds(20,390,946,290);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE,1));
        JTable tblNhanVien = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"MÃ SP", "TÊN SP", "SỐ LƯỢNG", "GIÁ NHẬP", "GIÁ BÁN","NGÀY NHẬP","ẢNH"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });
        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        scrollPane.setBounds(10, 50, 926, 220);
        pnlDanhSach.add(scrollPane);
        pnlDanhSach.add(lblTimKiem);
        pnlDanhSach.add(txtTimKiem);

        add(lblTitle);
        add(pnlCapNhat);
        add(pnlDanhSach);

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
