<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>班级成绩管理系统 - 登录</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f5f7fa; height: 100vh; display: flex; align-items: center; justify-content: center; }
        .login-card { box-shadow: 0 4px 20px rgba(0,0,0,0.1); border-radius: 12px; }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <h2 class="text-center mb-4 text-primary">4A班学生成绩管理系统</h2>
            <div class="card login-card">
                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="mb-3">
                            <label class="form-label fw-bold">登录身份</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" value="teacher" checked id="r1">
                                <label class="form-check-label" for="r1">教师</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" value="student" id="r2">
                                <label class="form-check-label" for="r2">学生</label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">账号</label>
                            <input type="text" name="username" class="form-control" placeholder="请输入账号" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">密码</label>
                            <input type="password" name="password" class="form-control" placeholder="请输入密码" required>
                        </div>
                        <% if(request.getAttribute("msg")!=null){ %>
                            <div class="alert alert-danger py-2"><%= request.getAttribute("msg") %></div>
                        <% } %>
                        <button type="submit" class="btn btn-primary w-100 py-2">登 录</button>
                    </form>
                    <div class="mt-3 text-muted small">
                        <p class="mb-1">测试账号：</p>
                        <p class="mb-0">教师：teacher01 / 123456</p>
                        <p class="mb-0">学生：stu001 / 123456</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
