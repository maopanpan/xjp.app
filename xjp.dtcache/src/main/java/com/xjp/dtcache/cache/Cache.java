package com.xjp.dtcache.cache;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/13.
 **/
public interface Cache {

    /**
     *
     * get(取value)
     * @param key
     * @throws Exception
     * @return Object
     */
    Object get(String key) throws Exception;

    /**
     *
     * set(设置键值)
     * @param key
     * @param value
     * @return void
     * @throws Exception
     */
    void set(String key, Object value) throws Exception;

    /**
     *
     * set(设置定时键值)
     * @param key
     * @param value
     * @param liveSeconds kv存活时间
     * @throws Exception
     * @return void
     */
    void set(String key, Object value, int liveSeconds) throws Exception;
}