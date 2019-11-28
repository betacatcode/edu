package com.lab.edu.mapper;

import com.lab.edu.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author ruin
 * @date 2019/7/11-17:27
 */
@Repository
@Mapper
public interface AdminMapper{

    @Select("select * from tb_admin where adID=#{id}")
    public Admin selectAdminById(Integer id);

    @Select("select * from tb_admin where adAccount=#{Account}")
    public Admin getAdminByAccount(String Account);
}
