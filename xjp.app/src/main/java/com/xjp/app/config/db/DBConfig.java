package com.xjp.app.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.xjp.app.common.interceptor.pageinterceptor.Page;
import com.xjp.app.common.interceptor.pageinterceptor.PaginationInterceptor;
import com.xjp.app.common.annotation.MyBatisDao;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: maopanpan
 * @Description: 自定义数据源
 * @Date: 2017/10/12.
 **/
@Configuration
@MapperScan(basePackages = DBConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory", annotationClass = MyBatisDao.class)
@ConfigurationProperties(prefix = "xjpapp.mybatis")
public class DBConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.xjp.app.dao";
    static final String MAPPER_LOCATION = "classpath:mappings/**/*.xml";

    private String url;

    private String userName;

    private String password;

    private String driverClass;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPlugins(new Interceptor[]{new PaginationInterceptor()});
        Class<?>[] typeAliases = {Page.class};
        sessionFactory.setTypeAliases(typeAliases);
        sessionFactory.setMapperLocations(applicationContext.getResources(MAPPER_LOCATION));
        return sessionFactory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }
}
