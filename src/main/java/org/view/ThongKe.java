package org.view;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.DAO.ThongKeDAO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.view.QLNhanVien.addCompoment;
import static org.view.QLNhanVien.setFontForTextFields;

public class ThongKe extends JPanel {
    private JLabel lblDoanhThuThang, lblDoanhThuNam, lblDoanhThuTong;
    private JComboBox<String> cboThang, cboNam;
    private JButton btnXoaLoc, btnExportExcel;
    private JTable tblDoanhThu, tblBanChay, tblThanhTich;
    private JTabbedPane tabs; // Added to access the tabbed pane in exportToExcel

    public ThongKe() {
        setSize(986, 713);
        setLayout(null);

        Font font = new Font("Arial", Font.BOLD, 14);
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
        btnXoaLoc.setBounds(760, 10, 150, 35);

        btnExportExcel = new JButton("Xuất Excel");
        btnExportExcel.setBounds(760, 55, 150, 35);
        btnExportExcel.addActionListener(e -> exportToExcel());
        addCompoment(pnlLoc, pnlLocThang, pnlLocNam, btnXoaLoc, btnExportExcel);

        tabs = new JTabbedPane();
        tabs.setBounds(10, 360, 946, 330);

        JPanel pnlDoanhThu = new JPanel(null);
        tblDoanhThu = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"TÊN SẢN PHẨM", "ĐÃ BÁN", "TỔNG TIỀN BÁN", "GIÁ GỐC", "LỢI NHUẬN"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });
        JScrollPane scrollPaneDT = new JScrollPane(tblDoanhThu);
        scrollPaneDT.setBounds(10, 10, 926, 285);
        pnlDoanhThu.add(scrollPaneDT);

        JPanel pnlBanChay = new JPanel(null);
        tblBanChay = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"TÊN SẢN PHẨM", "LOẠI SẢN PHẨM", "ĐÃ BÁN", "CÒN LẠI TRONG KHO"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });
        JScrollPane scrollPaneBC = new JScrollPane(tblBanChay);
        scrollPaneBC.setBounds(10, 10, 926, 285);
        pnlBanChay.add(scrollPaneBC);

        JPanel pnlThanhTich = new JPanel(null);
        tblThanhTich = new JTable(new DefaultTableModel(
                new Object[][]{}, new String[]{"MÃ NV", "TÊN NV", "SỐ ĐƠN ĐÃ BÁN"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ ô nào
                return false;
            }
        });
        JScrollPane scrollPaneTT = new JScrollPane(tblThanhTich);
        scrollPaneTT.setBounds(10, 10, 926, 285);
        pnlThanhTich.add(scrollPaneTT);

        tabs.addTab("Doanh thu theo tháng", pnlDoanhThu);
        tabs.addTab("Sản phẩm bán chạy", pnlBanChay);
        tabs.addTab("Thành tích nhân viên", pnlThanhTich);

        add(lblDoanhThu);
        add(pnlCapNhat);
        add(lblBoLoc);
        add(pnlLoc);
        add(tabs);
        fillCboNam();

        cboNam.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                fillCboThang();
                updateData();
            }
        });

        cboThang.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateData();
            }
        });

        btnXoaLoc.addActionListener(e -> resetFilter());
    }

    ThongKeDAO dao = new ThongKeDAO();

    private void fillCboNam() {
        try {
            List<Integer> namList = dao.getDanhSachNam();
            cboNam.removeAllItems();
            for (int nam : namList) {
                cboNam.addItem(String.valueOf(nam));
            }
            if (cboNam.getItemCount() > 0) {
                cboNam.setSelectedIndex(0);
                fillCboThang();
                updateData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách năm!");
        }
    }

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
                    cboThang.setSelectedIndex(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách tháng!");
        }
    }

    private void exportToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            // Get the currently selected tab index
            int selectedTabIndex = tabs.getSelectedIndex();
            JTable selectedTable;
            String sheetName;

            // Determine which table and sheet name based on the selected tab
            switch (selectedTabIndex) {
                case 0:
                    selectedTable = tblDoanhThu;
                    sheetName = "Doanh Thu";
                    break;
                case 1:
                    selectedTable = tblBanChay;
                    sheetName = "San Pham Ban Chay";
                    break;
                case 2:
                    selectedTable = tblThanhTich;
                    sheetName = "Thanh Tich Nhan Vien";
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Không có bảng nào được chọn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            // Export only the selected table
            createSheetForTable(workbook, selectedTable, sheetName);

            // Save file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
            fileChooser.setSelectedFile(new java.io.File(sheetName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".xlsx"));
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createSheetForTable(Workbook workbook, JTable table, String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Create header row
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));
            cell.setCellStyle(headerStyle);
        }

        // Populate data rows
        for (int row = 0; row < model.getRowCount(); row++) {
            Row dataRow = sheet.createRow(row + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = dataRow.createCell(col);
                Object value = model.getValueAt(row, col);
                if (value != null) {
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            }
        }

        // Auto-size columns
        for (int col = 0; col < model.getColumnCount(); col++) {
            sheet.autoSizeColumn(col);
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
            List<Object[]> list = dao.getDoanhThuChiTiet(thang, nam);
            for (Object[] row : list) {
                model.addRow(row);
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

            List<Object[]> dtThangList = dao.getDoanhThuThang(thang, nam);
            double doanhThuThang = 0;
            if (!dtThangList.isEmpty() && dtThangList.get(0).length >= 1) {
                doanhThuThang = ((Number) dtThangList.get(0)[0]).doubleValue();
            }
            lblDoanhThuThang.setText("Tháng: " + df.format(doanhThuThang) + " VNĐ");

            List<Object[]> dtNamList = dao.getDoanhThuNam(nam);
            double doanhThuNam = 0;
            if (!dtNamList.isEmpty() && dtNamList.get(0).length >= 1) {
                doanhThuNam = ((Number) dtNamList.get(0)[0]).doubleValue();
            }
            lblDoanhThuNam.setText("Năm: " + df.format(doanhThuNam) + " VNĐ");

            List<Object[]> dtTongList = dao.getDoanhThuTong();
            double doanhThuTong = 0;
            if (!dtTongList.isEmpty() && dtTongList.get(0).length >= 1) {
                doanhThuTong = ((Number) dtTongList.get(0)[0]).doubleValue();
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
            cboNam.setSelectedIndex(0);
            fillCboThang();
        }
        updateData();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thống Kê");
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