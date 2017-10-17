package com.xjp.app.common.listener;

import com.xjp.app.service.localcache.CacheObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: maopanpan
 * @Description: Spring容器启动后，初始化本地缓存
 * @Date: 2017/10/12.
 **/
@Component
public class XjpAppListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(XjpAppListener.class);

    @Autowired
    @Qualifier("loadUserInfoObserver")
    CacheObserver loadUserInfoObserver;


    /**
     * Spring容器启动后，初始化本地缓存
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("开始加载本地缓存");
        if (event.getApplicationContext().getParent() == null) {
            loadUserInfoObserver.loadUpdate();
        }
    }
}
