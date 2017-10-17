package com.xjp.app.config.redis;

import com.xjp.app.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: maopanpan
 * @Description: Redis封装
 * @Date: 2017/10/13.
 **/
@Configuration
@ConfigurationProperties(prefix = "xjpapp.redis")
public class RedisConfig {

    public Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * Redis地址
     **/
    private String host = "localhost";
    /**
     * Redis密码
     **/
    private String password = "";
    /**
     * Redis端口
     **/
    private String port = "6379";
    /**
     * 数据库0-16
     **/
    private String database = "0";
    /**
     * 请求超时
     **/
    private String timeout = "3000";
    /**
     * 最大空闲连接数
     **/
    private String maxIdle = "8";

    @Bean(name = "redisTemplate")
    public RedisTemplate createRedisTemplate() {
        try {
            logger.info("@@@@@@@@@@开始初始化Redis");
            RedisTemplate redisTemplate = new RedisTemplate(host, password, port, database, timeout, maxIdle);
            logger.info("@@@@@@@@@@Redis初始化失败");
            return redisTemplate;
        } catch (Exception e) {
            LoggerUtil.error(RedisConfig.class, "Redis初始化失败");
            e.printStackTrace();
        }

        return null;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }
}
