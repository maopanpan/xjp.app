package com.xjp.dtcache.cache.ehcache;

import com.xjp.dtcache.cache.Cache;
import com.xjp.dtcache.cache.CacheProvider;
import com.xjp.dtcache.exception.CacheException;
import net.sf.ehcache.CacheManager;

import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/13.
 **/
public class EhcacheProvider implements CacheProvider {

    // ehcache管理器
    private static CacheManager manager;

    // cache对象集合
    private ConcurrentHashMap<String, Cache> cacheManagerMap = new ConcurrentHashMap<String, Cache>();

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @throws Exception
     */
    public EhcacheProvider() throws Exception {
        initCache();
    }

    /**
     * 初始化echcache manager 重实例，只需初始化一次
     *
     * @throws Exception
     */
    public void initCache() throws Exception {
        InputStream configurationInputStream = EhcacheProvider.class.getClassLoader().getResourceAsStream("dtcache/ehcache.xml");
        if (configurationInputStream == null)
            throw new CacheException("Ehcache configfile not exist");
        if (manager == null) {
            if (manager == null) {
                manager = new CacheManager(configurationInputStream);
            }
        }
    }

    /**
     * @throws CacheException
     * @see
     */
    public Cache getCache(String cacheName) throws CacheException {
        if (manager == null)
            throw new CacheException("Ehcache CacheManager is null");
        Ehcache cache = (Ehcache) cacheManagerMap.get(cacheName);
        if (cache == null) {
            synchronized (cacheManagerMap) {
                if (cache == null) {
                    net.sf.ehcache.Cache ecache = manager.getCache(cacheName);
                    if (ecache == null) {
                        manager.addCache(cacheName);
                    }
                    cache = new Ehcache(manager.getCache(cacheName));
                    cacheManagerMap.put(cacheName, cache);
                }
            }
        }

        return cache;
    }
}
