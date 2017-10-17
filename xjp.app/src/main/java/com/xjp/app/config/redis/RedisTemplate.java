package com.xjp.app.config.redis;

import com.xjp.serial.fst.FSTSerializableUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: maopanpan
 * @Description: redis封装
 * @Date: 2017/10/13.
 **/
@Configuration
public class RedisTemplate {

    //redis连接池
    public JedisPool pool;

    public RedisTemplate() {
    }

    public RedisTemplate(String host, String password, String port, String database, String timeout, String maxIdle) {
        RedisPool redisPool = new RedisPool(host, password, port, database, timeout, maxIdle);
        pool = RedisPool.getPool();
    }

    /***************************************************
     * get操作
     **************************************************/
    /**
     * redis key-value value可以是对象和字符串
     */
    public Object get(String key) throws Exception {
        if (StringUtils.isEmpty(key))
            throw new NullPointerException("redis key is null");
        Jedis redis = pool.getResource();
        byte[] valByte = redis.get(key.getBytes());
        Object value = FSTSerializableUtil.deserialize(valByte);
        redis.close();
        return value;
    }

    public String getStrVal(String key) throws Exception {
        if (StringUtils.isEmpty(key))
            throw new NullPointerException("redis key is null");
        Jedis redis = pool.getResource();
        String value = redis.get(key);
        redis.close();
        return value;
    }

    /**
     * 使用管道 查询所有hash值
     *
     * @param pattern 匹配值
     * @return
     */
    public Set<String> keys(String pattern) {
        Jedis redis = pool.getResource();
        Set<String> allKeys = redis.keys(pattern);
        redis.close();
        return allKeys;
    }

    /**
     * 查询所有hash
     *
     * @param keys
     * @return
     */
    public List<Map<String, Object>> pipelineHGetALL(Set<String> keys) {
        Jedis redis = pool.getResource();
        Pipeline pipeline = redis.pipelined();
        for (String key : keys) {
            pipeline.hgetAll(key);
        }
        List results = pipeline.syncAndReturnAll();
        redis.close();
        return results;
    }

    /**
     * 从hash中获取所有key、value
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetall(String key) {
        Jedis redis = pool.getResource();
        Map<String, String> map = redis.hgetAll(key);
        pool.returnResourceObject(redis);
        return map;
    }

    /**
     * 散列集合 查询一个属性值
     *
     * @param key
     * @param field
     * @return 结果
     */
    public String hget(String key, String field) {
        Jedis redis = pool.getResource();
        String res = redis.hget(key, field);
        redis.close();
        return res;
    }

    /**
     * 批量查询散列集合
     *
     * @param key
     * @param fields
     * @return 结果结合
     */
    public List<String> hmget(String key, String... fields) {
        Jedis redis = pool.getResource();
        List<String> list = redis.hmget(key, fields);
        redis.close();
        return list;
    }

    /***************************************************
     * string set 操作
     * 1、key:string value:string
     * 2、key:string value:object序列化字符串
     * 3、key:string value:map
     **************************************************/
    /**
     * 插入对象
     * redis key-value value可以是对象和字符串
     *
     * @throws Exception java.lang.Object)
     */
    public void set(String key, Object value) throws Exception {
        String res = null;
        if (key == null)
            throw new NullPointerException("key is null");
        Jedis redis = pool.getResource();
        res = redis.set(key.getBytes(), FSTSerializableUtil.serialize(value));
        redis.close();
    }

    public void set(String key, String value) {
        String res = null;
        Jedis redis = pool.getResource();
        res = redis.set(key, value);
        redis.close();
    }

    /**
     * 插入对象,设置存活期
     * key:Stirng value:序列化对象
     * java.lang.Object, int)
     */
    public void set(String key, Object value, int liveSeconds) throws Exception {
        String res = null;
        Jedis redis = pool.getResource();
        if (key == null)
            throw new NullPointerException("key is null");
        if (liveSeconds > 0) {
            res = redis.setex(key.getBytes(), liveSeconds, FSTSerializableUtil.serialize(value));
        } else {
            throw new NullPointerException("liveSeconds must greater 0");
        }
        redis.close();
        System.out.println("redis set结果=" + res);
    }

    /***************************************************
     * 散列集合
     * 查询、更新
     **************************************************/
    /**
     * 插入更新 散列集合
     *
     * @param key
     * @param field 字段
     * @param value 值
     * @return 返回long型结果
     */
    public long hset(String key, String field, String value) {
        Jedis redis = pool.getResource();
        long res = redis.hset(key, field, value);
        redis.close();
        return res;
    }

    /**
     * 批量插入更新 散列集合
     *
     * @param key
     * @param hash
     * @return
     */
    public String hmset(String key, Map<String, String> hash) {
        Jedis redis = pool.getResource();
        String res = redis.hmset(key, hash);
        redis.close();
        return res;
    }

    /***************************************************
     * 计算 操作
     *
     * 散列 集合
     **************************************************/
    /**
     * float 加操作
     *
     * @param key   键
     * @param field 字段
     * @param value 值
     * @return
     * @throws NullPointerException
     */
    public double hincrByFloat(String key, String field, double value) throws NullPointerException {
        if (pool == null || StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
            throw new NullPointerException("redis pool or key or field is null");
        }
        Jedis redis = pool.getResource();
        double res = redis.hincrByFloat(key, field, value);
        redis.close();
        return res;
    }

    /**
     * 指定key 加指定值
     * <p>
     * 适用范围：整形
     *
     * @param key
     * @param field
     * @param addValue 需要增加的值
     * @return
     */
    public long hincr(String key, String field, long addValue) {
        Jedis redis = pool.getResource();
        long res = redis.hincrBy(key, field, addValue);
        redis.close();
        return res;
    }

    /***************************************************
     * 管道操作
     **************************************************/

    /**
     * 使用管道批量插入hash集合
     *
     * @param values
     */
    public void pipelineHash(Map<String, Map<String, String>> values) {
        Jedis redis = pool.getResource();
        Pipeline pipeline = redis.pipelined();
        for (Map.Entry entry : values.entrySet()) {
            String key = String.valueOf(entry.getKey());
            Map<String, String> value = (Map<String, String>) entry.getValue();
            pipeline.hmset(key, value);
        }
        pipeline.sync();
        redis.close();
    }

    /**
     * 是否存在指定key
     *
     * @param key 键
     * @return
     */
    public boolean IsExistKey(String key) {
        Jedis redis = pool.getResource();
        boolean isExits = redis.exists(key);
        redis.close();
        return isExits;
    }

    /**
     * 删除指定键值
     *
     * @param key 键
     * @return
     */
    public boolean delKey(String key) {
        Jedis redis = pool.getResource();
        long num = redis.del(key);
        if (num == 1) {
            return true;
        }
        redis.close();
        return false;
    }

    /**
     * 向队列左侧插入数据
     *
     * @param key
     * @param value
     * @return
     */
    public long lpush(String key, String value) {
        Jedis redis = pool.getResource();
        long len = redis.lpush(key, value);
        redis.close();
        return len;
    }

    /**
     * 向队列左边插入数据
     *
     * @param key
     * @param values
     * @return
     */
    public long lpush(String key, String... values) {
        Jedis redis = pool.getResource();
        long len = redis.lpush(key, values);
        redis.close();
        return len;
    }

    /**
     * 从队列左边插入数据
     * <p>
     * 一次插入对个值
     *
     * @param key
     * @param valueList
     * @return
     */
    public long lpush(String key, List<String> valueList) {
        Jedis redis = pool.getResource();
        String[] values = valueList.toArray(new String[valueList.size()]);
        long len = redis.lpush(key, values);
        redis.close();
        return len;
    }

    /**
     * 从队列左侧取出一个数据
     *
     * @param key 队列名称
     * @return
     */
    public String lpop(String key) {
        Jedis redis = pool.getResource();
        String res = redis.lpop(key);
        redis.close();
        return res;
    }

    /**
     * 从队列右侧边插入数据
     * 每次插入一个值
     *
     * @param key
     * @param value
     * @return
     */
    public long rpush(String key, String value) {
        Jedis redis = pool.getResource();
        long len = redis.rpush(key, value);
        redis.close();
        return len;
    }


    /**
     * 从队列 右侧 插入数据
     *
     * @param key       ➹
     * @param valueList 数据集合
     * @return
     */
    public long rpush(String key, List<String> valueList) {
        Jedis redis = pool.getResource();
        String[] values = valueList.toArray(new String[valueList.size()]);
        long len = redis.rpush(key, values);
        redis.close();
        return len;
    }

    /**
     * 从队列右侧取出一个数据
     *
     * @param key 队列
     * @return
     */
    public String rpop(String key) {
        Jedis redis = pool.getResource();
        String res = redis.rpop(key);
        redis.close();
        return res;
    }

    /**
     * 获取列表片段
     *
     * @param key   ➹
     * @param start 开始索引
     * @param end   结束索引
     * @return
     */
    public List<String> lrange(String key, int start, int end) {
        Jedis redis = pool.getResource();
        List<String> list = redis.lrange(key, start, end);
        redis.close();
        return list;
    }

    /**
     * 从队列 【左侧】 取出 【rPopCount】 个数据，并将取出的数据删掉
     * 注：此方法必须和【rpush】配合使用，并且此方法可能存在数据少量丢失问题
     *
     * @param key       ➹
     * @param lPopCount 左侧取出的数据量
     * @return
     */
    public List<String> lRangeAndTrim(String key, int lPopCount) {
        Jedis redis = pool.getResource();
        //队列长度
        long len = redis.llen(key);

        long endIndex = lPopCount - 1;
        List<String> list = redis.lrange(key, 0, endIndex);
        redis.ltrim(key, lPopCount, redis.llen(key) - 1);
        redis.close();
        return list;

    }


    /**
     * 得到队列的长度
     *
     * @param key 键
     * @return
     */
    public long getListLen(String key) {
        Jedis redis = pool.getResource();
        long len = redis.llen(key);
        redis.close();
        return len;
    }

    /**
     * 向集合中添加n个元素
     *
     * @param key
     * @param members
     * @return
     */
    public long sadd(String key, String... members) {
        Jedis redis = pool.getResource();
        long count = redis.sadd(key, members);
        redis.close();
        return count;
    }

    /**
     * 对多个集合做交集操作
     *
     * @param keys 集合名称
     * @return
     */
    public Set<String> sinter(String... keys) {
        Jedis redis = pool.getResource();
        Set<String> res = redis.sinter(keys);
        redis.close();
        return res;
    }

    /**
     * redis自增
     *
     * @param key
     * @return
     */
    public long incr(String key) {
        Jedis redis = pool.getResource();
        long res = redis.incr(key);
        redis.close();
        return res;
    }

    /**
     * redis自减
     *
     * @param key
     * @return
     */
    public long decr(String key) {
        Jedis redis = pool.getResource();
        long res = redis.decr(key);
        redis.close();
        return res;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        Jedis redis = pool.getResource();
        boolean result = redis.exists(key);
        redis.close();
        return result;
    }

    /**
     * 删除key
     * @param key
     */
    public void deleteKey(String key) {
        Jedis redis = pool.getResource();
        redis.del(key);
        redis.close();
    }

    public void flushdb() {
        Jedis redis = pool.getResource();
        redis.flushDB();
        redis.close();
    }


    /**
     * 关闭remplate
     */
    public void closeTemplate() {
        pool.close();
    }

}
