package com.lab.edu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.edu.model.*;
import com.lab.edu.service.ArticleService;
import com.lab.edu.service.CommentService;
import com.lab.edu.service.UserService;
import com.lab.edu.utils.ArticleUtil;
import com.lab.edu.utils.CommentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ruin
 * @date 2019/7/15-10:03
 */
@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleUtil articleUtil;

    @Autowired
    CommentUtil commentUtil;


    @RequestMapping("/blog/home")
    public String goBlogHome(@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        Page page= PageHelper.startPage(pageNum,3);

        List<Article> articles=articleService.getAllArticle();

        PageInfo info = new PageInfo<Article>(page.getResult());

        List<ArticleInfo> articleInfos= articleUtil.ArticleToArticleInfo(articles);
        List<Tag> tags=articleService.getAllTag();
        List<Article> popularArticle=articleService.getMostPopularFour();

        //文章信息
        model.addAttribute("articleInfos",articleInfos);
        //页数信息
        model.addAttribute("info",info);
        model.addAttribute("tags",tags);
        model.addAttribute("populars",popularArticle);
        return "/blog/home";
    }

    @RequestMapping("/blog/searchByTitle")
    public String searchByTitle(String title,@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        Page page= PageHelper.startPage(pageNum,2);

        List<Article> articles=articleService.searchByTitle(title);
        PageInfo info = new PageInfo<Article>(page.getResult());

        List<ArticleInfo> articleInfos= articleUtil.ArticleToArticleInfo(articles);

        model.addAttribute("articleInfos",articleInfos);
        model.addAttribute("info",info);
        model.addAttribute("title",title);
        return "/blog/searchByTitle";

    }

    @RequestMapping("/blog/searchByTag")
    public String searchPage(Integer tagId,@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        List<Integer> ids=articleService.getArticlesByTagId(tagId);

        Page page= PageHelper.startPage(pageNum,2);
        List<Article> articles=articleService.getArticlesByIds(ids);
        PageInfo info = new PageInfo<Article>(page.getResult());

        List<ArticleInfo> articleInfos= articleUtil.ArticleToArticleInfo(articles);

        model.addAttribute("articleInfos",articleInfos);
        model.addAttribute("info",info);
        model.addAttribute("tagId",tagId);
        return "/blog/searchByTag";
    }


    @RequestMapping("/viewMore")
    public String viewMore(Integer articleId,Model model){

        articleService.increaseViewNumByArticleId(articleId);

        Article article=articleService.getArticleById(articleId);
        ArticleInfo articleInfo=articleUtil.ArticleToArticleInfo(article);
        List<Comment> comments=commentService.getCommentByArticleId(articleId);

        List<CommentInfo> commentInfos=commentUtil.commentToCommentInfo(comments);

        model.addAttribute("commentInfos",commentInfos);
        model.addAttribute("info",articleInfo);
        return "/blog/single";
    }

    @RequestMapping("/submitComment")
    public String submitComment(Integer userId,Integer articleId,String content){

        Date pubTime=new Date();

        Comment comment=new Comment(null,articleId,userId,pubTime,content);

        commentService.insertOneComment(comment);
        articleService.increaseCommentNumByArticleId(articleId);

        return "redirect:/viewMore?articleId="+articleId;
    }

    @RequestMapping("/blog/space")
    public String goBlogSpace(@RequestParam(defaultValue = "1") Integer pageNum, Model model,Integer userId){
        Page page= PageHelper.startPage(pageNum,3);
        List<Article> articles = articleService.getArticleByUserId(userId);

        PageInfo info = new PageInfo<Article>(page.getResult());

        List<ArticleInfo> articleInfos = articleUtil.ArticleToArticleInfo(articles);

        User user=userService.getUserByUserID(userId);

        //用户信息
        model.addAttribute("user",user);
        //文章信息
        model.addAttribute("articleInfos",articleInfos);
        //页数信息
        model.addAttribute("info",info);

        return "/blog/space";

    }

    @RequestMapping("/findUserSpace")
    public String findUserSpace(String userName, HttpSession session){

        Integer nowId=(Integer) session.getAttribute("userId");
        Integer id = userService.getIdByName(userName);

        if(id==null)
            id=nowId;
        return "redirect:/blog/space?userId="+id;
    }

    @RequestMapping("/addArticle")
    public String addArticle(Integer userId,String title, String content, MultipartFile img,String tag){

        articleService.saveArticle(userId,title,content,img,tag);
        return "redirect:/manage/index";
    }

    @RequestMapping("/updateArticle")
    public String updateArticle(Integer id,String title,String content,MultipartFile img,String tag,String originTag){
        articleService.updateArticle(id,title,content,img,tag,originTag);
        return "redirect:/manage/index";
    }

    @RequestMapping("/deleteArticleById")
    @ResponseBody
    public Integer deleteArticleById(Integer id){
        return articleService.deleteArticleById(id);
    }

    @RequestMapping("/deleteArticleByIds")
    @ResponseBody
    public Integer deleteArtilceByIds(String ids){
        String []id=ids.split(",");
        for(String i:id){
            articleService.deleteArticleById(Integer.valueOf(i));
        }

        return 1;
    }

    @RequestMapping("/searchByArticleTitle")
    public String searchByArticleTitle(String title,@RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        Page page= PageHelper.startPage(pageNum,2);

        List<Article> articles=articleService.searchByTitle(title);
        PageInfo info = new PageInfo<Article>(page.getResult());

        List<ArticleInfo> articleInfos= articleUtil.ArticleToArticleInfo(articles);

        model.addAttribute("articleInfos",articleInfos);
        model.addAttribute("info",info);

        return "/manage/index";
    }

    @RequestMapping("/getArticleById")
    @ResponseBody
    public ArticleInfo getArticleById(Integer id){
        Article article=articleService.getArticleById(id);
        ArticleInfo articleInfo=articleUtil.ArticleToArticleInfo(article);
        return articleInfo;
    }
}
