package com.lab.edu.mapper;

import com.lab.edu.model.Article;
import com.lab.edu.model.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ruin
 * @date 2019/7/15-9:52
 */

@Repository
@Mapper
public interface ArticleMapper {

    @Select("select * from tb_article order by articleId desc")
    public List<Article> getAllArticle();

    @Select("select * from tb_article where userId=#{id}")
    public List<Article> getArticleByUserId(Integer Id);

    @Select("SELECT * from (SELECT a.articleId,tag = substring(a.tags , b.number , charindex(',' , a.tags + ',' , b.number) - b.number) \n" +
            "from tb_article a join master..spt_values  b\n" +
            "on b.type='p' and b.number between 1 and len(a.tags)\n" +
            "WHERE substring(',' + a.tags , b.number , 1) = ',') B where B.tag=#{id}")
    public List<Integer> getArticlesByTagId(Integer id);

    @Select("<script>"
             + "SELECT * FROM tb_article WHERE articleId IN "
             + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
             + "#{item}"
             + "</foreach>"
             + "</script>")
    public List<Article> getArticlesByIds(@Param("ids") List<Integer> ids);

    @Select("select tagName from tb_tag where tagId=#{id}")
    public String getTagNameById(Integer Id);

    @Select("select tagId from tb_tag where tagName=#{name}")
    public Integer getTagIdByName(String name);

    @Update("update tb_tag set tagNum=tagNum+1 where tagName=#{name}")
    public Integer increaseTagNumByName(String name);

    @Insert("insert into tb_tag(tagName,tagNum) values(#{name},1)")
    public Integer insertNewTag(String name);

    @Select("select * from tb_tag")
    public List<Tag> getAllTag();

    @Select("SELECT * FROM tb_article WHERE title LIKE CONCAT('%',#{title},'%')")
    public List<Article> searchByTitle(String title);

    @Select("select top 4 * from tb_article ORDER BY viewNum desc")
    public List<Article> getMostPopularFour();

    @Select("select * from tb_article where articleId=#{id}")
    public Article getArticleById(Integer id);

    @Update("update tb_article SET commentNum=commentNum+1 WHERE articleId=#{id}")
    public Integer increaseCommentNumByArticleId(Integer id);

    @Update("update tb_article set viewNum=viewNum+1 where articleId=#{id}")
    public Integer increaseViewNumByArticleId(Integer id);

    @Insert("insert into tb_article (userId,tags,pubTime,commentNum,title,content,img,viewNum)" +
            "values(#{userId},#{tags},#{pubTime},0,#{title},#{content},#{img},0)")
    public Integer saveArticle(@Param("userId") Integer userId, @Param("tags")String tags,
                               @Param("pubTime")Date pubTime, @Param("title")String title,
                               @Param("content")String content, @Param("img")String img);

    @Update("update tb_article set tags=#{tags},title=#{title},content=#{content},img=#{img}" +
                           " where articleId=#{id}")
    public Integer updateArticle(@Param("tags")String tags, @Param("title")String title,
                                 @Param("content")String content, @Param("img")String img,@Param("id")Integer id);

    @Delete("delete tb_article where articleId=#{id}")
    public Integer deleteArticleById(Integer id);

    @Select("select tags from tb_article where articleId=#{id}")
    public String getTagsByArticleId(Integer id);

    @Select("select tagNum from tb_tag where tagId=#{id}")
    public Integer getTagNumByTagId(Integer id);

    @Update("update tb_tag set tagNum=tagNum-1 where tagId=#{id}")
    public Integer decreaseTagNumByTagId(Integer id);

    @Delete("delete tb_tag where tagId=#{id}")
    public Integer deleteTagByTagId(Integer id);

    @Select("select tagNum from tb_tag where tagName=#{name}")
    public Integer getTagNumByTagName(String name);

    @Update("update tb_tag set tagNum=tagNum-1 where tagName=#{name}")
    public Integer decreaseTagNumByTagName(String name);

    @Update("update tb_tag set tagNum=tagNum+1 where tagName=#{name}")
    public Integer increaseTagNumByTagName(String name);

    @Delete("delete tb_tag where tagName=#{name}")
    public Integer deleteTagByTagName(String name);

    @Select("select img from tb_article where articleId=#{id}")
    public String getImgById(Integer id);
}
