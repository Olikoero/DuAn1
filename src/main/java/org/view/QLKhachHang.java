package org.view;

import org.DAO.KhachHangDAO;
import org.Entity.KhachHang;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.view.QLNhanVien.*;

public class QLKhachHang extends JPanel {
    private JTable tblKhachHang;
    private JTextField txtMaKH, txtHoTen, txtEmail, txtSDT,txtDiaChi,txtTimKiem;
    private JButton btnFirst, btnPrev,btnNext,btnLast,btnMoi,btnXoa,btnSua,btnThem;
    public QLKhachHang(){
        setSize(986, 713);
        setLayout(null);
        Font font= new Font("Arial",Font.BOLD,14);

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
        txtHoTen = new JTextField();
        txtHoTen.setBounds(20, 100, 330, 30);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(20, 130, 170, 30);
        txtEmail = new JTextField();
        txtEmail.setBounds(20, 160, 330, 30);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setBounds(20, 190, 100, 30);
        txtSDT = new JTextField();
        txtSDT.setBounds(20, 220, 330, 30);

        JLabel lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setBounds(20, 250, 100, 30);
        txtDiaChi= new JTextField();
        txtDiaChi.setBounds(20, 280, 330, 30);

        JPanel pnlBtn2 = new JPanel();
        pnlBtn2.setLayout(new GridLayout(1,4,10,10));
        pnlBtn2.setBounds(10,400,350,30);
        btnFirst = new JButton(XImage.loadIcon("/IMG/First.png"));
        btnPrev = new JButton(XImage.loadIcon("/IMG/Left.png"));
        btnNext = new JButton(XImage.loadIcon("/IMG/Right.png"));
        btnLast = new JButton(XImage.loadIcon("/IMG/Last.png"));
        pnlBtn2.add(btnFirst);
        pnlBtn2.add(btnPrev);
        pnlBtn2.add(btnNext);
        pnlBtn2.add(btnLast);

        addCompoment(pnlCapNhat,lblMaKH,lblHoTen,lblEmail,lblSDT,pnlBtn2,lblDiaChi,
                txtMaKH,txtHoTen,txtEmail,txtSDT,txtDiaChi);




        //Buttons
        JPanel pnlBtn1 = new JPanel();
        pnlBtn1.setLayout(new GridLayout(2,2,30,20));
        pnlBtn1.setBounds(20,553,370,130);
        btnMoi = new JButton(XImage.loadIcon("/IMG/new.png"));
        btnXoa = new JButton(XImage.loadIcon("/IMG/Delete.png"));
        btnSua = new JButton(XImage.loadIcon("/IMG/Save.png"));
        btnThem = new JButton(XImage.loadIcon("/IMG/add.png"));
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
        JLabel lblTimKiem= new JLabel("Tìm kiếm:");
        lblTimKiem.setBounds(10,10,100,30);
        txtTimKiem= new JTextField();
        txtTimKiem.setBounds(140,10,406,30);

        pnlDanhSach.setBounds(410,100,556,583);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE,1));
        tblKhachHang = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"MÃ KHÁCH HÀNG", "HỌ VÀ TÊN", "SỐ ĐIỆN THOẠI", "EMAIL","ĐỊA CHỈ"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });
        JScrollPane scrollPane = new JScrollPane(tblKhachHang);
        scrollPane.setBounds(10, 50, 536, 523);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
        pnlDanhSach.add(scrollPane);
        pnlDanhSach.add(txtTimKiem);
        pnlDanhSach.add(lblTimKiem);
        setFontForTextFields(font,lblMaKH,lblHoTen,lblEmail,lblSDT,lblTimKiem,lblDiaChi,lblTimKiem);

        add(pnlCapNhat);
        add(pnlBtn1);
        add(pnlDanhSach);
        add(lblTitle);
        setVisible(true);
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
        tblKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    row = tblKhachHang.getSelectedRow();
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
    }
    KhachHangDAO dao = new KhachHangDAO();
    int row=-1;

    void insert(){
        KhachHang kh= getForm();
        try {
        if(txtMaKH.getText().isEmpty() | txtHoTen.getText().isEmpty()){
            MsgBox.alert(this,"Vui lòng không được để trống thông tin liên hệ!");
            return ;
        }else if (kh.getSdt() == null || kh.getSdt().trim().isEmpty() || !kh.getSdt().trim().matches("^0[0-9]{9}$")) {
            MsgBox.alert(this, "Số điện thoại không đúng định dạng!");
            return;
        }else {
            dao.insert(kh);
            this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Thêm mới thành công");
        }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void update(){
        KhachHang kh= getForm();
            try {
                dao.update(kh);this.fillTable();
                MsgBox.alert(this, "Cập nhật thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
    }
    void delete(){

            String makh=txtMaKH.getText();
            if(makh.equals(Auth.user.getMaNv())){
                MsgBox.alert(this, "Bạn không được xoá chính mình");
            } else if(MsgBox.confirm(this, "Xác nhận xoá?")){
                try {
                    dao.delete(makh); fillTable();clearForm();
                    MsgBox.alert(this, "Xoá thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xoá thất bại!");
                }
            }
        }
    void clearForm(){
        KhachHang kh=new KhachHang();
        this.setForm(kh);
        this.row=-1;
        this.updateStatus();
    }
    void edit(){
        String makh=(String) tblKhachHang.getValueAt(this.row,0);
        KhachHang kh=dao.selectByID(makh);
        this.setForm(kh);
        this.updateStatus();
    }
    void first(){
        this.row=0;
        tblKhachHang.setRowSelectionInterval(row, row);
        this.edit();
    }
    void prev(){
        if(this.row>0){
            this.row--;
            tblKhachHang.setRowSelectionInterval(row, row);
            this.edit();
        }
    }
    void next(){
        if(this.row < tblKhachHang.getRowCount()-1){
            this.row++;
            tblKhachHang.setRowSelectionInterval(row, row);
            this.edit();
        }
    }
    void last(){
        this.row = tblKhachHang.getRowCount()-1;
        tblKhachHang.setRowSelectionInterval(row, row);
        this.edit();
    }
    void fillTable(){
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> list = dao.selectAll();
            for (KhachHang kh:list){
                Object[] row =  {kh.getMaKH(),kh.getTenKH(), kh.getSdt(),
                        kh.getEmail(),kh.getDiaChi()};
                model.addRow(row);
            }

        }catch (Exception e){
            MsgBox.alert(this,"Lỗi truy vấn dữ liệu");
        }
    }
    void setForm(KhachHang kh){
        txtMaKH.setText(kh.getMaKH());
        txtHoTen.setText(kh.getTenKH());
        txtSDT.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
    }
    KhachHang getForm(){
        KhachHang kh= new KhachHang();
        kh.setMaKH((txtMaKH.getText()));
        kh.setTenKH((txtHoTen.getText()));
        kh.setSdt((txtSDT.getText()));
        kh.setEmail(txtEmail.getText());
        kh.setDiaChi(txtDiaChi.getText());
        return kh;
    }
    void updateStatus(){
        boolean edit=(this.row>=0);
        boolean first=(this.row==0);
        boolean last=(this.row==tblKhachHang.getRowCount()-1);
        txtMaKH.setEditable(!edit);
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
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> list = dao.search(keyword);
            model.setRowCount(0);

            for (KhachHang kh:list){
                Object[] row =  {kh.getMaKH(),kh.getTenKH(), kh.getSdt(),
                        kh.getEmail(),kh.getDiaChi()};
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this,"Lỗi tìm kiếm");
            e.printStackTrace();
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

            QLKhachHang panel = new QLKhachHang();
            frame.add(panel);

            frame.setVisible(true);
        });
    }

}
