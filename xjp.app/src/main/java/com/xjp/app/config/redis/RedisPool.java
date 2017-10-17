package com.xjp.app.config.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * @Author: maopanpan
 * @Description: 构建redis连接池
 * @Date: 2017/10/13.
 **/
public class RedisPool {
    // redis 连接池
    private static JedisPool pool;

    public RedisPool() {

    }

    public RedisPool(String host, String password, String port, String database, String timeout, String maxIdle) {
        redisPool(host, password, port, database, timeout, maxIdle);
    }

    /**
     * 初始化redis连接池
     */
    public void redisPool(String host, String password, String port, String database, String timeout, String maxIdle) {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        // 最大连接数, 默认8个
        config.setMaxTotal(20);
        // 最大空闲连接数, 默认8个
        config.setMaxIdle(Integer.valueOf(maxIdle));

        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,
        // 默认-1
        config.setMaxWaitMillis(10000);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟), 设置成3分钟
        config.setMinEvictableIdleTimeMillis(180000);

        // 在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);
        // 在空闲时检查有效性, 默认false
        config.setTestWhileIdle(true);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1，设置2分钟
        config.setTimeBetweenEvictionRunsMillis(10000);

        if (StringUtils.isEmpty(password)) {
            pool = new JedisPool(config, host, Integer.valueOf(port), Integer.valueOf(timeout));
        } else {
            pool = new JedisPool(config, host, Integer.valueOf(port), Integer.valueOf(timeout), password, Integer.valueOf(database));
        }
    }

    private static String getPropertyString(Properties props, String key) {
        return props.getProperty(key).trim();
    }

    private static int getPropertyInt(Properties props, String key) {
        return Integer.parseInt(props.getProperty(key).trim());
    }

    public static JedisPool getPool() {
        return pool;
    }

}
