package org.DAO;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import org.DAO.DuAnDAO;
import org.Entity.NhanVien;
import org.util.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author huanl
 */
public class NhanVienDAO extends DuAnDAO<NhanVien, String> {
    final String INSERT_SQL = "INSERT INTO Nhanvien(MaNV,MatKhau,HoTen,VaiTro,GioiTinh) Values(?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE Nhanvien set MatKhau = ?, Hoten =?, VaiTro=?, GioiTinh=? WHERE MaNV=?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String SELECT_BY_ID = "SELECT * FROM NhanVien WHERE MaNV=?";

    @Override
    public void insert(NhanVien entity) throws SQLException {
        JDBCHelper.update(INSERT_SQL, entity.getMaNv(), entity.getMatKhau(), entity.getHoVaTen(),entity.isVaiTro(),entity.getGioitinh());
    }


    @Override
    public void update(NhanVien entity) throws SQLException {

        JDBCHelper.update(UPDATE_SQL,entity.getMatKhau(), entity.getHoVaTen(), entity.isVaiTro(), entity.getGioitinh(), entity.getMaNv());
    }


    @Override
    public void delete(String key) throws SQLException {
        JDBCHelper.update(DELETE_SQL, key);
    }


    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }


    @Override
    public NhanVien selectByID(String key) {
        List<NhanVien> listNhanVien = this.selectBySql(SELECT_BY_ID, key);
        if (listNhanVien.isEmpty()) {
            return null;
        }
        return listNhanVien.get(0);
    }
    public boolean checkEmailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE Email = ?";
        try (Connection conn = JDBCHelper.getConnection(); // Sử dụng getConnection()
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }



    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> listNhanVien = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNv(rs.getString("MaNV"));
                entity.setHoVaTen(rs.getString("HoTen"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setVaiTro(rs.getBoolean("Vaitro"));
                entity.setGioitinh(rs.getInt("GioiTinh"));
                entity.setEmail(rs.getString("Email"));
                listNhanVien.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listNhanVien;
    }
}
