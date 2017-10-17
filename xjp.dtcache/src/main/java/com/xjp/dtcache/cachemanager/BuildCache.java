package com.xjp.dtcache.cachemanager;

import com.xjp.dtcache.cache.Cache;
import com.xjp.dtcache.cache.CacheProvider;
import com.xjp.dtcache.exception.CacheException;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/13.
 **/
public class BuildCache {

    /**
     *
     * buildCacheSet(set方法获取cache对象)
     * @param cacheLV cache等级
     * @param cacheName cache名称
     * @param providers cache提供者
     * @throws Exception
     * @return Cache
     */
    public Cache buildCacheSet(int cacheLV, String cacheName, CacheProvider... providers) throws Exception {
        Cache cache = null;
        if(providers == null || providers.length == 0)
            throw new CacheException("缓存提供者个数为NULL或者个数不为2");
        if (cacheLV == 1 && providers[1 - 1] != null) {
            cache = providers[1 - 1].getCache(cacheName);
        } else if (cacheLV == 2 && providers[2 - 1] != null) {
            cache = providers[2 - 1].getCache(cacheName);
        }
        return cache;
    }

    /**
     *
     * buildCacheGet(get方法获取cache对象)
     * @param cacheLV cache等级
     * @param cacheName cache名称
     * @param providers cache提供者
     * @throws Exception
     * @return Cache
     */
    public Cache buildCacheGet(int cacheLV, String cacheName, CacheProvider... providers) throws Exception {
        Cache cache = null;
        if (cacheLV == 1) {
            cache = providers[1 - 1].getCache(cacheName);
        } else if (cacheLV == 2) {
            cache = providers[2 - 1].getCache(cacheName);
        }
        return cache;
    }
}
