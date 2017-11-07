package com.qqf.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@Configuration
public class ShiroConfig {


     @Bean
     public ShiroFilterFactoryBean createFilter(DefaultWebSecurityManager sm){
          ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
          bean.setSecurityManager(sm);
          //登录页面
          bean.setLoginUrl("/");
          bean.setUnauthorizedUrl("/noqx.html");
          //有序
          LinkedHashMap<String,String> data=new LinkedHashMap<>();
          //有类别管理权限才能访问findAllCategory
          data.put("/findAllRoles","perms[角色管理]");
          //保护所有页面除了index.html
          data.put("/index.html","anon");
          //登录后才能访问html
          data.put("/*.html","authc");
          data.put("/**","anon");
          bean.setFilterChainDefinitionMap(data);
          return  bean;
     }
     @Bean
     public DefaultWebSecurityManager securityManager(JdbcRealm realm){
          DefaultWebSecurityManager sm=new DefaultWebSecurityManager();
          sm.setRealm(realm);
          return sm;
     }
     @Bean
     public JdbcRealm jdbcRealm(DruidDataSource druidDataSource){
          JdbcRealm realm=new JdbcRealm();
          realm.setPermissionsLookupEnabled(true);
          realm.setAuthenticationQuery("select password from user where username=?");
          realm.setUserRolesQuery("SELECT r.rolename FROM role r LEFT JOIN userrole ur ON r.roleid=ur.roleid LEFT JOIN USER u ON ur.userid=u.userid WHERE u.username=?");
          realm.setPermissionsQuery("SELECT rs.text FROM res rs LEFT JOIN resrole rr ON rs.id=rr.resid LEFT JOIN role r ON rr.roleid=r.roleid WHERE r.rolename=?");


          realm.setDataSource(druidDataSource);
          return  realm;
     }
     @Bean
     @ConfigurationProperties(prefix = "spring.datasource")
     public DruidDataSource druidDataSource(){
          DruidDataSource druidDataSource=new DruidDataSource();
          return  druidDataSource;
     }


















    /* @Bean
     public ShiroFilterFactoryBean  createFilter(DefaultWebSecurityManager sm){
          ShiroFilterFactoryBean bean= new ShiroFilterFactoryBean();
          bean.setSecurityManager(sm);
          bean.setLoginUrl("/");

          LinkedHashMap<String,String> data = new LinkedHashMap<>();
          data.put("/test.html","authc");
          data.put("*//**","anon");
          bean.setFilterChainDefinitionMap(data);
          return bean;

     }

     @Bean
     public DefaultWebSecurityManager securityManager(JdbcRealm realm){
          DefaultWebSecurityManager sm=new DefaultWebSecurityManager();
          sm.setRealm(realm);
          return sm;
     }


     @Bean
     public JdbcRealm jdbcRealm(DruidDataSource druidDataSource){
          JdbcRealm realm = new JdbcRealm();
          realm.setPermissionsLookupEnabled(true);
          realm.setAuthenticationQuery("select u.password from user u where u.username=?");
          realm.setDataSource(druidDataSource);
          return realm;
     }


     @Bean
     @ConfigurationProperties(prefix = "spring.datasource")
     public DruidDataSource druidDataSource(){
          DruidDataSource druidDataSource = new DruidDataSource();
          return druidDataSource;
     }*/
}
