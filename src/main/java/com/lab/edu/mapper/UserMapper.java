package com.lab.edu.mapper;

import com.lab.edu.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


/**
 * @author ruin
 * @date 2019/7/15-12:05
 */

@Repository
@Mapper
public interface UserMapper {
    @Select("select * from tb_user")
    public List<User> getAllUsers();

    @Select("select userName from tb_user where userId=#{id}")
    public String getNameById(Integer id);

    @Select("select * from tb_user where userAccount=#{account}")
    public User getUserByAccount(String account);

    @Select("select * from tb_user where userName=#{userName}")
    public User getUserByName(String userName);

    @Select("select img from tb_user where userId=#{id}")
    public String getImgById(Integer id);

    @Select("select img from tb_user where userName=#{name}")
    public String getImgByName(String name);

    @Select("select * from tb_user where userId=#{id}")
    public User getUserByUserID(Integer id);

    @Select("select userId from tb_user where userName=#{name}")
    public Integer getIdByName(String name);

    @Select("select userName from tb_user where userName=#{userName}")
    public String checkUserName(String userName);

    @Select("SELECT userAccount from tb_user WHERE userAccount=#{userAccount}")
    public String checkUserAccount(String userAccount);

    @Insert("Insert into tb_user(userName,userAccount,userPassWord,birth,gender,img,profile)" +
            "values(#{userName},#{account},#{password},#{birth},0,'origin.jpg','这个人很懒,还没有简介')")
    public Integer doRegister(@Param("userName") String userName, @Param("account") String account,
                              @Param("password") String password, @Param("birth")Date birth);

    @Insert("Insert into tb_user(userName,userAccount,userPassWord,birth,gender,img,profile)"+
            "values(#{userName},#{account},#{password},#{birth},#{gender},#{img},#{profile})")
    public Integer userAdd(@Param("userName") String userName, @Param("account") String account,
                           @Param("password") String password, @Param("birth")Date birth,
                           @Param("gender")Integer gender,@Param("img")String img,@Param("profile")String profile);

    @Update("Update tb_user set userName=#{userName},userAccount=#{account},userPassWord=#{password}," +
            "birth=#{birth},gender=#{gender},img=#{img},profile=#{profile} where userId=#{id}")
    public Integer userUpdate(@Param("userName") String userName, @Param("account") String account,
                              @Param("password") String password, @Param("birth")Date birth,
                              @Param("gender")Integer gender,@Param("img")String img,@Param("profile")String profile,@Param("id")Integer id);

    @Delete("delete tb_user where userId=#{id}")
    public Integer deleteUserById(Integer id);


}
