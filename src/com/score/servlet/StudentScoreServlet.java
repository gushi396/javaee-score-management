package com.score.servlet;

import com.score.entity.Student;
import com.score.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/student/myScore")
public class StudentScoreServlet extends HttpServlet {
    private ScoreService scoreService = new ScoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("loginUser");
        if(student == null){
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
            return;
        }

        try {
            List<Map<String, Object>> myScores = scoreService.getStudentScores(student.getStuId());
            req.setAttribute("myScores", myScores);

            // 计算个人平均分
            double total = 0;
            for(Map<String, Object> m : myScores){
                total += (Double) m.get("score");
            }
            double avg = myScores.isEmpty() ? 0 : total / myScores.size();
            req.setAttribute("myAvg", String.format("%.2f", avg));

            req.getRequestDispatcher("/student/index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/student/index.jsp");
        }
    }
}
