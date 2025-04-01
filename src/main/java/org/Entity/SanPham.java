package org.Entity;

import java.util.Date;

public class SanPham {
    private int MaSP;
    private String TenSP;
    private String Anh;
    private double GiaNhap;
    private double GiaBan;
    private int SoLuong;
    private Date NgayNhap;
    private String GhiChu;

    public SanPham(int maSP, String tenSP, String anh, double giaNhap, double giaBan, int soLuong, Date ngayNhap, String ghiChu) {
        MaSP = maSP;
        TenSP = tenSP;
        Anh = anh;
        GiaNhap = giaNhap;
        GiaBan = giaBan;
        SoLuong = soLuong;
        NgayNhap = ngayNhap;
        GhiChu = ghiChu;
    }

    public SanPham() {

    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public double getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        GiaNhap = giaNhap;
    }

    public double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(double giaBan) {
        GiaBan = giaBan;
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

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}