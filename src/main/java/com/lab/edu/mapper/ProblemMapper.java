package com.lab.edu.mapper;

import com.lab.edu.model.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ruin
 * @date 2019/9/18-12:23
 */

@Repository
@Mapper
public interface ProblemMapper {

    @Select("Select * from tb_problem")
    public List<Problem> getAllProblems();

    @Select("Select * from tb_problem where id=#{id}")
    public Problem getProblemById(Integer id);

    @Select("Select rightAns from tb_problem where id=#{id}")
    public String getRightAnswerById(Integer id);

    @Select("select t.content from (SELECT id,'a' as choice, a as 'content' from tb_problem\n" +
            "UNION ALL\n" +
            "SELECT id,'b' as choice, b as 'content' from tb_problem\n" +
            "UNION ALL\n" +
            "SELECT id,'c' as choice, c as 'content' from tb_problem\n" +
            "UNION ALL\n" +
            "SELECT id,'d' as choice, d as 'content' from tb_problem) as t,tb_problem as p\n" +
            "where p.rightAns=t.choice and p.id=t.id and p.id=#{id}")
    public String getRightProblemContentById(Integer id);
}
