package util;

import java.sql.*;

public class JDBCHelper {
    static String driver= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl= "jdbc:sqlserver://localhost:1433;DatabaseName=EduSys;encrypt=true;trustServerCertificate=true;";
    static String user = "sa";
    static String pass = "songlong";

    static {
        try {
            Class.forName(driver);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection conn= DriverManager.getConnection(dburl,user,pass);
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt=conn.prepareCall(sql); //Proc
        }else {
            stmt=conn.prepareStatement(sql); //SQL
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i+1,args[i]);
        }
        return stmt;
    }
    public static int update(String sql, Object...args)throws SQLException{
        try {
            PreparedStatement stmt=JDBCHelper.getStmt(sql,args);
            try {
                return stmt.executeUpdate();
            }finally {
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet query(String sql, Object...args)throws SQLException{
        PreparedStatement stmt = JDBCHelper.getStmt(sql,args);
        return stmt.executeQuery();
    }
    public static Object value(String sql, Object...args){
        try {
            ResultSet rs=JDBCHelper.query(sql,args);
            if (rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
