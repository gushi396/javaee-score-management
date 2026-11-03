<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.score.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    Student student = (Student) session.getAttribute("loginUser");
    if(student == null){ response.sendRedirect(request.getContextPath()+"/login.jsp"); return; }
    List<Map<String, Object>> myScores = (List<Map<String, Object>>) request.getAttribute("myScores");
    String myAvg = (String) request.getAttribute("myAvg");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>学生端 - 我的成绩</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark bg-success">
    <div class="container">
        <span class="navbar-brand mb-0 h1">成绩管理系统 - 学生端</span>
        <div>
            <span class="text-white me-3">欢迎，<%= student.getName() %>（<%= student.getClassName() %>）</span>
            <a href="${pageContext.request.contextPath}/student/myScore" class="btn btn-light btn-sm me-2">查看成绩</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light btn-sm">退出</a>
        </div>
    </div>
</nav>
<div class="container mt-4">
    <div class="card">
        <div class="card-header bg-light">
            <h5 class="mb-0">我的成绩查询</h5>
        </div>
        <div class="card-body">
            <% if(myScores == null){ %>
                <p class="text-muted">点击上方「查看成绩」按钮加载你的成绩数据。</p>
            <% } else { %>
                <table class="table table-bordered table-striped">
                    <thead class="table-light">
                        <tr>
                            <th>课程名称</th>
                            <th>成绩</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if(myScores.isEmpty()){ %>
                            <tr><td colspan="2" class="text-center text-muted">暂无成绩记录</td></tr>
                        <% } else { for(Map<String,Object> m : myScores){ %>
                        <tr>
                            <td><%= m.get("courseName") %></td>
                            <td><%= m.get("score") %></td>
                        </tr>
                        <% }} %>
                    </tbody>
                </table>
                <div class="alert alert-info">
                    <strong>个人平均分：<%= myAvg %> 分</strong>
                </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
