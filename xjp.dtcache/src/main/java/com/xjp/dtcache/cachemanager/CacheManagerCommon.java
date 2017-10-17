package com.xjp.dtcache.cachemanager;

import com.xjp.dtcache.cache.Cache;
import com.xjp.dtcache.cache.CacheProvider;
import com.xjp.dtcache.cache.ehcache.EhcacheProvider;
import com.xjp.dtcache.exception.CacheException;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/13.
 **/
public class CacheManagerCommon extends BuildCache {

    // 一级和二级缓存提供器
    public CacheProvider lv1Provider = null;
    public CacheProvider lv2Provider = null;

    /**
     * initCacheProvider(初始化缓存提供器--两级)
     *
     * @param configPath 配置文件路径
     * @return void
     * @throws CacheException
     */
    public void initCacheProviderLV12(String configPath, String cacheLv1, String cacheLv2) throws CacheException {
        try {
            InputStream in = CacheManagerCommon.class.getClassLoader().getResourceAsStream(configPath);
            Properties pro = new Properties();
            pro.load(in);
            in.close();
            lv1Provider = getProviderByProperties(pro, cacheLv1);
            lv2Provider = getProviderByProperties(pro, cacheLv2);
            if (lv1Provider == null || lv2Provider == null) {
                throw new CacheException("初始化Provider失败");
            }
        } catch (Exception e) {
            throw new CacheException("初始化提供器异常, 错误信息:", e);
        }
    }

    /**
     * @param configPath 配置文件路径
     * @return void
     * @throws CacheException
     * @throws
     * @Description: 初始化缓存提供器--一级
     */
    public void initCacheProviderLV1(String configPath, String cacheLv) throws CacheException {
        try {
            InputStream in = CacheManagerCommon.class.getClassLoader().getResourceAsStream(configPath);
            Properties pro = new Properties();
            pro.load(in);
            in.close();
            lv1Provider = getProviderByProperties(pro, cacheLv);
            if (lv1Provider == null) {
                throw new CacheException("初始化Provider失败");
            }
        } catch (Exception e) {
            throw new CacheException("初始化提供器异常, 错误信息:", e);
        }
    }

    /**
     * getProviderByProperties(通过配置文件创建缓存提供器)
     *
     * @param pro
     * @param key 配置文件缓存提供者key
     * @return CacheProvider
     * @throws Exception
     */
    public CacheProvider getProviderByProperties(Properties pro, String key) throws Exception {
        CacheProvider cp = null;
        String value = pro.getProperty(key);
        if (StringUtils.isEmpty(value)) {
            throw new CacheException(MessageFormat.format("dtcache.properties{1}没有指定缓存类型", key));
        } else if ("ehcache".equals(value)) {
            cp = new EhcacheProvider();
        }
        return cp;
    }

    /**
     * set(根据 缓存等级设置缓存)
     *
     * @param cacheLV   缓存等级
     * @param cacheName 缓存名称
     * @param key       键
     * @param value     值
     * @return void
     * @throws Exception
     */
    public void set(int cacheLV, String cacheName, String key, Object value) throws Exception {
        Cache cache = buildCacheSet(cacheLV, cacheName, lv1Provider, lv2Provider);
        if (cache == null)
            throw new CacheException("Cache object is null");
        cache.set(key, value);
    }

    /**
     * set(根据 缓存等级设置缓存)
     *
     * @param cacheLV     缓存等级
     * @param cacheName   缓存名称
     * @param key         键
     * @param value       值
     * @param liveSeconds kv 存活时间
     * @return void
     * @throws Exception
     */
    public void set(int cacheLV, String cacheName, String key, Object value, int liveSeconds) throws Exception {
        Cache cache = buildCacheSet(cacheLV, cacheName, lv1Provider, lv2Provider);
        if (cache == null)
            throw new CacheException("Cache object is null");
        cache.set(key, value, liveSeconds);
    }

    /**
     * get(通过 可以key 得到value)
     *
     * @param cacheLV   缓存等级
     * @param cacheName 缓存名称
     * @param key       键
     * @return Object
     * @throws Exception
     */
    public Object get(int cacheLV, String cacheName, String key) throws Exception {
        Cache cache = buildCacheGet(cacheLV, cacheName, lv1Provider, lv2Provider);
        if (cache == null)
            throw new CacheException("Cache object is null");
        return cache.get(key);
    }

}
