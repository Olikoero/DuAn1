package org.DAO;

import org.util.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getDoanhThuThang(int thang, int nam) {
        String sql = "{CALL TinhDoanhThuThang(?, ?)}";
        String[] cols = { "DoanhThuThang"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    public List<Object[]> getDoanhThuNam(int nam) {
        String sql = "{CALL TinhDoanhThuNam(?)}";
        String[] cols = {"DoanhThuNam"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<Object[]> getDoanhThuTong() {
        String sql = "{CALL TinhDoanhThuTong}";
        String[] cols = {"DoanhThuTong"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getDoanhThuChiTiet(int thang, int nam) {
        String sql = "{CALL sp_BaoCaoDoanhThu(?, ?)}";
        String[] cols = {"TÊN SẢN PHẨM", "ĐÃ BÁN", "TỔNG TIỀN BÁN", "GIÁ GỐC", "LỢI NHUẬN"};
        return this.getListOfArray(sql, cols, thang, nam);
    }


    public List<Object[]> getSanPhamBanChay(int thang, int nam) {
        String sql = "{CALL sp_SanPhamBanChay(?, ?)}";
        String[] cols = {"Tên sản phẩm", "Đã bán trong tháng", "Còn lại trong kho"};
        return this.getListOfArray(sql, cols, thang, nam);
    }
    public List<Object[]> getThanhTichNhanVien(int thang, int nam) {
        String sql = "{CALL TinhThanhTichNhanVien(?, ?)}";
        String[] cols = {"Mã NV", "Tên NV", "Số đơn đã bán"};
        return this.getListOfArray(sql, cols, thang, nam);
    }
    public List<Integer> getDanhSachNam() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT YEAR(ngaylaphd) AS Nam FROM hoadon ORDER BY Nam DESC";

        try (ResultSet rs = JDBCHelper.query(sql)) {
            while (rs.next()) {
                list.add(rs.getInt("Nam"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách năm từ hóa đơn!", e);
        }
        return list;
    }

    // Lấy danh sách tháng theo năm (giảm dần)
    public List<Integer> getDanhSachThang(int nam) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT MONTH(ngaylaphd) AS Thang FROM hoadon WHERE YEAR(ngaylaphd) = ? ORDER BY Thang DESC";

        try (ResultSet rs = JDBCHelper.query(sql, nam)) {
            while (rs.next()) {
                list.add(rs.getInt("Thang"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tháng từ hóa đơn!", e);
        }
        return list;
    }
}