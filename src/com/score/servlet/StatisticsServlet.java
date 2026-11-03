package com.score.servlet;

import com.score.entity.Course;
import com.score.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/teacher/statistics")
public class StatisticsServlet extends HttpServlet {
    private ScoreService scoreService = new ScoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        try {
            List<Course> courseList = scoreService.getAllCourses();
            req.setAttribute("courseList", courseList);

            String courseIdStr = req.getParameter("courseId");
            if(courseIdStr != null && !courseIdStr.isEmpty()){
                int courseId = Integer.parseInt(courseIdStr);
                Map<String, Object> stats = scoreService.getStatistics(courseId);
                req.setAttribute("stats", stats);
                req.setAttribute("selectedCourseId", courseId);
            }
            req.getRequestDispatcher("/teacher/statistics.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/teacher/statistics.jsp");
        }
    }
}
