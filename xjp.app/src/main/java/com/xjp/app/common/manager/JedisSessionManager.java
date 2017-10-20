package com.xjp.app.common.manager;

import com.xjp.app.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @Author: maopanpan
 * @Description: 构建SESSION管理器
 * @Date: 2017/10/20.
 **/
@Component
public class JedisSessionManager {

    private Logger logger = LoggerFactory.getLogger(JedisSessionManager.class);


    private static final String REDIS_XJP_SESSION = Constants.REDISSESSION;


    @Autowired
    JedisManager jedisManager;

    private int dbIndex = Constants._0;

    private int liveSeconds = Constants.LIVESECONDS;

    /**
     * 创建SESSION
     *
     * @param accessToken
     * @param object
     * @throws Exception
     */
    public void saveSession(String accessToken, Object object) throws Exception {
        if (!StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(object)) {
            jedisManager.set(dbIndex, buildRedisSessionKey(accessToken), object, liveSeconds);
        }
    }

    /**
     * 获取SESSION
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    public Object getSession(String accessToken) throws Exception {
        if (!StringUtils.isEmpty(accessToken)) {
            return jedisManager.get(dbIndex, buildRedisSessionKey(accessToken));
        }
        return null;
    }

    /**
     * 删除SESSION
     *
     * @param accessToken
     */
    public void delSession(String accessToken) {
        if (!StringUtils.isEmpty(accessToken)) {
            jedisManager.remove(dbIndex, buildRedisSessionKey(accessToken));
        }
    }

    /**
     * 设置key生命周期
     * @param accessToken
     */
    public void expire(String accessToken) {
        if(!StringUtils.isEmpty(accessToken)) {
            jedisManager.expire(dbIndex, buildRedisSessionKey(accessToken), liveSeconds);
        }
    }


    /**
     * 构建SESSION ID
     *
     * @param accessToken
     * @return
     */
    private String buildRedisSessionKey(Serializable accessToken) {
        return REDIS_XJP_SESSION + accessToken;
    }
}
