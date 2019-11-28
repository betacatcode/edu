package com.lab.edu.service;

import com.lab.edu.model.Article;
import com.lab.edu.model.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ruin
 * @date 2019/7/15-9:54
 */
public interface ArticleService {

    public List<Article> getAllArticle();

    public String getTagNameById(Integer Id);

    public List<Tag> getAllTag();

    public List<Article> searchByTitle(String title);

    public List<Article> getMostPopularFour();

    public Article getArticleById(Integer id);

    public List<Integer> getArticlesByTagId(Integer id);

    public Integer increaseCommentNumByArticleId(Integer id);

    public Integer increaseViewNumByArticleId(Integer id);

    public List<Article> getArticleByUserId(Integer Id);

    public Integer saveArticle(Integer userId,String title, String content, MultipartFile img, String tag);

    public Integer deleteArticleById(Integer id);

    public Integer updateArticle(Integer id,String title,String content,MultipartFile img,String tag,String originTag);

    public List<Article> getArticlesByIds(List<Integer> ids);
}
