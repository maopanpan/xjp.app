package com.xjp.dtcache.api;

import com.xjp.dtcache.StatisticParams;
import com.xjp.dtcache.cachemanager.CacheManagerCommon;
import com.xjp.dtcache.exception.CacheException;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/13.
 **/
public class PreMemoryCacheApi {

    private CacheManagerCommon cmc = new CacheManagerCommon();
    private String configPath = "dtcache/dtcache.properties";
    private String cacheLv = "cache.L1.ehcache";

    public PreMemoryCacheApi(){
        try {
            cmc.initCacheProviderLV1(configPath, cacheLv);
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * set(两级缓存  1级：ehcache)
     * @param cacheName cache对象名称
     * @param key 键
     * @param value 值
     * @throws Exception
     * @return void
     */
    public void set(String cacheName, String key, Object value) throws Exception{
        //一级缓存
        cmc.set(StatisticParams.LV1, cacheName, key, value);
    }


    /**
     *
     * get(取数据)
     * @param cacheName cache对象名称
     * @param key
     * @throws Exception
     * @return Object
     */
    public Object get(String cacheName, String key) throws Exception{
        //一级缓存取数据
        Object result = cmc.get(StatisticParams.LV1, cacheName, key);
        return result;
    }
}
