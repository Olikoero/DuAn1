package org.util;

import java.sql.*;
import java.util.List;

public class JDBCHelper {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost:1433;DatabaseName=ThunderStore;encrypt=true;trustServerCertificate=true;";
    static String user = "sa";
    static String pass = "123";

    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, user, pass);
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection conn = getConnection(); // Use getConnection here
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = conn.prepareCall(sql); // Proc
        } else {
            stmt = conn.prepareStatement(sql); // SQL
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    public static int update(String sql, Object... args) throws SQLException {
        try (PreparedStatement stmt = getStmt(sql, args)) { // use try-with-resources
            return stmt.executeUpdate();
        }
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement stmt = getStmt(sql, args);
        return stmt.executeQuery();
    }
    public static Object[] query1(String sql, Object... args) throws SQLException {
    Connection conn = getConnection();
    PreparedStatement stmt = getStmt(sql, args);
    ResultSet rs = stmt.executeQuery();
    return new Object[]{conn, stmt, rs}; // Trả về cả 3 đối tượng để không bị đóng sớm
}

    public static Object value(String sql, Object... args) {
        try (ResultSet rs = query(sql, args);
             Statement st = rs.getStatement()) { // use try-with resources
            if (rs.next()) {
                return rs.getObject(1); // corrected index to 1
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}