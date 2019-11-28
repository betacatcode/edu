package com.lab.edu.config;

import com.lab.edu.shiro.CommonRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ruin
 * @date 2019/7/12-9:44
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterMap=new LinkedHashMap<>();

        filterMap.put("/manage/**","authc");
        filterMap.put("/exercise/**","authc");
        filterMap.put("/blog/single","authc");
        filterMap.put("/submitComment","authc");
        filterMap.put("/blog/space","authc");
        filterMap.put("/logout","logout");
        shiroFilterFactoryBean.setLoginUrl("/manage/login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;

    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("commonRealm")CommonRealm commonRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(commonRealm);
        return securityManager;
    }

    @Bean(name="commonRealm")
    public CommonRealm getCommonRealm(){
        return new CommonRealm();
    }

}
