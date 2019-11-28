package com.lab.edu.mapper;

import com.lab.edu.model.Wrong;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ruin
 * @date 2019/9/19-15:43
 */

@Repository
@Mapper
public interface WrongMapper {

    @Insert("insert into tb_wrongque(userId,problem,content)\n" +
            "select #{userId},#{problem},#{content}\n" +
            "WHERE NOT EXISTS (SELECT * FROM tb_wrongque WHERE problem=#{problem})")
    public Integer saveWrongQuestion(@Param("userId") Integer userId,@Param("problem") String problem,
                                     @Param("content") String content);

    @Select("select * from tb_wrongque where userId=#{userId}")
    public List<Wrong> getAllWrongQuestions(Integer userId);

    @Delete("delete from tb_wrongque where id=#{id}")
    public Integer deleteWrongQuestionsById(Integer id);
}
