package com.score.dao;

import com.score.entity.Score;
import com.score.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreDao {

    // 录入成绩
    public int addScore(Score score) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO score(stu_id, course_id, score) VALUES (?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, score.getStuId());
        pstmt.setInt(2, score.getCourseId());
        pstmt.setDouble(3, score.getScoreValue());
        int num = pstmt.executeUpdate();
        DBUtil.close(conn, pstmt, null);
        return num;
    }

    // 查询某门课全班成绩
    public List<Map<String, Object>> findByCourse(int courseId) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT s.*, st.name as stu_name, st.class_name " +
                     "FROM score s JOIN student st ON s.stu_id = st.stu_id " +
                     "WHERE s.course_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, courseId);
        ResultSet rs = pstmt.executeQuery();
        List<Map<String, Object>> list = new ArrayList<>();
        while(rs.next()){
            Map<String, Object> map = new HashMap<>();
            map.put("id", rs.getInt("id"));
            map.put("stuId", rs.getString("stu_id"));
            map.put("stuName", rs.getString("stu_name"));
            map.put("className", rs.getString("class_name"));
            map.put("score", rs.getDouble("score"));
            list.add(map);
        }
        DBUtil.close(conn, pstmt, rs);
        return list;
    }

    // 计算平均分和及格率
    public Map<String, Object> calcStatistics(int courseId) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT AVG(score) as avg_score, " +
                     "SUM(CASE WHEN score >= 60 THEN 1 ELSE 0 END) as pass_num, " +
                     "COUNT(*) as total_num " +
                     "FROM score WHERE course_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, courseId);
        ResultSet rs = pstmt.executeQuery();
        Map<String, Object> map = new HashMap<>();
        if(rs.next()){
            double avg = rs.getDouble("avg_score");
            int pass = rs.getInt("pass_num");
            int total = rs.getInt("total_num");
            double passRate = total == 0 ? 0 : (pass * 100.0 / total);
            map.put("avgScore", String.format("%.2f", avg));
            map.put("passNum", pass);
            map.put("totalNum", total);
            map.put("passRate", String.format("%.2f", passRate));
        }
        DBUtil.close(conn, pstmt, rs);
        return map;
    }

    // 学生查询个人成绩
    public List<Map<String, Object>> findByStudent(String stuId) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT s.score, c.course_name FROM score s " +
                     "JOIN course c ON s.course_id = c.id " +
                     "WHERE s.stu_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, stuId);
        ResultSet rs = pstmt.executeQuery();
        List<Map<String, Object>> list = new ArrayList<>();
        while(rs.next()){
            Map<String, Object> map = new HashMap<>();
            map.put("courseName", rs.getString("course_name"));
            map.put("score", rs.getDouble("score"));
            list.add(map);
        }
        DBUtil.close(conn, pstmt, rs);
        return list;
    }
}
