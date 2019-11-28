package com.lab.edu.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ruin
 * @date 2019/7/12-9:43
 */
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        /**
         * 管理员跳转控制
         */
        registry.addViewController("/manage/login").setViewName("/manage/login");
        registry.addViewController("/manage/index").setViewName("/manage/index");
        registry.addViewController("/manage/pie").setViewName("/manage/pie");


        /**
         * 博客页面跳转控制
         */
        registry.addViewController("/blog/single").setViewName("/blog/single");
        registry.addViewController("/exercise/scorePage").setViewName("/exercise/scorePage");

        /**
         * 介绍页面跳转
         */

        registry.addViewController("/education/place/beijing").setViewName("/education/place/beijing");
        registry.addViewController("/education/place/hebei").setViewName("/education/place/hebei");
        registry.addViewController("/education/place/anhui").setViewName("/education/place/anhui");
        registry.addViewController("/education/place/jiangxi").setViewName("/education/place/jiangxi");


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/path/**").addResourceLocations("file:/E:/edu/");
    }
}
