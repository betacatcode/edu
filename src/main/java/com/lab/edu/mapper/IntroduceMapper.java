package com.lab.edu.mapper;

import com.lab.edu.model.Introduce;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ruin
 * @date 2019/9/8-15:15
 */

@Repository
@Mapper
public interface IntroduceMapper {

    @Select("select * from tb_introduce")
    public List<Introduce>getAllIntroduce();
}
