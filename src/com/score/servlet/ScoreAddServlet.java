package com.score.servlet;

import com.score.entity.Score;
import com.score.entity.Teacher;
import com.score.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/teacher/scoreAdd")
public class ScoreAddServlet extends HttpServlet {
    private ScoreService scoreService = new ScoreService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("loginUser");
        if(teacher == null){
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
            return;
        }

        try {
            String stuId = req.getParameter("stuId");
            int courseId = Integer.parseInt(req.getParameter("courseId"));
            double scoreValue = Double.parseDouble(req.getParameter("score"));

            Score score = new Score();
            score.setStuId(stuId);
            score.setCourseId(courseId);
            score.setScoreValue(scoreValue);

            scoreService.addScore(score);
            req.setAttribute("msg", "成绩录入成功");
            req.getRequestDispatcher("/teacher/score_add.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "录入失败，可能该学生此课程已有成绩");
            req.getRequestDispatcher("/teacher/score_add.jsp").forward(req, resp);
        }
    }
}
