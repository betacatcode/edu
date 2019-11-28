package com.lab.edu.utils;

import com.lab.edu.model.Article;
import com.lab.edu.model.ArticleInfo;
import com.lab.edu.service.ArticleService;
import com.lab.edu.service.UserService;
import com.lab.edu.service.serviceImpl.ArticleServiceImpl;
import com.lab.edu.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ruin
 * @date 2019/7/15-15:26
 */
@Component
public class ArticleUtil {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    public List<ArticleInfo> ArticleToArticleInfo(List<Article> articles){
        List<ArticleInfo> articleInfos=new ArrayList<>();
        for(int i=0;i<articles.size();i++){
            ArticleInfo articleInfo=new ArticleInfo(articles.get(i));
            String userName=userService.getNameById(articles.get(i).getUserId());
            articleInfo.setUserName(userName);

            String allTag=articles.get(i).getTags();
            String[] tagId = allTag.split(",");

            List<String>tags=new ArrayList<>();
            for(int j=0;j<tagId.length;j++){
                String tag=articleService.getTagNameById(Integer.valueOf(tagId[j]));
                tags.add(tag);
            }
            articleInfo.setTagNames(tags);
            articleInfos.add(articleInfo);
        }
        return articleInfos;
    }

    public ArticleInfo ArticleToArticleInfo(Article article){
        ArticleInfo articleInfo=new ArticleInfo(article);
        String userName=userService.getNameById(article.getUserId());
        articleInfo.setUserName(userName);

        String allTag=article.getTags();
        String[] tagId = allTag.split(",");

        List<String>tags=new ArrayList<>();
        for(int j=0;j<tagId.length;j++){
            String tag=articleService.getTagNameById(Integer.valueOf(tagId[j]));
            tags.add(tag);
        }
        articleInfo.setTagNames(tags);

        return articleInfo;
    }
}
