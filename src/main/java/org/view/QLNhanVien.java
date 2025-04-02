package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class QLNhanVien extends JPanel {
    private JTable tblNhanVien;
    private JTextField txtMaNV,  txtHoTen, txtEmail;
    private JPasswordField txtMatKhau, txtMatKhau2;
    private JRadioButton rdoTruongPhong, rdoNhanVien;
    private JButton btnThem, btnSua, btnXoa, btnMoi,
            btnFirst, btnPrev, btnNext, btnLast;
    public QLNhanVien(){
        setSize(986, 713);
        setLayout(null);

        Font font= new Font("Arial",Font.BOLD,14);


        //Tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setFont(lblTitle.getFont().deriveFont(30f));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(50,30,500,50);

        //Tab cập nhật
        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE,1));
        pnlCapNhat.setBounds(20,100,370,443);
        JLabel lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setBounds(20, 10, 150, 30);
        txtMaNV = new JTextField();
        txtMaNV.setBounds(20, 40, 330, 30);

        JLabel lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setBounds(20, 70, 150, 30);
        txtMatKhau = new JPasswordField();
        txtMatKhau.setBounds(20, 100, 330, 30);

        JLabel lblMatKhau2 = new JLabel("Xác nhận mật khẩu");
        lblMatKhau2.setBounds(20, 130, 170, 30);
        txtMatKhau2 = new JPasswordField();
        txtMatKhau2.setBounds(20, 160, 330, 30);

        JLabel lblHoTen = new JLabel("Họ và tên");
        lblHoTen.setBounds(20, 190, 100, 30);
        txtHoTen = new JTextField();
        txtHoTen.setBounds(20, 220, 330, 30);

        JLabel lblEmail = new JLabel("Địa chỉ Email");
        lblEmail.setBounds(20, 250, 170, 30);
        txtEmail = new JPasswordField();
        txtEmail.setBounds(20, 280, 330, 30);

        JLabel lblVaiTro = new JLabel("Vai trò");
        lblVaiTro.setBounds(20, 310, 100, 30);
        rdoTruongPhong = new JRadioButton("Trưởng phòng");
        rdoTruongPhong.setBounds(20, 340, 150, 30);
        rdoNhanVien = new JRadioButton("Nhân viên");
        rdoNhanVien.setBounds(200, 340, 150, 30);


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

        addCompoment(pnlCapNhat,lblMaNV,lblMatKhau,lblMatKhau2,lblHoTen,lblVaiTro,lblEmail, pnlBtn2,
                txtMaNV,txtMatKhau,txtMatKhau2,txtHoTen,rdoTruongPhong,rdoNhanVien,txtEmail);


        setFontForTextFields(font,lblMaNV,lblMatKhau, lblMatKhau2,lblHoTen,lblEmail,lblVaiTro,rdoNhanVien,rdoTruongPhong);

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
        tblNhanVien = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"MÃ NV", "MẬT KHẨU", "HỌ VÀ TÊN", "VAI TRÒ", "EMAIL"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });
        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        scrollPane.setBounds(10, 10, 536, 563);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        pnlDanhSach.add(scrollPane);

        add(pnlCapNhat);
        add(pnlBtn1);
        add(pnlDanhSach);
        add(lblTitle);
        setVisible(true);
    }

    public static void setFontForTextFields(Font font, JComponent... fields) {
        for (JComponent field : fields) {
            field.setFont(font);
        }
    }
    public static void addCompoment(JComponent component, JComponent... comps) {
        for (JComponent comp : comps) {
            component.add(comp);
        }
    }
    public static void setBooleanProperty(AbstractButton[] buttons, BiConsumer<AbstractButton, Boolean> setter, boolean value) {
        for (AbstractButton button : buttons) {
            setter.accept(button, value);
        }
    }
    public static void setComponentProperty(JComponent[] components, Consumer<JComponent> setter) {
        for (JComponent component : components) {
            setter.accept(component); // Gọi phương thức setter
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

            QLNhanVien panel = new QLNhanVien();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}

