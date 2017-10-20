package com.xjp.app.config.jedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: maopanpan
 * @Description: RedisPool
 * @Date: 2017/10/20.
 **/
@Configuration
//@ConfigurationProperties(prefix = "xjpapp.redis.")
public class JedisConfig {
    /**
     * Redis地址
     **/
    @Value("${xjpapp.redis.host}")
    private String host;
    /**
     * Redis端口
     **/
    @Value("${xjpapp.redis.port}")
    private int port;
    /**
     * 设置请求超时
     **/
    @Value("${xjpapp.redis.timeout}")
    private int timeout;
    /**
     * Redis密码
     **/
    @Value("${xjpapp.redis.password}")
    private String password;

    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        // 最大连接数, 默认8个
        config.setMaxTotal(20);
        // 最大空闲连接数, 默认8个
        config.setMaxIdle(8);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,
        config.setMaxWaitMillis(10000);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟), 设置成3分钟
        config.setMinEvictableIdleTimeMillis(180000);
        // 在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);
        // 在空闲时检查有效性, 默认false
        config.setTestWhileIdle(true);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1，设置2分钟
        config.setTimeBetweenEvictionRunsMillis(10000);

        JedisPool pool = null;
        if (!StringUtils.isEmpty(password)) {
            pool = new JedisPool(config, host, port, timeout, password);
        } else {
            pool = new JedisPool(config, host, port, timeout);
        }

        return pool;
    }

}
