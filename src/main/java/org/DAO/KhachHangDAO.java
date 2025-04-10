package org.DAO;

import org.Entity.KhachHang;
import org.Entity.NhanVien;
import org.util.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO extends DuAnDAO<KhachHang, String>{
    final String INSERT_SQL = "INSERT INTO khachhang(makh,tenkh,sdt,email,diachi) Values(?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE khachhang set tenkh = ?, sdt =?, email=?, diachi=? WHERE makh=?";
    final String DELETE_SQL = "DELETE FROM khachhang WHERE makh=?";
    final String SELECT_ALL_SQL = "SELECT * FROM khachhang";
    final String SELECT_BY_ID = "SELECT * FROM khachhang WHERE makh=?";

    @Override
    public void insert(KhachHang entity) throws SQLException {
        JDBCHelper.update(INSERT_SQL, entity.getMaKH(), entity.getTenKH(), entity.getSdt(),
                entity.getEmail(),entity.getDiaChi());
    }

    @Override
    public void update(KhachHang entity) throws SQLException {
        JDBCHelper.update(UPDATE_SQL, entity.getTenKH(), entity.getSdt(),
                entity.getEmail(),entity.getDiaChi(), entity.getMaKH());
    }

    @Override
    public void delete(String key) throws SQLException {
        JDBCHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectByID(String key) {
        List<KhachHang> lishKhachHang = this.selectBySql(SELECT_BY_ID, key);
        if (lishKhachHang.isEmpty()) {
            return null;
        }
        return lishKhachHang.get(0);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> lishKhachHang = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("makh"));
                entity.setTenKH(rs.getString("tenkh"));
                entity.setSdt(rs.getString("sdt"));
                entity.setEmail(rs.getString("email"));
                entity.setDiaChi(rs.getString("diachi"));
                lishKhachHang.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lishKhachHang;
    }
    public List<KhachHang> search(String keyword) {
        String sql = "SELECT * FROM khachhang WHERE "
                + "makh LIKE ? OR "
                + "tenkh LIKE ? OR "
                + "sdt LIKE ? OR "
                + "email LIKE ? OR "
                + "diachi LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%"
                + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }
}
