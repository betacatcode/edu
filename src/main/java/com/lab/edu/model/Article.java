package com.lab.edu.model;

import java.sql.Date;

/**
 * @author ruin
 * @date 2019/7/15-9:47
 */
public class Article {

    private Integer articleId;
    private Integer userId;
    private String tags;
    private Date pubTime;
    private Integer commentNum;
    private String title;
    private String content;
    private Integer viewNum;
    private String img;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                ", tags='" + tags + '\'' +
                ", pubTime=" + pubTime +
                ", commentNum=" + commentNum +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewNum=" + viewNum +
                ", img='" + img + '\'' +
                '}';
    }
}
