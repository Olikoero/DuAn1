package org.DAO;

import org.Entity.HoaDon;
import org.Entity.KhachHang;
import org.util.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO extends DuAnDAO<HoaDon, String>{
    final String INSERT_SQL = "INSERT INTO hoadon(mahd,makh,manv,ngaylaphd,tongtien) Values(?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE hoadon set makh = ?, manv =?, ngaylaphd=?, tongtien=? WHERE mahd=?";
    final String DELETE_SQL = "DELETE FROM hoadon WHERE mahd=?";
    final String SELECT_ALL_SQL = "SELECT * FROM hoadon order by mahd DESC";
    final String SELECT_BY_ID = "SELECT * FROM hoadon WHERE mahd=?";


    @Override
    public void insert(HoaDon entity) throws SQLException {
        JDBCHelper.update(INSERT_SQL, entity.getMaHD(), entity.getMaKH(), entity.getMaNV(),
                entity.getNgayLap(),entity.getTongTien());
    }

    @Override
    public void update(HoaDon entity) throws SQLException {
        JDBCHelper.update(UPDATE_SQL, entity.getMaKH(), entity.getMaNV(), entity.getNgayLap(),
                entity.getTongTien(), entity.getMaHD());
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
        List<HoaDon> lishHoaDon = this.selectBySql(SELECT_BY_ID, key);
        if (lishHoaDon.isEmpty()) {
            return null;
        }
        return lishHoaDon.get(0);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> lishHoaDon = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("mahd"));
                entity.setMaKH(rs.getString("makh"));
                entity.setMaNV(rs.getString("manv"));
                entity.setNgayLap(rs.getDate("ngaylaphd"));
                entity.setTongTien(rs.getDouble("tongtien"));
                lishHoaDon.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lishHoaDon;
    }
}
