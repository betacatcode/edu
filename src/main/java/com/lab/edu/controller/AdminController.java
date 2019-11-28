package com.lab.edu.controller;

import com.lab.edu.model.Admin;
import com.lab.edu.model.Article;
import com.lab.edu.model.Tag;
import com.lab.edu.model.User;
import com.lab.edu.service.ArticleService;
import com.lab.edu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


/**
 * @author ruin
 * @date 2019/7/12-9:39
 */
@Controller
public class AdminController {

    @Autowired
    ArticleService articleService;


    @RequestMapping("/doLogin")
    public String doAdLogin(String account, String passWord, Model model, HttpSession session){

        Subject subject= SecurityUtils.getSubject();
        String type = null;
        UsernamePasswordToken token=new UsernamePasswordToken(account,passWord);

        try{
            subject.login(token);
            PrincipalCollection principalCollection = subject.getPrincipals();
            Set<String> realmNames = principalCollection.getRealmNames();

            for(String s:realmNames){
                type=s;
                session.setAttribute("type",type);
            }

            if(type.equals("user")){
                User userInfo= (User) principalCollection.getPrimaryPrincipal();
                session.setAttribute("userId",userInfo.getUserId());
                session.setAttribute("userName",userInfo.getUserName());
            }else if(type.equals("admin")){
                Admin adminInfo= (Admin) principalCollection.getPrimaryPrincipal();
                session.setAttribute("adminId",adminInfo.getAdID());
            }
            return "redirect:/manage/index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","该账号不存在");
            return "/manage/login";
        }catch (IncorrectCredentialsException e){
            //登陆失败:密码错误
            model.addAttribute("msg","密码错误");
            return "/manage/login";
        }
    }

    @RequestMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "/index";
    }

}
