package org.view;

import org.DAO.ChiTietHoaDonDAO;
import org.DAO.HoaDonDAO;
import org.DAO.SanPhamDAO;
import org.Entity.CTHD;
import org.Entity.HoaDon;
import org.Entity.KhachHang;
import org.Entity.SanPham;
import org.util.Auth;
import org.util.MsgBox;
import org.util.SanPhamThemListener;
import org.util.XDate;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import static org.view.QLNhanVien.*;
import static org.view.QLNhanVien.setBooleanProperty;

public class QLHoaDon extends JPanel {
    private JTable tableHoaDon, tableChiTiet;
    private JTextField txtMaHD, txtMaKH, txtNgayLap, txtTongTien;
    private JButton btnThem,btnMoi, btnPrint, btnPrev, btnNext, btnLast, btnFirst, btnThemSP, btnXoaChiTiet;

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
        txtNgayLap = new JTextField(XDate.toString(getCurrentDate(),"dd/MM/yyyy"));
        lblNgayLap.setBounds(330, 10, 100, 30);
        txtNgayLap.setBounds(330, 40, 250, 30);

        JLabel lblTongTien = new JLabel("Tổng tiền:");
        txtTongTien = new JTextField();
        lblTongTien.setBounds(330, 70, 100, 30);
        txtTongTien.setBounds(330, 100, 250, 30);

        JPanel pnlBtn1 = new JPanel();
        pnlBtn1.setLayout(new GridLayout(3,1,10,10));
        pnlBtn1.setBounds(650,30,250,140);
        btnPrint = new JButton(new ImageIcon("img/save.png"));
        btnThem = new JButton(new ImageIcon("img/add.png"));
        btnMoi = new JButton(new ImageIcon("img/new.png"));
        pnlBtn1.add(btnThem);
        pnlBtn1.add(btnPrint);
        pnlBtn1.add(btnMoi);


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
                new String[]{"Mã HD", "Mã KH", "Mã NV", "Ngày lập", "Tổng tiền"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollHoaDon = new JScrollPane(tableHoaDon);
        scrollHoaDon.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
        scrollHoaDon.setBounds(10, 40, 555, 353);

        tableChiTiet = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Tên SP", "Số lượng", "Đơn giá", "Thành tiền"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
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

        btnXoaChiTiet= new JButton("Xóa sản phẩm");
        btnXoaChiTiet.setBounds(760,363,150,30);
        pnlTable.add(btnThemSP);
        pnlTable.add(btnXoaChiTiet);
        setFontForTextFields(font,lblMaHD,lblMaKH,lblNgayLap,lblTongTien,lblTimKiem);
        AbstractButton[] btn={btnPrev,btnThemSP,btnLast,btnNext,btnXoaChiTiet,btnFirst,btnPrint,btnThem,btnMoi};
        Color defaultBorderColor = Color.LIGHT_GRAY;
        setComponentProperty(btn, c -> c.setBackground(defaultBorderColor) );
        setBooleanProperty(btn, AbstractButton::setFocusPainted, false);
        setBooleanProperty(btn, AbstractButton::setBorderPainted, false);

        add(lblTitle);
        add(panelTop);
        add(pnlTable);
        this.fillTable();
        this.row=-1;
        this.updateStatus();
        btnThem.addActionListener(e -> insert());
        btnMoi.addActionListener(e -> clearForm());
        btnFirst.addActionListener(e -> first());
        btnPrev.addActionListener(e -> prev());
        btnNext.addActionListener(e -> next());
        btnLast.addActionListener(e -> last());
        tableHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    row = tableHoaDon.getSelectedRow();
                    edit();
                }
            }
        });
        btnThemSP.addActionListener(e -> {
            if (row >= 0) {
                String maHD = (String) tableHoaDon.getValueAt(row, 0);

                SanPhamThemListener listener = new SanPhamThemListener() {
                    @Override
                    public void onSanPhamAdded(String maHD) {
                        edit(); // Cập nhật bảng chi tiết
                           // Cập nhật tổng tiền
                        fillTable();            // Cập nhật bảng hóa đơn
                    }
                };

                ChiTietHoaDon chiTietForm = new ChiTietHoaDon(maHD, listener); // Truyền maHD và listener
            } else {
                MsgBox.alert(this, "Vui lòng chọn một hóa đơn trước khi thêm sản phẩm!");
            }
        });
        btnXoaChiTiet.addActionListener(e -> delete());
    }
    int row=-1;
    HoaDonDAO hdDAO= new HoaDonDAO();
    ChiTietHoaDonDAO ctDAO= new ChiTietHoaDonDAO();
    SanPhamDAO spDAO= new SanPhamDAO();

    void insert(){
        HoaDon hd= getForm();

        try {
            hdDAO.insert(hd);this.fillTable();this.clearForm();
            MsgBox.alert(this, "Tạo hóa đơn thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Tạo hóa đơn thất bại");
        }
    }


    void clearForm(){
        HoaDon hd=new HoaDon();
        setForm(hd);
        this.row=-1;
        this.updateStatus();
        fillTableChiTiet();
    }
    void edit(){
        String maHD=(String) tableHoaDon.getValueAt(this.row,0);
        HoaDon hd=hdDAO.selectByID(maHD);
        this.setForm(hd);
        this.updateStatus();
        fillTableChiTiet();
    }
    void first(){
        this.row=0;
        this.edit();
    }
    void prev(){
        if(this.row>0){
            this.row--;
            this.edit();
        }
    }
    void next(){
        if(this.row < tableHoaDon.getRowCount()-1){
            this.row++;
            this.edit();
        }
    }
    void last(){
        this.row = tableHoaDon.getRowCount()-1;
        this.edit();
    }
    void fillTable(){
        DefaultTableModel model = (DefaultTableModel) tableHoaDon.getModel();
        model.setRowCount(0);
        try {
            List<HoaDon> list = hdDAO.selectAll();
            for (HoaDon hd:list){
                Object[] row =  {hd.getMaHD(),hd.getMaKH(), hd.getMaNV(),
                        XDate.toString(hd.getNgayLap(),"dd/MM/yyyy"),hd.getTongTien()};
                model.addRow(row);
            }

        }catch (Exception e){
            MsgBox.alert(this,"Lỗi truy vấn dữ liệu");
        }
        fillTableChiTiet();
    }
    void fillTableChiTiet(){
        DefaultTableModel model = (DefaultTableModel) tableChiTiet.getModel();
        model.setRowCount(0);
        try {
            List<CTHD> list = ctDAO.selectByMaHD(txtMaHD.getText());
            for (CTHD ct:list){
                Object[] row =  {ct.getTenSP(),ct.getSoLuong(), ct.getGiaBan(),
                        ct.getThanhTien()};
                model.addRow(row);
            }

        }catch (Exception e){
            MsgBox.alert(this,"Lỗi truy vấn dữ liệu");
        }
    }
    void setForm(HoaDon hd){
        txtMaHD.setText(hd.getMaHD());
        txtMaKH.setText(hd.getMaKH());
        if(hd.getNgayLap()==null){
            hd.setNgayLap(getCurrentDate());
            txtNgayLap.setText(XDate.toString(getCurrentDate(),"dd/MM/yyyy"));
        }else {
            txtNgayLap.setText(XDate.toString(hd.getNgayLap(), "dd/MM/yyyy"));
        }
        txtTongTien.setText(String.valueOf(hd.getTongTien()));
    }
    HoaDon getForm(){
        HoaDon hd= new HoaDon();
        hd.setMaHD((txtMaHD.getText()));
        hd.setMaKH((txtMaKH.getText()));
        hd.setMaNV((Auth.user.getMaNv()));
        hd.setNgayLap(XDate.toDate(txtNgayLap.getText(),"dd/MM/yyyy"));
        hd.setTongTien(Double.parseDouble(txtTongTien.getText()));
        return hd;
    }
    void delete() {
        int row1 = tableChiTiet.getSelectedRow();
        if (row1 == -1) {
            MsgBox.alert(this, "Vui lòng chọn sản phẩm cần xoá!");
            return;
        }

        String mahd = txtMaHD.getText();
        String tenSP = (String) tableChiTiet.getValueAt(row1, 0);
        SanPham sp = spDAO.selectByTenSP(tenSP);

        if (sp == null) {
            MsgBox.alert(this, "Không tìm thấy mã sản phẩm!");
            return;
        }

        if (MsgBox.confirm(this, "Xác nhận xoá?")) {
            try {
                ctDAO.delete(mahd, sp.getMaSP());
                fillTable();
                edit();
                MsgBox.alert(this, "Xoá thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xoá thất bại!");
            }
        }
    }

    void updateStatus(){
        boolean edit=(this.row>=0);
        boolean first=(this.row==0);
        boolean last=(this.row==tableHoaDon.getRowCount()-1);
        txtMaHD.setEditable(!edit);
        txtNgayLap.setEditable(false);
        btnThem.setEnabled(!edit);
        btnPrint.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    public Date getCurrentDate() {
        return new Date();
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

