package org.view;

import org.DAO.NhanVienDAO;
import org.Entity.NhanVien;
import org.Entity.SanPham;
import org.util.Auth;
import org.util.MsgBox;
import org.util.XImage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.util.JDBCHelper.getConnection;

public class QLNhanVien extends JPanel {
    private JTable tblNhanVien;
    private JTextField txtMaNV, txtHoTen, txtEmail, txtTimKiem;
    private JPasswordField txtMatKhau, txtMatKhau2;
    private JRadioButton rdoTruongPhong, rdoNhanVien;
    private JButton btnThem, btnSua, btnXoa, btnMoi,
            btnFirst, btnPrev, btnNext, btnLast;
    private DefaultTableModel tableModel;

    public QLNhanVien() {
        setSize(986, 713);
        setLayout(null);

        Font font = new Font("Arial", Font.BOLD, 14);

        // Tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setFont(lblTitle.getFont().deriveFont(30f));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(50, 30, 500, 50);

        // Tab cập nhật
        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE, 1));
        pnlCapNhat.setBounds(20, 100, 370, 443);

        JLabel lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setBounds(20, 10, 150, 30);
        txtMaNV = new JTextField();
        txtMaNV.setBounds(20, 40, 330, 30);
        txtMaNV.setEditable(false); // Không cho chỉnh sửa mã nhân viên

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
        txtEmail = new JTextField();
        txtEmail.setBounds(20, 280, 330, 30);

        JLabel lblVaiTro = new JLabel("Vai trò");
        lblVaiTro.setBounds(20, 310, 100, 30);
        rdoTruongPhong = new JRadioButton("Quản lý");
        rdoTruongPhong.setBounds(20, 340, 150, 30);
        rdoNhanVien = new JRadioButton("Nhân viên");
        rdoNhanVien.setBounds(200, 340, 150, 30);
        rdoNhanVien.setSelected(true);
        ButtonGroup vaiTroGroup = new ButtonGroup();
        vaiTroGroup.add(rdoTruongPhong);
        vaiTroGroup.add(rdoNhanVien);

        JPanel pnlBtn2 = new JPanel();
        pnlBtn2.setLayout(new GridLayout(1, 4, 10, 10));
        pnlBtn2.setBounds(10, 400, 350, 30);
        btnFirst = new JButton(XImage.loadIcon("/IMG/First.png"));
        btnPrev = new JButton(XImage.loadIcon("/IMG/Left.png"));
        btnNext = new JButton(XImage.loadIcon("/IMG/Right.png"));
        btnLast = new JButton(XImage.loadIcon("/IMG/Last.png"));
        pnlBtn2.add(btnFirst);
        pnlBtn2.add(btnPrev);
        pnlBtn2.add(btnNext);
        pnlBtn2.add(btnLast);

        addCompoment(pnlCapNhat, lblMaNV, lblMatKhau, lblMatKhau2, lblHoTen, lblVaiTro, lblEmail, pnlBtn2,
                txtMaNV, txtMatKhau, txtMatKhau2, txtHoTen, rdoTruongPhong, rdoNhanVien, txtEmail);

        setFontForTextFields(font, lblMaNV, lblMatKhau, lblMatKhau2, lblHoTen, lblEmail, lblVaiTro, rdoNhanVien, rdoTruongPhong);

        // Buttons chức năng
        JPanel pnlBtn1 = new JPanel();
        pnlBtn1.setLayout(new GridLayout(2, 2, 30, 20));
        pnlBtn1.setBounds(20, 553, 370, 130);
        btnMoi = new JButton(XImage.loadIcon("/IMG/new.png"));
        btnXoa = new JButton(XImage.loadIcon("/IMG/Delete.png"));
        btnSua = new JButton(XImage.loadIcon("/IMG/Save.png"));
        btnThem = new JButton(XImage.loadIcon("/IMG/add.png"));
        pnlBtn1.add(btnMoi);
        pnlBtn1.add(btnXoa);
        pnlBtn1.add(btnSua);
        pnlBtn1.add(btnThem);

        AbstractButton[] components = {btnMoi, btnXoa, btnSua, btnThem, btnFirst, btnPrev, btnNext, btnLast};
        setBooleanProperty(components, AbstractButton::setFocusPainted, false);
        setBooleanProperty(components, AbstractButton::setBorderPainted, false);
        setComponentProperty(components, c -> c.setBackground(Color.LIGHT_GRAY));

        // Tab danh sách
        JPanel pnlDanhSach = new JPanel(null);
        pnlDanhSach.setBounds(410, 100, 556, 583);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE, 1));

        // Khung tìm kiếm
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        lblTimKiem.setBounds(10, 10, 80, 30);
        lblTimKiem.setFont(font);
        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(90, 10, 456, 30);

        // Bảng danh sách
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"MÃ NV", "MẬT KHẨU", "HỌ VÀ TÊN", "VAI TRÒ", "EMAIL"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblNhanVien = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblNhanVien);
        scrollPane.setBounds(10, 50, 536, 523);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        pnlDanhSach.add(lblTimKiem);
        pnlDanhSach.add(txtTimKiem);
        pnlDanhSach.add(scrollPane);

        // Thêm các thành phần vào panel chính
        add(pnlCapNhat);
        add(pnlBtn1);
        add(pnlDanhSach);
        add(lblTitle);

        this.fillTable();
        this.row=-1;
        this.updateStatus();
        btnThem.addActionListener(e -> insert());
        btnSua.addActionListener(e -> update());
        btnXoa.addActionListener(e -> delete());
        btnMoi.addActionListener(e -> clearForm());
        btnFirst.addActionListener(e -> first());
        btnPrev.addActionListener(e -> prev());
        btnNext.addActionListener(e -> next());
        btnLast.addActionListener(e -> last());
        tblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    row = tblNhanVien.getSelectedRow();
                    edit();
                }
            }
        });
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });
        setVisible(true);
    }

    NhanVienDAO dao = new NhanVienDAO();
    int row =-1;
    void insert(){
        NhanVien nv= getForm();
            try {
                dao.insert(nv);this.fillTable();this.clearForm();
                MsgBox.alert(this, "Thêm mới thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại");
            }

    }
    void update(){
        NhanVien nv= getForm();
        String mk2= new String(txtMatKhau2.getPassword());
        if(!mk2.equals(nv.getMatKhau())){
            MsgBox.alert(this,"Xác nhận mật khẩu không đúng");
        }else{
            try {
                dao.update(nv);this.fillTable();
                MsgBox.alert(this, "Cập nhật thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }
    void delete(){
            String manv=txtMaNV.getText();
            if(manv.equals(Auth.user.getMaNv())){
                MsgBox.alert(this, "Bạn không được xoá chính mình");
            } else if(MsgBox.confirm(this, "Xác nhận xoá?")){
                try {
                    dao.delete(manv); fillTable();clearForm();
                    MsgBox.alert(this, "Xoá thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xoá thất bại!");
                }
            }
    }
    void clearForm(){
        NhanVien nv=new NhanVien();
        this.setForm(nv);
        this.row=-1;
        this.updateStatus();
    }
    void edit(){
        String manv=(String) tblNhanVien.getValueAt(this.row,0);
        NhanVien nv=dao.selectByID(manv);
        this.setForm(nv);
        this.updateStatus();
    }
    void first(){
        this.row=0;
        tblNhanVien.setRowSelectionInterval(row, row);
        this.edit();
    }
    void prev(){
        if(this.row>0){
            this.row--;
            tblNhanVien.setRowSelectionInterval(row, row);
            this.edit();
        }
    }
    void next(){
        if(this.row < tblNhanVien.getRowCount()-1){
            this.row++;
            tblNhanVien.setRowSelectionInterval(row, row);
            this.edit();
        }
    }
    void last(){
        this.row = tblNhanVien.getRowCount()-1;
        tblNhanVien.setRowSelectionInterval(row, row);
        this.edit();
    }
    void fillTable(){
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.selectAll();
            for (NhanVien nv:list){
                String maskedPassword = "*".repeat(nv.getMatKhau().length());
                Object[] row =  {nv.getMaNv(),maskedPassword, nv.getHoVaTen(),
                        nv.isVaiTro()?"Quản lý":"Nhân Viên",nv.getEmail()};
                model.addRow(row);
            }

        }catch (Exception e){
            MsgBox.alert(this,"Lỗi truy vấn dữ liệu");
        }
    }
    void setForm(NhanVien nv){
        txtMaNV.setText(nv.getMaNv());
        txtHoTen.setText(nv.getHoVaTen());
        txtMatKhau.setText(nv.getMatKhau());
        txtMatKhau2.setText(nv.getMatKhau());
        rdoTruongPhong.setSelected(nv.isVaiTro());
        rdoNhanVien.setSelected(!nv.isVaiTro());
        txtEmail.setText(nv.getEmail());
    }
    NhanVien getForm(){
        NhanVien nv = new NhanVien();
        String mk2= new String(txtMatKhau2.getPassword());
        String mk= new String(txtMatKhau.getPassword());
        if(txtMaNV.getText().isEmpty()){
            MsgBox.alert(this, "Mã NV không được để trống");
        }else if (txtMatKhau.getText().isEmpty()){
            MsgBox.alert(this, "Mật khẩu không được để trống");
        }else if(!mk2.equals(mk)){
            MsgBox.alert(this,"Xác nhận mật khẩu không đúng");
        }else if (txtHoTen.getText().isEmpty()){
            MsgBox.alert(this, "Tên NV không được để trống");
        }else if (txtEmail.getText().isEmpty() || !txtEmail.getText().matches("\\w+@\\w+.\\w+")){
            MsgBox.alert(this, "Sai định dạng email");
        }else {
            nv.setMaNv((txtMaNV.getText()));
            nv.setHoVaTen((txtHoTen.getText()));
            nv.setMatKhau((txtMatKhau.getText()));
            nv.setVaiTro(rdoTruongPhong.isSelected());
            nv.setEmail((txtEmail.getText()));
        }
        return nv;
    }
    void updateStatus(){
        boolean edit=(this.row>=0);
        boolean first=(this.row==0);
        boolean last=(this.row==tblNhanVien.getRowCount()-1);
        txtMaNV.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    private void search() {
        String keyword = txtTimKiem.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.search(keyword);
            model.setRowCount(0);

            for (NhanVien nv:list){
                String maskedPassword = "*".repeat(nv.getMatKhau().length());
                Object[] row =  {nv.getMaNv(),maskedPassword, nv.getHoVaTen(),
                        nv.isVaiTro()?"Quản lý":"Nhân Viên",nv.getEmail()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this,"Lỗi tìm kiếm");
            e.printStackTrace();
        }
    }
    // Các phương thức hỗ trợ
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
            setter.accept(component);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Nhân Viên");
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
//Test