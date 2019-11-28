package com.lab.edu;

import com.lab.edu.mapper.AdminMapper;
import com.lab.edu.model.Article;
import com.lab.edu.service.ArticleService;
import com.lab.edu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EduApplicationTests {

	@Autowired
	AdminMapper adminMapper;

	@Autowired
	ArticleService articleService;

	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSelectById(){
		System.out.println(adminMapper.selectAdminById(1));
	}

	@Test
	public void testSelectAllArticle(){
		List<Article> l= articleService.getAllArticle();
		System.out.println(l.size());
	}

	@Test
	public void insertUserTestData(){
		for(int i=5;i<=30;i++){
			String userName="test"+i;
			String userAccount="t"+i;
			String passWord="p"+i;
			Date date=new Date(1,1,1);
			userService.doRegister(userName,userAccount,passWord,date);
		}
	}

	@Test
	public void setTest(){
		String []str1={"a","b"};
		String []str2={"b","c"};
		Set<String> set1=new HashSet<String>(Arrays.asList(str1));
		Set<String> set2=new HashSet<String>(Arrays.asList(str2));
		Set<String> commonSet = new HashSet<String>();

		commonSet.addAll(set1);
		commonSet.retainAll(set2);
		System.out.println("交集:"+commonSet);

		set1.removeAll(commonSet);
		set2.removeAll(commonSet);

		System.out.println("A剩下的:"+set1);
		System.out.println("B剩下的:"+set2);

	}
}
