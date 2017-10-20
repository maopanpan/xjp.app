package com.xjp.app.common.locks;

/**
 * @Author: maopanpan
 * @Description: Redis分页式锁定义
 * @Date: 2017/10/17.
 **/
public class Lock {
    /**
     * 分页式锁名称
     **/
    private String name;
    /**
     * 分页式锁值
     **/
    private String value;

    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}
