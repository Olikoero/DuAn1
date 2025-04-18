package org.DAO;

import org.Entity.HoaDon;
import org.Entity.KhachHang;
import org.util.JDBCHelper;
import org.util.MsgBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO extends DuAnDAO<HoaDon, String> {
    final String INSERT_SQL = "INSERT INTO hoadon(mahd, makh, manv, ngaylaphd, tongtien) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE hoadon SET makh = ?, manv = ?, ngaylaphd = ?, tongtien = ? WHERE mahd = ?";
    final String DELETE_SQL = "DELETE FROM hoadon WHERE mahd = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM hoadon ORDER BY mahd DESC";
    final String SELECT_BY_ID = "SELECT * FROM hoadon WHERE mahd = ?";

    @Override
    public void insert(HoaDon entity) throws SQLException {
        // Validate makh and manv before inserting
        if (!checkMaKHExist(entity.getMaKH())) {
            throw new SQLException("Mã khách hàng '" + entity.getMaKH() + "' không tồn tại trong bảng khachhang!");
        }
        if (!checkMaNVExist(entity.getMaNV())) {
            throw new SQLException("Mã nhân viên '" + entity.getMaNV() + "' không tồn tại trong bảng nhanvien!");
        }

        try {
            JDBCHelper.update(INSERT_SQL, entity.getMaHD(), entity.getMaKH(), entity.getMaNV(),
                    entity.getNgayLap(), entity.getTongTien());
        } catch (SQLException e) {
            if (e.getMessage().contains("FOREIGN KEY constraint")) {
                throw new SQLException("Lỗi: Mã khách hàng hoặc mã nhân viên không hợp lệ!", e);
            }
            throw e;
        }
    }

    @Override
    public void update(HoaDon entity) throws SQLException {
        // Validate makh and manv for updates
        if (!checkMaKHExist(entity.getMaKH())) {
            throw new SQLException("Mã khách hàng '" + entity.getMaKH() + "' không tồn tại trong bảng khachhang!");
        }
        if (!checkMaNVExist(entity.getMaNV())) {
            throw new SQLException("Mã nhân viên '" + entity.getMaNV() + "' không tồn tại trong bảng nhanvien!");
        }

        try {
            JDBCHelper.update(UPDATE_SQL, entity.getMaKH(), entity.getMaNV(), entity.getNgayLap(),
                    entity.getTongTien(), entity.getMaHD());
        } catch (SQLException e) {
            if (e.getMessage().contains("FOREIGN KEY constraint")) {
                throw new SQLException("Lỗi: Mã khách hàng hoặc mã nhân viên không hợp lệ!", e);
            }
            throw e;
        }
    }

    @Override
    public void delete(String key) throws SQLException {
        JDBCHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDon selectByID(String key) {
        List<HoaDon> listHoaDon = this.selectBySql(SELECT_BY_ID, key);
        if (listHoaDon.isEmpty()) {
            return null;
        }
        return listHoaDon.get(0);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> listHoaDon = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("mahd"));
                entity.setMaKH(rs.getString("makh"));
                entity.setMaNV(rs.getString("manv"));
                entity.setNgayLap(rs.getDate("ngaylaphd"));
                entity.setTongTien(rs.getDouble("tongtien"));
                listHoaDon.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listHoaDon;
    }

    public List<HoaDon> search(String keyword) {
        String sql = "SELECT * FROM hoadon WHERE "
                + "mahd LIKE ? OR "
                + "makh LIKE ? OR "
                + "manv LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    public boolean checkMaKHExist(String maKH) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCHelper.getConnection();
            String sql = "SELECT COUNT(*) FROM khachhang WHERE makh = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maKH);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // True if makh exists
            }
            return false;
        } catch (SQLException e) {
            throw new SQLException("Lỗi kiểm tra mã khách hàng: " + e.getMessage(), e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public boolean checkMaNVExist(String maNV) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCHelper.getConnection();
            String sql = "SELECT COUNT(*) FROM nhanvien WHERE manv = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNV);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // True if manv exists
            }
            return false;
        } catch (SQLException e) {
            throw new SQLException("Lỗi kiểm tra mã nhân viên: " + e.getMessage(), e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}