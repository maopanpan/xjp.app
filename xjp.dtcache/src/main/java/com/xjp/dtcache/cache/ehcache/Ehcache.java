package com.xjp.dtcache.cache.ehcache;

import com.xjp.dtcache.cache.Cache;
import com.xjp.dtcache.exception.CacheException;
import net.sf.ehcache.Element;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/13.
 **/
public class Ehcache  implements Cache {
    private net.sf.ehcache.Cache ecache;

    public Ehcache(net.sf.ehcache.Cache ecache) throws CacheException {
        this.ecache = ecache;
        if(ecache == null)
            throw new CacheException("Ehcache Cache object is null");
    }

    /**
     *
     * @see Cache#get(String)
     */
    public Object get(String key) throws Exception {
        Element element = ecache.get(key);
        Object value = null;
        if(element != null){
            value = element.getObjectValue();
        }
        return value;
    }

    /**
     *
     * @see Cache#set(String, Object)
     */
    public void set(String key, Object value) throws Exception {
        Element element = new Element(key, value);
        ecache.put(element);
    }

    /**
     *
     * @see Cache#set(String, Object, int)
     */
    public void set(String key, Object value, int liveSeconds) throws Exception {
        Element element = new Element(key, value, 0, liveSeconds);
        ecache.put(element);
    }
}
