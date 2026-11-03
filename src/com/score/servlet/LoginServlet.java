package com.score.servlet;

import com.score.entity.Student;
import com.score.entity.Teacher;
import com.score.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String role = req.getParameter("role");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        try {
            if("teacher".equals(role)){
                Teacher teacher = loginService.teacherLogin(username, password);
                if(teacher != null){
                    session.setAttribute("loginUser", teacher);
                    session.setAttribute("role", "teacher");
                    resp.sendRedirect(req.getContextPath()+"/teacher/index.jsp");
                } else {
                    req.setAttribute("msg", "账号或密码错误");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            } else {
                Student student = loginService.studentLogin(username, password);
                if(student != null){
                    session.setAttribute("loginUser", student);
                    session.setAttribute("role", "student");
                    resp.sendRedirect(req.getContextPath()+"/student/index.jsp");
                } else {
                    req.setAttribute("msg", "账号或密码错误");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "系统异常");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
