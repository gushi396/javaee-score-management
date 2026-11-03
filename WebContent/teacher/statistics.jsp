<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.score.entity.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.score.entity.Course" %>
<%
    Teacher teacher = (Teacher) session.getAttribute("loginUser");
    if(teacher == null){ response.sendRedirect(request.getContextPath()+"/login.jsp"); return; }
    List<Course> courseList = (List<Course>) request.getAttribute("courseList");
    Map<String, Object> stats = (Map<String, Object>) request.getAttribute("stats");
    Integer selectedCourseId = (Integer) request.getAttribute("selectedCourseId");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>教师端 - 成绩统计</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand mb-0 h1">成绩管理系统 - 教师端</span>
        <div>
            <span class="text-white me-3">欢迎，<%= teacher.getName() %></span>
            <a href="${pageContext.request.contextPath}/teacher/scoreList" class="btn btn-light btn-sm me-2">成绩列表</a>
            <a href="${pageContext.request.contextPath}/teacher/score_add.jsp" class="btn btn-light btn-sm me-2">录入成绩</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light btn-sm">退出</a>
        </div>
    </div>
</nav>
<div class="container mt-4">
    <div class="card">
        <div class="card-header bg-light">
            <h5 class="mb-0">成绩统计分析（平均分 / 及格率）</h5>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/teacher/statistics" method="get" class="row g-3 mb-4">
                <div class="col-auto">
                    <label class="col-form-label">选择课程：</label>
                </div>
                <div class="col-md-3">
                    <select name="courseId" class="form-select" required>
                        <option value="">--请选择--</option>
                        <% if(courseList!=null){ for(Course c : courseList){ %>
                            <option value="<%= c.getId() %>" <%= selectedCourseId!=null && selectedCourseId.equals(c.getId())?"selected":"" %>>
                                <%= c.getCourseName() %>
                            </option>
                        <% }} %>
                    </select>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary">统计</button>
                </div>
            </form>

            <% if(stats != null){ %>
            <div class="row">
                <div class="col-md-3">
                    <div class="card text-center bg-primary text-white">
                        <div class="card-body">
                            <h3><%= stats.get("avgScore") %></h3>
                            <p class="mb-0">班级平均分</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center bg-success text-white">
                        <div class="card-body">
                            <h3><%= stats.get("passNum") %> / <%= stats.get("totalNum") %></h3>
                            <p class="mb-0">及格人数 / 总人数</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center bg-warning text-white">
                        <div class="card-body">
                            <h3><%= stats.get("passRate") %> %</h3>
                            <p class="mb-0">及格率</p>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
