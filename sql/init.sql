-- 创建数据库
CREATE DATABASE score_management DEFAULT CHARACTER SET utf8mb4;
USE score_management;

-- 1. 教师表
CREATE TABLE teacher (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL DEFAULT '123456',
    name VARCHAR(20) NOT NULL
);

-- 2. 学生表
CREATE TABLE student (
    stu_id VARCHAR(20) PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL DEFAULT '123456',
    name VARCHAR(20) NOT NULL,
    class_name VARCHAR(30) NOT NULL
);

-- 3. 课程表
CREATE TABLE course (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(50) NOT NULL UNIQUE
);

-- 4. 成绩表
CREATE TABLE score (
    id INT PRIMARY KEY AUTO_INCREMENT,
    stu_id VARCHAR(20) NOT NULL,
    course_id INT NOT NULL,
    score DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (stu_id) REFERENCES student(stu_id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    UNIQUE KEY uniq_stu_course (stu_id, course_id)
);

-- 初始化测试数据
INSERT INTO teacher (username, name) VALUES ('teacher01', '张老师');
INSERT INTO student (stu_id, username, name, class_name) VALUES 
('2024001', 'stu001', '小明', '4A班'),
('2024002', 'stu002', '小红', '4A班'),
('2024003', 'stu003', '小刚', '4A班');
INSERT INTO course (course_name) VALUES ('Java EE程序设计'),('高等数学'),('计算机网络');
