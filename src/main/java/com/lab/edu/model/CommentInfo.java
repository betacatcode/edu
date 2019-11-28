package com.lab.edu.model;

/**
 * @author ruin
 * @date 2019/7/16-14:55
 */
public class CommentInfo extends Comment {
    private String userName;
    private String img;

    public CommentInfo(Comment comment){
        super();
        this.setArticleId(comment.getArticleId());
        this.setCommentId(comment.getCommentId());
        this.setContent(comment.getContent());
        this.setPubTime(comment.getPubTime());
        this.setUserId(comment.getUserId());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
