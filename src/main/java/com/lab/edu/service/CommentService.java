package com.lab.edu.service;

import com.lab.edu.model.Comment;

import java.util.List;

/**
 * @author ruin
 * @date 2019/7/16-15:13
 */
public interface CommentService {
    public List<Comment> getCommentByArticleId(Integer id);

    public Integer insertOneComment(Comment comment);
}
