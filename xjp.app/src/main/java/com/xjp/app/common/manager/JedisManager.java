package com.xjp.app.common.manager;

import com.xjp.serial.fst.FSTSerializableUtil;
import org.junit.rules.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;
import java.util.Timer;

/**
 * @Author: maopanpan
 * @Description: Redis管理器
 * @Date: 2017/10/20.
 **/
@Component
public class JedisManager {

    @Autowired
    JedisPool j;

    /**
     * 获取反序列化对象
     *
     * @param dbIndex
     * @param key
     * @return
     * @throws Exception
     */
    public Object get(int dbIndex, String key) throws Exception {
        if (key == null)
            throw new NullPointerException("key is null");
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        byte[] bytes = redis.get(key.getBytes());
        Object object = null;
        if (!StringUtils.isEmpty(bytes))
            object = FSTSerializableUtil.deserialize(bytes);
        redis.close();
        return object;
    }

    /**
     * 插入序列化对象
     *
     * @param dbIndex
     * @param key
     * @param value
     * @throws Exception
     */
    public void set(int dbIndex, String key, Object value) throws Exception {
        if (key == null)
            throw new NullPointerException("key is null");
        String res = null;
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        res = redis.set(key.getBytes(), FSTSerializableUtil.serialize(value));
        redis.close();
    }

    /**
     * 获取对象
     *
     * @param dbIndex
     * @param key
     * @return
     */
    public String getStr(int dbIndex, String key) {
        if (key == null)
            throw new NullPointerException("key is null");
        String res = null;
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        res = redis.get(key);
        redis.close();
        return res;
    }

    /**
     * 插入对象
     *
     * @param dbIndex
     * @param key
     * @param value
     */
    public void set(int dbIndex, String key, String value) {
        if (key == null)
            throw new NullPointerException("key is null");

        String res = null;
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        res = redis.set(key, value);
        redis.close();
    }

    /**
     * 插入对象,设置存活期
     *
     * @param dbIndex
     * @param key
     * @param value
     * @param liveSeconds
     * @throws Exception
     */
    public void set(int dbIndex, String key, Object value, int liveSeconds) throws Exception {
        if (key == null)
            throw new NullPointerException("key is null");

        String res = null;
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        if (liveSeconds > 0) {
            res = redis.setex(key.getBytes(), liveSeconds, FSTSerializableUtil.serialize(value));
        }

        redis.close();
    }

    /**
     * redis自增
     *
     * @param dbIndex
     * @param key
     * @return
     */
    public long incr(int dbIndex, String key) {
        if (key == null)
            throw new NullPointerException("key is null");
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        long res = redis.incr(key);
        redis.close();
        return res;
    }

    /**
     * redis自减
     *
     * @param dbIndex
     * @param key
     * @return
     */
    public long decr(int dbIndex, String key) {
        if (key == null)
            throw new NullPointerException("key is null");
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        long res = redis.decr(key);
        redis.close();
        return res;
    }

    /**
     * 判断key是否存在
     *
     * @param dbIndex
     * @param key
     * @return
     */
    public boolean hasKey(int dbIndex, String key) {
        if (key == null)
            throw new NullPointerException("key is null");
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        boolean result = redis.exists(key);
        redis.close();
        return result;
    }

    /**
     * 删除key
     *
     * @param dbIndex
     * @param key
     */
    public void remove(int dbIndex, String key) {
        if (key == null)
            throw new NullPointerException("key is null");
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        redis.del(key);
        redis.close();
    }

    /**
     * 设置key的生命周期
     *
     * @param dbIndex
     * @param key
     * @param liveSeconds
     */
    public void expire(int dbIndex, String key, int liveSeconds) {
        if (key == null)
            throw new NullPointerException("key is null");
        Jedis redis = j.getResource();
        redis.select(dbIndex);
        redis.expire(key.getBytes(), liveSeconds);
        redis.close();
    }


}
