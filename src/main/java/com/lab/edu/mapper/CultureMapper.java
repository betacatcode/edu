package com.lab.edu.mapper;

import com.lab.edu.model.Culture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ruin
 * @date 2019/9/8-15:52
 */
@Mapper
@Repository
public interface CultureMapper {

    @Select("select * from tb_culture")
    public List<Culture> getAllCulture();

    @Select("select * from tb_culture where id=#{id}")
    public Culture getCultureById(Integer id);
}
