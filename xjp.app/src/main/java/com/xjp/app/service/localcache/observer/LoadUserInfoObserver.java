package com.xjp.app.service.localcache.observer;

import com.xjp.app.dao.example.UserDao;
import com.xjp.app.model.example.SysUser;
import com.xjp.app.service.localcache.CacheObserver;
import com.xjp.app.utils.LoggerUtil;
import com.xjp.dtcache.api.PreMemoryCacheApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: maopanpan
 * @Description: 加载用户信息本地缓存
 * @Date: 2017/10/14.
 **/
@Service("loadUserInfoObserver")
public class LoadUserInfoObserver implements CacheObserver {

    private Logger logger = LoggerFactory.getLogger(LoadUserInfoObserver.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private PreMemoryCacheApi preMemoryCacheApi;

    /**
     * 定义缓存KEY
     */
    public final static String CACHEKEY = "apierrorcode";

    @Override
    public void loadUpdate() {
        logger.info("##############开始加载用户信息");
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);

        SysUser sysUser = userDao.findById(params);

        try {
            preMemoryCacheApi.set(CacheObserver.PREMEMORYCACHENAME, CACHEKEY, sysUser);
            logger.info("#############加载用户信息成功");
        } catch (Exception e) {
            LoggerUtil.error(LoadUserInfoObserver.class, "加载用户信息失败");
            e.printStackTrace();
        }
    }
}
