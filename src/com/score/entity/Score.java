package com.score.entity;

public class Score {
    private Integer id;
    private String stuId;
    private Integer courseId;
    private Double scoreValue;

    public Score() {}
    public Score(Integer id, String stuId, Integer courseId, Double scoreValue) {
        this.id = id;
        this.stuId = stuId;
        this.courseId = courseId;
        this.scoreValue = scoreValue;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getStuId() { return stuId; }
    public void setStuId(String stuId) { this.stuId = stuId; }
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    public Double getScoreValue() { return scoreValue; }
    public void setScoreValue(Double scoreValue) { this.scoreValue = scoreValue; }
}
