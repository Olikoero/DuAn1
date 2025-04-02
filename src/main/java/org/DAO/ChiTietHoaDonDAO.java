package org.DAO;

import org.Entity.CTHD;
import org.Entity.HoaDon;
import org.util.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {
    final String INSERT_SQL =  "INSERT INTO chitiethoadon (mahd, masp, soluong, thanhtien) VALUES (?, ?, ?, ?)";
        // Truy vấn chi tiết hóa đơn theo mã hóa đơn
        public List<CTHD> selectByMaHD(String maHD) {
            String sql = "SELECT ct.mahd, ct.masp,sp.tensp, ct.soluong,sp.giaban, (ct.soluong * sp.giaban) AS thanhtien " +
                    "FROM chitiethoadon ct " +
                    "JOIN sanpham sp ON ct.masp = sp.masp " +
                    "WHERE ct.mahd = ?";
            List<CTHD> list = new ArrayList<>();

            try {
                ResultSet rs = JDBCHelper.query(sql, maHD);
                while (rs.next()) {
                    CTHD cthd = new CTHD();
                    cthd.setMaHD(rs.getString("mahd"));
                    cthd.setMaSP(rs.getInt("masp"));
                    cthd.setTenSP(rs.getString("tensp"));
                    cthd.setGiaBan(rs.getDouble("giaban"));
                    cthd.setSoLuong(rs.getInt("soluong"));
                    cthd.setThanhTien(rs.getDouble("thanhtien"));
                    list.add(cthd);
                }
                rs.getStatement().getConnection().close();
                return list;
            } catch (Exception e) {
                throw new RuntimeException("Lỗi truy vấn chi tiết hóa đơn: " + e.getMessage());
            }
        }

        // Thêm chi tiết hóa đơn (dùng khi thêm sản phẩm từ ChiTietHoaDon form)
        public void insert(CTHD entity) throws SQLException {
            JDBCHelper.update(INSERT_SQL, entity.getMaHD(), entity.getMaSP(), entity.getSoLuong(),
                    entity.getThanhTien());
        }
    }

