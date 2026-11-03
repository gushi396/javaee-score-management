package com.score.service;

import com.score.dao.CourseDao;
import com.score.dao.ScoreDao;
import com.score.entity.Course;
import com.score.entity.Score;

import java.util.List;
import java.util.Map;

public class ScoreService {
    private ScoreDao scoreDao = new ScoreDao();
    private CourseDao courseDao = new CourseDao();

    public int addScore(Score score) throws Exception {
        return scoreDao.addScore(score);
    }

    public List<Map<String, Object>> getScoreByCourse(int courseId) throws Exception {
        return scoreDao.findByCourse(courseId);
    }

    public Map<String, Object> getStatistics(int courseId) throws Exception {
        return scoreDao.calcStatistics(courseId);
    }

    public List<Map<String, Object>> getStudentScores(String stuId) throws Exception {
        return scoreDao.findByStudent(stuId);
    }

    public List<Course> getAllCourses() throws Exception {
        return courseDao.findAll();
    }
}
