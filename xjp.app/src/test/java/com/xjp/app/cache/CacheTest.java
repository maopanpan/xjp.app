package com.xjp.app.cache;

import com.xjp.app.BaseTest;
import com.xjp.dtcache.api.PreMemoryCacheApi;
import com.xjp.dtcache.cache.Cache;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: maopanpan
 * @Description: 本地缓存系统测试
 * @Date: 2017/10/13.
 **/
public class CacheTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(CacheTest.class);

    @Autowired
    PreMemoryCacheApi preMemoryCacheApi;

    @Test
    public void putCache() {
        try {
            preMemoryCacheApi.set("cs11", "name", "张三");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCache() {
        try {
            Object name = preMemoryCacheApi.get("cs11", "name");
            logger.info("测试结果为：{}", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
