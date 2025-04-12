package org.DAO;

import org.Entity.SanPham;
import org.util.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO extends DuAnDAO<SanPham, Integer>{
    final String INSERT_SQL = "INSERT INTO SanPham (tensp, sl, ngaynhap,anh, gianhap, giaban, ghichu) VALUES (?,?, ?,?,?,?, ?)";
    final String UPDATE_SQL = "UPDATE SanPham SET tensp = ?, sl = ?, ngaynhap = ?,anh = ?, gianhap = ?, giaban =?, ghichu =? WHERE masp = ?";
    final String DELETE_SQL = "DELETE FROM SanPham WHERE masp = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    final String SELECT_BY_ID = "SELECT * FROM SanPham WHERE masp = ?";


    @Override
    public void insert(SanPham entity) throws SQLException {
        JDBCHelper.update(INSERT_SQL, entity.getTenSP(), entity.getSoLuong(), entity.getNgayNhap(),
                entity.getAnh(), entity.getGiaNhap(), entity.getGiaBan(), entity.getGhiChu());
    }

    @Override
    public void update(SanPham entity) throws SQLException {
        JDBCHelper.update(UPDATE_SQL, entity.getTenSP(), entity.getSoLuong(),entity.getNgayNhap(),entity.getAnh(),
                entity.getGiaNhap(),entity.getGiaBan(),entity.getGhiChu(),entity.getMaSP());
    }

    @Override
    public void delete(Integer key) throws SQLException {
        JDBCHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SanPham selectByID(Integer key) {
        List<SanPham> listSanPham = this.selectBySql(SELECT_BY_ID, key);
        if (listSanPham.isEmpty()) {
            return null;
        }
        return listSanPham.get(0);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> listSanPham = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                SanPham entity = new SanPham();
                entity.setMaSP(rs.getInt("MaSP"));
                entity.setTenSP(rs.getString("TenSP"));
                entity.setSoLuong(rs.getInt("SL"));
                entity.setNgayNhap(rs.getDate("NgayNhap"));
                entity.setAnh(rs.getString("Anh"));
                entity.setGiaNhap(rs.getInt("GiaNhap"));
                entity.setGiaBan(rs.getInt("GiaBan"));
                entity.setGhiChu(rs.getString("GhiChu"));
                listSanPham.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listSanPham ;
    }

    public List<SanPham> search(String keyword) {
        String sql = "SELECT * FROM SanPham WHERE "
                + "maSP LIKE ? OR "
                + "tenSP LIKE ? OR "
                + "SL LIKE ? OR "
                + "giaNhap LIKE ? OR "
                + "giaBan LIKE ? OR "
                + "ngayNhap LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%"
                + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }
    public SanPham selectByTenSP(String tenSP) {
        String sql = "SELECT * FROM sanpham WHERE tensp = ?";
        try {
            ResultSet rs = JDBCHelper.query(sql, tenSP);
            if (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt("masp"));
                sp.setTenSP(rs.getString("tensp"));
                sp.setGiaBan(rs.getDouble("giaban"));
                // ... (các thuộc tính khác)
                rs.getStatement().getConnection().close();
                return sp;
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn sản phẩm: " + e.getMessage());
        }
        return null;
    }
}
