<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.score.entity.Teacher" %>
<%
    Teacher teacher = (Teacher) session.getAttribute("loginUser");
    if(teacher == null){ response.sendRedirect(request.getContextPath()+"/login.jsp"); return; }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>教师端 - 成绩录入</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand mb-0 h1">成绩管理系统 - 教师端</span>
        <div>
            <span class="text-white me-3">欢迎，<%= teacher.getName() %></span>
            <a href="${pageContext.request.contextPath}/teacher/scoreList" class="btn btn-light btn-sm me-2">成绩列表</a>
            <a href="${pageContext.request.contextPath}/teacher/statistics" class="btn btn-light btn-sm me-2">统计分析</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light btn-sm">退出</a>
        </div>
    </div>
</nav>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-light">
                    <h5 class="mb-0">录入学生成绩</h5>
                </div>
                <div class="card-body">
                    <% if(request.getAttribute("msg")!=null){ %>
                        <div class="alert alert-success py-2"><%= request.getAttribute("msg") %></div>
                    <% } %>
                    <form action="${pageContext.request.contextPath}/teacher/scoreAdd" method="post">
                        <div class="mb-3">
                            <label class="form-label">学生学号</label>
                            <input type="text" name="stuId" class="form-control" placeholder="例如：2024001" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">课程编号</label>
                            <input type="number" name="courseId" class="form-control" placeholder="1-JavaEE 2-高数 3-计网" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">分数</label>
                            <input type="number" step="0.01" name="score" class="form-control" placeholder="0-100" min="0" max="100" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">确认录入</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
