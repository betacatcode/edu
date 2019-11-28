package com.lab.edu.mapper;

import com.lab.edu.model.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ruin
 * @date 2019/8/26-15:08
 */
@Repository
@Mapper
public interface VideoMapper {

    @Select("select * from tb_video")
    public List<Video>getAllVideo();

    @Select("select * from tb_video  WHERE title LIKE CONCAT('%',#{title},'%')")
    public List<Video>searchByTitle(String title);
}
