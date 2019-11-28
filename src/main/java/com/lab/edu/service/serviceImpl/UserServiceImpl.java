package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.UserMapper;
import com.lab.edu.model.User;
import com.lab.edu.service.UserService;
import com.lab.edu.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * @author ruin
 * @date 2019/7/15-12:10
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    ImageUtil imageUtil;

    @Override
    public String getNameById(Integer id) {
        return userMapper.getNameById(id);
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public String getImgById(Integer id) {
        return userMapper.getImgById(id);
    }

    @Override
    public User getUserByUserID(Integer id) {
        return userMapper.getUserByUserID(id);
    }

    @Override
    public Integer getIdByName(String name) {
        return userMapper.getIdByName(name);
    }

    @Override
    public String checkUserName(String userName) {
        return userMapper.checkUserName(userName);
    }

    @Override
    public String checkUserAccount(String userAccount) {
        return userMapper.checkUserAccount(userAccount);
    }

    @Override
    public Integer doRegister(String userName, String account, String password, Date date) {
        return userMapper.doRegister(userName,account,password,date);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public Integer userAdd(String userName, String account, String password, Date birth, Integer gender, MultipartFile img, String profile) {

        String imgName=imageUtil.saveImage(img,userName,account);
        return userMapper.userAdd(userName,account,password,birth,gender,imgName,profile);
    }

    @Override
    public Integer userUpdate(String userName, String account, String password, Date birth, Integer gender, MultipartFile img, String profile, Integer id) {
        String imgName;
        if(!img.isEmpty()){
            imgName=imageUtil.saveImage(img,userName,account);
        }else{
            imgName=userMapper.getImgByName(userName);
        }

        return userMapper.userUpdate(userName,account,password,birth,gender,imgName,profile,id);
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

}
