package com.score.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/score_management?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PWD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PWD);
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try { if(rs!=null) rs.close(); } catch (Exception e) {}
        try { if(pstmt!=null) pstmt.close(); } catch (Exception e) {}
        try { if(conn!=null) conn.close(); } catch (Exception e) {}
    }
}
