package org.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.view.QLNhanVien.addCompoment;

public class QLSanPham extends JPanel {
    private JLabel lblAnh;
    JFileChooser fileChooser;
    public QLSanPham(){
        setSize(986, 713);
        setLayout(null);

        JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẦM");
        lblTitle.setFont(lblTitle.getFont().deriveFont(30f));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(50,15,500,30);

        JPanel pnlCapNhat = new JPanel(null);
        pnlCapNhat.setBorder(new LineBorder(Color.BLUE,1));
        pnlCapNhat.setBounds(20,60,946,255);

        JLabel lblHinh = new JLabel("Hình ảnh");
        lblHinh.setBounds(10, 225, 100, 30);

        lblAnh = new JLabel();
        lblAnh.setBounds(10, 10, 190, 215);
        lblAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        fileChooser = new JFileChooser();

        addCompoment(pnlCapNhat,lblHinh,lblAnh,fileChooser);
        JPanel pnlDanhSach = new JPanel(null);
        pnlDanhSach.setBorder(new LineBorder(Color.BLUE,1));
        pnlDanhSach.setBounds(20,335,946,345);


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
