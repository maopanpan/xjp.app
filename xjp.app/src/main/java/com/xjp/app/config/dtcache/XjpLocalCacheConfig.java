package com.xjp.app.config.dtcache;

import com.xjp.app.utils.LoggerUtil;
import com.xjp.dtcache.api.PreMemoryCacheApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: maopanpan
 * @Description: 初始化本地缓存系统
 * @Date: 2017/10/13.
 **/
@Configuration
public class XjpLocalCacheConfig {

    private Logger logger = LoggerFactory.getLogger(XjpLocalCacheConfig.class);

    @Bean("preMemoryCacheApi")
    public PreMemoryCacheApi createPreMemoryCacheApi() {
        try {
            logger.info("@@@@@@@@@开始初始化本地缓存系统");
            PreMemoryCacheApi preMemoryCacheApi = (PreMemoryCacheApi) Class.forName("com.xjp.dtcache.api.PreMemoryCacheApi").newInstance();
            logger.info("@@@@@@@@@本地缓存系统初始化完毕");
            return preMemoryCacheApi;
        } catch (Exception e) {
            LoggerUtil.error(XjpLocalCacheConfig.class, "缓存系统初始化异常！");
            e.printStackTrace();
        }
        return null;
    }
}
