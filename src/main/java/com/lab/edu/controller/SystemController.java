package com.lab.edu.controller;

import com.lab.edu.model.Article;
import com.lab.edu.model.Tag;
import com.lab.edu.model.User;
import com.lab.edu.model.Video;
import com.lab.edu.service.ArticleService;
import com.lab.edu.service.UserService;
import com.lab.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author ruin
 * @date 2019/8/26-16:49
 */
@Controller
public class SystemController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    VideoService videoService;

    @RequestMapping(value = {"/home","/","/index"})
    public String goIndex(Model model){
        List<Tag> tags=articleService.getAllTag();
        model.addAttribute("tags",tags);
        return "/index";
    }

    @RequestMapping("/searchAll")
    public String searchAll(String key,RedirectAttributes attributes,Model model){
        //第一次按用户名字搜索
        User user=userService.getUserByName(key);
        if(user!=null){
            attributes.addAttribute("userName",key);
            return "redirect:/findUserSpace";
        }
        else{
            List<Article> articles=articleService.searchByTitle(key);
            if(articles.size()!=0){
                attributes.addAttribute("title",key);
                return "redirect:/blog/searchByTitle";
            }else {
                List<Video> videos=videoService.searchByTitle(key);
                if (videos.size()!=0){
                    model.addAttribute("videos",videos);
                    return "/education/searchByTitle.html";
                }
            }
            return "/error/noSearch";
        }
    }
}
