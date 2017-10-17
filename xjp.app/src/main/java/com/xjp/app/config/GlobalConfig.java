package com.xjp.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: maopanpan
 * @Description: APP配置
 * @Date: 2017/10/16.
 **/
@Component
@ConfigurationProperties("xjpapp.global")
public class GlobalConfig {

    /**
     * 数据库类型（mysql, oracle, DB2）
     **/
    private String dbType;
    /**
     * DES3加密密钥
     **/
    private String cryptKey;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getCryptKey() {
        return cryptKey;
    }

    public void setCryptKey(String cryptKey) {
        this.cryptKey = cryptKey;
    }
}
