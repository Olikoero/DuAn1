package Entity;

import java.util.Date;

public class SanPham {
    private String MaSP;
    private String TenSP;
    private String Hinh;
    private double Gia;
    private int SoLuong;
    private Date NgayNhap;
    private boolean TrangThai;
    private String TinhTrang;

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }
}
