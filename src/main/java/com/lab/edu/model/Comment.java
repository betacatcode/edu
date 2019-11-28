package com.lab.edu.model;

import java.util.Date;

/**
 * @author ruin
 * @date 2019/7/16-14:52
 */
public class Comment {
    private Integer commentId;
    private Integer articleId;
    private Integer userId;
    private Date pubTime;
    private String content;

    public Comment(Integer commentId,Integer articleId, Integer userId, Date pubTime, String content) {
        this.commentId=commentId;
        this.articleId = articleId;
        this.userId = userId;
        this.pubTime = pubTime;
        this.content = content;
    }

    public Comment() {

    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", pubTime=" + pubTime +
                ", content='" + content + '\'' +
                '}';
    }
}
