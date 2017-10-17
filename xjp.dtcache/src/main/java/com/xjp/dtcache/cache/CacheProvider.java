package com.xjp.dtcache.cache;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/13.
 **/
public interface CacheProvider {

    /**
     *
     * getCache(获取cache对象)
     * @param cacheName cache名称
     * @return void
     */
    Cache getCache(String cacheName) throws Exception;
}
