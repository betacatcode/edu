package com.lab.edu.model;

import java.util.List;

/**
 * @author ruin
 * @date 2019/7/15-12:27
 */
public class ArticleInfo extends Article {
    private String userName;
    private List<String> tagNames;

    public ArticleInfo(Article article){
        this.setArticleId(article.getArticleId());
        this.setCommentNum(article.getCommentNum());
        this.setTitle(article.getTitle());
        this.setContent(article.getContent());
        this.setImg(article.getImg());
        this.setViewNum(article.getViewNum());
        this.setPubTime(article.getPubTime());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}
