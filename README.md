# javaee-score-management

Java EE 课程期末大作业 - 班级学生成绩管理系统

## 项目简介
本项目为 Java EE 课程设计作业，实现了 4A 班级学生成绩的信息化管理。系统采用 MVC 三层架构设计，支持教师与学生两种角色，实现了成绩录入、成绩查询、平均分统计、及格率计算等核心功能。

## 技术栈
- **后端**：Servlet + JSP + JDBC
- **数据库**：MySQL 8.0
- **前端**：JSP + Bootstrap 5
- **Web容器**：Tomcat 9.0
- **架构模式**：MVC 三层架构（Entity/DAO/Service/Servlet）

## 功能模块
### 教师端
- 教师账号登录与退出
- 学生成绩录入
- 按课程查看班级全部成绩
- 班级成绩统计：平均分、及格人数、及格率

### 学生端
- 学生账号登录与退出
- 个人所有课程成绩查询
- 个人平均分计算展示

## 数据库设计
共 4 张数据表：
1. `teacher` - 教师信息表
2. `student` - 学生信息表
3. `course` - 课程信息表
4. `score` - 成绩表（关联学生与课程）

数据库初始化脚本：`sql/init.sql`

## 项目目录结构

├── src/com/score
│   ├── entity      # 实体类
│   ├── dao         # 数据访问层
│   ├── service     # 业务逻辑层
│   ├── servlet     # 控制层
│   ├── filter      # 过滤器
│   └── util        # 工具类
├── WebContent
│   ├── login.jsp   # 登录页
│   ├── teacher/    # 教师端页面
│   ├── student/    # 学生端页面
│   └── WEB-INF
│       ├── lib     # 依赖 jar 包
│       └── web.xml # 配置文件
├── sql
│   └── init.sql    # 数据库脚本
└── README.md

## 部署运行步骤
1. 安装 JDK 8 + Tomcat 9.0 + MySQL 8.0
2. 在 MySQL 中执行 `sql/init.sql` 脚本创建数据库和测试数据
3. 修改 `src/com/score/util/DBUtil.java` 中的数据库账号密码为本地配置
4. 将 MySQL 驱动 jar 包放入 `WebContent/WEB-INF/lib` 目录
5. 将项目导入 Eclipse/IDEA，配置 Tomcat 并发布运行
6. 访问地址：`http://localhost:8080/javaee-score-management`

## 测试账号
| 角色 | 账号 | 密码 |
|------|------|------|
| 教师 | teacher01 | 123456 |
| 学生 | stu001 | 123456 |
| 学生 | stu002 | 123456 |
| 学生 | stu003 | 123456 |
