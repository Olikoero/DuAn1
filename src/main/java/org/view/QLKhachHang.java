package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static org.view.QLNhanVien.*;

public class QLKhachHang extends JPanel {
    private JTable tblKhachHang;
    private JTextField txtMaKH, txtHoTen, txtEmail, txtSDT;
    private JButton btnFirst, btnPrev,btnNext,btnLast,btnMoi,btnXoa,btnSua,btnThem;
    public QLKhachHang(){
        setSize(986, 713);
        setLayout(null);
        Font font= new Font("Arial",Font.PLAIN,14);

        //Tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTitle.setFont(lblTitle.getFont().deriveFont(30f));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(50,30,500,50);

        //Tab cập nhật
        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE,1));
        pnlCapNhat.setBounds(20,100,370,443);
        JLabel lblMaKH= new JLabel("Mã khách hàng");
        lblMaKH.setBounds(20, 10, 150, 30);
        txtMaKH = new JTextField();
        txtMaKH.setBounds(20, 40, 330, 30);

        JLabel lblHoTen = new JLabel("Họ và tên ");
        lblHoTen.setBounds(20, 70, 150, 30);
        txtHoTen = new JPasswordField();
        txtHoTen.setBounds(20, 100, 330, 30);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(20, 130, 170, 30);
        txtEmail = new JPasswordField();
        txtEmail.setBounds(20, 160, 330, 30);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setBounds(20, 190, 100, 30);
        txtSDT = new JTextField();
        txtSDT.setBounds(20, 220, 330, 30);

        JPanel pnlBtn2 = new JPanel();
        pnlBtn2.setLayout(new GridLayout(1,4,10,10));
        pnlBtn2.setBounds(10,400,350,30);
        btnFirst = new JButton(new ImageIcon("img/first.png"));
        btnPrev = new JButton(new ImageIcon("img/left.png"));
        btnNext = new JButton(new ImageIcon("img/right.png"));
        btnLast = new JButton(new ImageIcon("img/last.png"));
        pnlBtn2.add(btnFirst);
        pnlBtn2.add(btnPrev);
        pnlBtn2.add(btnNext);
        pnlBtn2.add(btnLast);

        addCompoment(pnlCapNhat,lblMaKH,lblHoTen,lblEmail,lblSDT,pnlBtn2,
                txtMaKH,txtHoTen,txtEmail,txtSDT);


        setFontForTextFields(font,lblMaKH,lblHoTen,lblEmail,lblSDT);

        //Buttons
        JPanel pnlBtn1 = new JPanel();
        pnlBtn1.setLayout(new GridLayout(2,2,30,20));
        pnlBtn1.setBounds(20,553,370,130);
        btnMoi = new JButton(new ImageIcon("img/new.png"));
        btnXoa = new JButton(new ImageIcon("img/delete.png"));
        btnSua = new JButton(new ImageIcon("img/save.png"));
        btnThem = new JButton(new ImageIcon("img/add.png"));
        pnlBtn1.add(btnMoi);
        pnlBtn1.add(btnXoa);
        pnlBtn1.add(btnSua);
        pnlBtn1.add(btnThem);

        AbstractButton[] components = {btnMoi, btnXoa,btnSua,btnThem,btnFirst,btnPrev,btnNext,btnLast};
        setBooleanProperty(components, AbstractButton::setFocusPainted, false);
        setBooleanProperty(components, AbstractButton::setBorderPainted, false);
        setComponentProperty(components, c -> c.setBackground(Color.LIGHT_GRAY) );


        //tab danh sách
        JPanel pnlDanhSach = new JPanel(null);
        pnlDanhSach.setBounds(410,100,556,583);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE,1));
        tblKhachHang = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"MÃ KHÁCH HÀNG", "HỌ VÀ TÊN", "EMAIL", "SỐ ĐIỆN THOẠI"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });
        JScrollPane scrollPane = new JScrollPane(tblKhachHang);
        scrollPane.setBounds(10, 10, 536, 563);
        pnlDanhSach.add(scrollPane);

        add(pnlCapNhat);
        add(pnlBtn1);
        add(pnlDanhSach);
        add(lblTitle);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Forgot Password");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 740);
            frame.setLayout(null);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            QLKhachHang panel = new QLKhachHang();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
