package com.score.dao;

import com.score.entity.Teacher;
import com.score.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDao {
    public Teacher findByUsernameAndPassword(String username, String password) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM teacher WHERE username = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        Teacher teacher = null;
        if(rs.next()){
            teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setUsername(rs.getString("username"));
            teacher.setPassword(rs.getString("password"));
            teacher.setName(rs.getString("name"));
        }
        DBUtil.close(conn, pstmt, rs);
        return teacher;
    }
}
