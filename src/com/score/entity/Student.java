package com.score.entity;

public class Student {
    private String stuId;
    private String username;
    private String password;
    private String name;
    private String className;

    public Student() {}
    public Student(String stuId, String username, String password, String name, String className) {
        this.stuId = stuId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.className = className;
    }

    public String getStuId() { return stuId; }
    public void setStuId(String stuId) { this.stuId = stuId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}
