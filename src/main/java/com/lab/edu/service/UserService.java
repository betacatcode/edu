package com.lab.edu.service;

import com.lab.edu.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

/**
 * @author ruin
 * @date 2019/7/15-12:09
 */
public interface UserService {

    public String getNameById(Integer id);

    public User getUserByAccount(String account);

    public String getImgById(Integer id);

    public User getUserByUserID(Integer id);

    public Integer getIdByName(String name);

    public String checkUserName(String userName);

    public String checkUserAccount(String userAccount);

    public Integer doRegister(String userName, String account, String password, Date date);

    public List<User> getAllUsers();

    public Integer deleteUserById(Integer id);

    public Integer userAdd(String userName, String account, String password, Date birth, Integer gender, MultipartFile img, String profile);

    public Integer userUpdate(String userName, String account, String password,Date birth,
                              Integer gender,MultipartFile img,String profile,Integer id);

    public User getUserByName(String userName);

}
