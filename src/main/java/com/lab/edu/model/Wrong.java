package com.lab.edu.model;

/**
 * @author ruin
 * @date 2019/9/19-15:39
 */
public class Wrong {
    private Integer id;
    private Integer userId;
    private String problem;
    private String content;

    public Wrong(Integer id, Integer userId, String problem, String content) {
        this.id = id;
        this.userId = userId;
        this.problem = problem;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
