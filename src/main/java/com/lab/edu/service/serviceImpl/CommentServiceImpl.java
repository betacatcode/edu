package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.CommentMapper;
import com.lab.edu.model.Comment;
import com.lab.edu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ruin
 * @date 2019/7/16-15:13
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentByArticleId(Integer id) {
        return commentMapper.getCommentByArticleId(id);
    }

    @Override
    public Integer insertOneComment(Comment comment) {
        return commentMapper.insertOneComment(comment);
    }


}
