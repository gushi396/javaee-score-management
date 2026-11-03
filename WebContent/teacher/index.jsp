<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.score.entity.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.score.entity.Course" %>
<%
    Teacher teacher = (Teacher) session.getAttribute("loginUser");
    if(teacher == null){ response.sendRedirect(request.getContextPath()+"/login.jsp"); return; }
    List<Course> courseList = (List<Course>) request.getAttribute("courseList");
    List<Map<String, Object>> scoreList = (List<Map<String, Object>>) request.getAttribute("scoreList");
    Integer selectedCourseId = (Integer) request.getAttribute("selectedCourseId");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>教师端 - 成绩列表</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand mb-0 h1">成绩管理系统 - 教师端</span>
        <div>
            <span class="text-white me-3">欢迎，<%= teacher.getName() %></span>
            <a href="${pageContext.request.contextPath}/teacher/score_add.jsp" class="btn btn-light btn-sm me-2">录入成绩</a>
            <a href="${pageContext.request.contextPath}/teacher/statistics" class="btn btn-light btn-sm me-2">统计分析</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light btn-sm">退出</a>
        </div>
    </div>
</nav>
<div class="container mt-4">
    <div class="card">
        <div class="card-header bg-light">
            <h5 class="mb-0">班级成绩查询</h5>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/teacher/scoreList" method="get" class="row g-3 mb-3">
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
                    <button type="submit" class="btn btn-primary">查询</button>
                </div>
            </form>

            <% if(scoreList != null){ %>
            <table class="table table-bordered table-striped">
                <thead class="table-light">
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>班级</th>
                        <th>分数</th>
                    </tr>
                </thead>
                <tbody>
                    <% if(scoreList.isEmpty()){ %>
                        <tr><td colspan="4" class="text-center text-muted">暂无成绩数据</td></tr>
                    <% } else { for(Map<String,Object> m : scoreList){ %>
                    <tr>
                        <td><%= m.get("stuId") %></td>
                        <td><%= m.get("stuName") %></td>
                        <td><%= m.get("className") %></td>
                        <td><%= m.get("score") %></td>
                    </tr>
                    <% }} %>
                </tbody>
            </table>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
