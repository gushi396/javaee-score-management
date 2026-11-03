package com.score.dao;

import com.score.entity.Course;
import com.score.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    public List<Course> findAll() throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM course";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Course> list = new ArrayList<>();
        while(rs.next()){
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setCourseName(rs.getString("course_name"));
            list.add(course);
        }
        DBUtil.close(conn, pstmt, rs);
        return list;
    }
}
