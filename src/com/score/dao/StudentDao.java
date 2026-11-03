package com.score.dao;

import com.score.entity.Student;
import com.score.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {
    public Student findByUsernameAndPassword(String username, String password) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM student WHERE username = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        Student student = null;
        if(rs.next()){
            student = new Student();
            student.setStuId(rs.getString("stu_id"));
            student.setUsername(rs.getString("username"));
            student.setPassword(rs.getString("password"));
            student.setName(rs.getString("name"));
            student.setClassName(rs.getString("class_name"));
        }
        DBUtil.close(conn, pstmt, rs);
        return student;
    }
}
