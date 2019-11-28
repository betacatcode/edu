package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.ArticleMapper;
import com.lab.edu.model.Article;
import com.lab.edu.model.Tag;
import com.lab.edu.service.ArticleService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ruin
 * @date 2019/7/15-9:55
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<Article> getAllArticle() {
        return articleMapper.getAllArticle();
    }

    @Override
    public String getTagNameById(Integer Id) {
        return articleMapper.getTagNameById(Id);
    }

    @Override
    public List<Tag> getAllTag() {
        return articleMapper.getAllTag();
    }

    @Override
    public List<Article> searchByTitle(String title) {
        return articleMapper.searchByTitle(title);
    }

    @Override
    public List<Article> getMostPopularFour() {
        return articleMapper.getMostPopularFour();
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public List<Integer> getArticlesByTagId(Integer id){
        return articleMapper.getArticlesByTagId(id);
    }

    @Override
    public Integer increaseCommentNumByArticleId(Integer id) {
        return articleMapper.increaseCommentNumByArticleId(id);
    }

    @Override
    public Integer increaseViewNumByArticleId(Integer id) {
        return articleMapper.increaseViewNumByArticleId(id);
    }

    @Override
    public List<Article> getArticleByUserId(Integer id) {
        return articleMapper.getArticleByUserId(id);
    }

    @Override
    public Integer saveArticle(Integer userId,String title, String content, MultipartFile img, String tag){

        /*
        获取时间
         */
        Date date=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        Date currentTime=formatter.parse(dateString,new ParsePosition(0));

        /*
        存储图像
         */
        String imgName="article-userId-"+userId+"-"+System.currentTimeMillis()+".jpg";
        String path="E:\\edu";
        File dir=new File(path);
        String pathFile=path+File.separator+imgName;
        File newFile=new File(pathFile);

        if(!dir.exists())
            dir.mkdirs();
        else{
            if(img.getSize()!=0) {
                try {
                    img.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /*
        处理标签
         */
        tag=tag.trim();
        String tagIds="";
        String[]tags=tag.split("\\s+");
        for(int i=0;i<tags.length;i++){
            Integer id=null;
            id=articleMapper.getTagIdByName(tags[i]);
            if(id!=null)
                articleMapper.increaseTagNumByName(tags[i]);
            else {
                articleMapper.insertNewTag(tags[i]);
                id=articleMapper.getTagIdByName(tags[i]);
            }
            tagIds+=id;
            if(i!=tags.length-1)
                tagIds+=",";
        }

        articleMapper.saveArticle(userId,tagIds,currentTime,title,content,imgName);
        return 1;
    }

    @Override
    public Integer deleteArticleById(Integer id) {

        String allTag=articleMapper.getTagsByArticleId(id);
        String[]tags=allTag.split(",");

        for(int i=0;i<tags.length;i++){
            Integer tagId=Integer.valueOf(tags[i]);
            Integer tagNum=articleMapper.getTagNumByTagId(tagId);
            if(tagNum>1) {
                Integer n1=articleMapper.decreaseTagNumByTagId(tagId);
            } else{
                Integer n2=articleMapper.deleteTagByTagId(tagId);
            }

        }

        articleMapper.deleteArticleById(id);

        return 1;
    }

    @Override
    public Integer updateArticle(Integer id, String title, String content, MultipartFile img, String tag,String originTag) {
         /*
        获取时间
         */
        Date date=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        Date currentTime=formatter.parse(dateString,new ParsePosition(0));

        /*
        存储图像
         */
        String imgName="article-Id-"+id+"-"+System.currentTimeMillis()+".jpg";
        String path="E:\\edu";
        File dir=new File(path);
        String pathFile=path+File.separator+imgName;
        File newFile=new File(pathFile);

        if(!dir.exists())
            dir.mkdirs();
        else{
            if(img.getSize()!=0) {
                try {
                    img.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                imgName=articleMapper.getImgById(id);
            }
        }

        /*
        处理标签
         */

        tag=tag.trim();
        Set<String> set1=new HashSet<String>(Arrays.asList(originTag.split("\\s+")));
        Set<String> set2=new HashSet<String>(Arrays.asList(tag.split("\\s+")));

        Set<String> commonSet = new HashSet<String>();

        commonSet.addAll(set1);
        commonSet.retainAll(set2);
        System.out.println("交集:"+commonSet);

        set1.removeAll(commonSet);
        set2.removeAll(commonSet);

        for(String str:set1){
            Integer num=articleMapper.getTagNumByTagName(str);
            if(num==1)
                articleMapper.deleteTagByTagName(str);
            else
                articleMapper.decreaseTagNumByTagName(str);
        }

        for(String str:set2){
            if(articleMapper.getTagIdByName(str)==null)
                articleMapper.insertNewTag(str);
            else
                articleMapper.increaseTagNumByName(str);
        }

        //将名字序列转为id序列
        String tagIds="";
        String[]tags=tag.split("\\s+");
        for(int i=0;i<tags.length;i++){
            Integer tid=null;
            tid=articleMapper.getTagIdByName(tags[i]);
            tagIds+=tid;
            if(i!=tags.length-1)
                tagIds+=",";
        }

        articleMapper.updateArticle(tagIds,title,content,imgName,id);
        return 1;
    }

    @Override
    public List<Article> getArticlesByIds(List<Integer> ids) {
        return articleMapper.getArticlesByIds(ids);
    }


}
