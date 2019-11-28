package com.lab.edu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.edu.model.Article;
import com.lab.edu.model.ArticleInfo;
import com.lab.edu.model.User;
import com.lab.edu.service.ArticleService;
import com.lab.edu.service.UserService;
import com.lab.edu.utils.ArticleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ruin
 * @date 2019/7/18-16:03
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleUtil articleUtil;

    @ResponseBody
    @RequestMapping("/checkUserName")
    public Integer checkUserName(String userName){
        if(userService.checkUserName(userName)==null)
            return 0;
        return 1;
    }

    @ResponseBody
    @RequestMapping("/checkAccount")
    public Integer checkUserAccount(String account){
        if (userService.checkUserAccount(account)==null)
            return 0;
        return 1;
    }

    @RequestMapping("/doRegister")
    public String doRegister(String userName,String account,String password){

        Date date=new Date(1,1,1);
        userService.doRegister(userName,account,password,date);
        return "/manage/login";
    }

    @RequestMapping("/manage/index")
    public String userList(@RequestParam(defaultValue = "1") Integer pageNum,Model model, HttpSession session){
        if(session.getAttribute("type").equals("admin")){

            Page page= PageHelper.startPage(pageNum,10);
            List<User> users=userService.getAllUsers();

            PageInfo info = new PageInfo<User>(page.getResult());

            model.addAttribute("info",info);
            model.addAttribute("users",users);
        }
        else if(session.getAttribute("type").equals("user")){

            Page page= PageHelper.startPage(pageNum,10);

            Integer userId= (Integer) session.getAttribute("userId");
            List<Article>articles=articleService.getArticleByUserId(userId);
            List<ArticleInfo> articleInfos= articleUtil.ArticleToArticleInfo(articles);

            PageInfo info = new PageInfo<User>(page.getResult());

            model.addAttribute("info",info);
            model.addAttribute("articleInfos",articleInfos);
        }
        return "/manage/index";
    }

    @RequestMapping("/deleteUserById")
    @ResponseBody
    public Integer deleteUserById(Integer id){
        return userService.deleteUserById(id);
    }

    @RequestMapping("/deleteAllById")
    @ResponseBody
    public Integer deleteAllById(String ids){
        String []id=ids.split(",");
        for(int i=0;i<id.length;i++)
            userService.deleteUserById(Integer.parseInt(id[i]));
        return 1;
    }

    @RequestMapping("/userAdd")
    public String userAdd(String userName, String userAccount, String password, Date birth, Integer gender,
                          String profile, MultipartFile img){
        userService.userAdd(userName,userAccount,password,birth,gender,img,profile);
        return "redirect:/manage/index";
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Integer id){
        return userService.getUserByUserID(id);
    }

    @RequestMapping("/userUpdate")
    public String userUpdate(String userName, String userAccount, String password, Date birth, Integer gender,
                          String profile, MultipartFile img,Integer id){
        userService.userUpdate(userName,userAccount,password,birth,gender,img,profile,id);
        return "redirect:/manage/index";
    }

    @RequestMapping("/searchByName")
    public String searchByName(String userName,Model model){
        Page page= PageHelper.startPage(1,10);
        User user=userService.getUserByName(userName);

        List<User> users=new ArrayList<>();
        PageInfo info = new PageInfo<User>(page.getResult());

        if(user==null)
            user=new User();
        users.add(user);
        model.addAttribute("info",info);
        model.addAttribute("users",users);

        return "/manage/index";
    }


}
