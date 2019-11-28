package com.lab.edu.mapper;

import com.lab.edu.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ruin
 * @date 2019/7/16-15:02
 */
@Mapper
@Repository
public interface CommentMapper {

    @Select("select * from tb_comment where articleId=#{id}")
    public List<Comment>getCommentByArticleId(Integer id);

    @Insert("insert into tb_comment(articleId,userId,pubTime,content) " +
            "values (#{articleId},#{userId},#{pubTime},#{content})")
    public Integer insertOneComment(Comment comment);
}
