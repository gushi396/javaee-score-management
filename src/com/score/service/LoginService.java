package com.score.service;

import com.score.dao.StudentDao;
import com.score.dao.TeacherDao;
import com.score.entity.Student;
import com.score.entity.Teacher;

public class LoginService {
    private TeacherDao teacherDao = new TeacherDao();
    private StudentDao studentDao = new StudentDao();

    public Teacher teacherLogin(String username, String password) throws Exception {
        return teacherDao.findByUsernameAndPassword(username, password);
    }

    public Student studentLogin(String username, String password) throws Exception {
        return studentDao.findByUsernameAndPassword(username, password);
    }
}
