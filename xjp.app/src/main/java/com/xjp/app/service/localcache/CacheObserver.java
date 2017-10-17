package com.xjp.app.service.localcache;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/14.
 **/
public interface CacheObserver {

    //定义缓存对象名称
    String PREMEMORYCACHENAME = "prememory";

    /**
     * 定义缓存对象行为
     */
    void loadUpdate();
}