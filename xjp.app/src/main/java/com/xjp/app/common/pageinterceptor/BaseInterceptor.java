package com.xjp.app.common.pageinterceptor;

import com.xjp.app.common.aware.SpringBeanHolder;
import com.xjp.app.common.dialect.Dialect;
import com.xjp.app.common.dialect.db.*;
import com.xjp.app.config.GlobalConfig;
import com.xjp.app.utils.ReflectionUtil;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.Properties;

/**
 * @Author: maopanpan
 * @Description: 分页拦截器
 * @Date: 2017/10/12.
 **/
@ConfigurationProperties(prefix = "myself")
public abstract class BaseInterceptor implements Interceptor, Serializable {

    private static final long serialVersionUID = 1L;

    protected static final String PAGE = "page";

    protected static final String DELEGATE = "delegate";

    protected static final String MAPPED_STATEMENT = "mappedStatement";

    protected Log log = LogFactory.getLog(this.getClass());

    protected Dialect DIALECT;

    /**
     * 对参数进行转换和检查
     * @param parameterObject
     * @param page
     * @return
     */
    protected static Page<Object> convertParameter(Object parameterObject, Page<Object> page) {
        try {
            if (parameterObject instanceof Page) {
                return (Page<Object>) parameterObject;
            } else {
                return (Page<Object>) ReflectionUtil.getFieldValue(parameterObject, PAGE);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 设置属性，支持自定义方言类和制定数据库的方式
     * @param p
     */
    protected void initProperties(Properties p) {
        Dialect dialect = null;
        GlobalConfig globalConfig = (GlobalConfig) SpringBeanHolder.getBean("globalConfig");
        String dbType = globalConfig.getDbType();
        if ("db2".equals(dbType)) {
            dialect = new DB2Dialect();
        } else if ("mysql".equals(dbType)) {
            dialect = new MySQLDialect();
        } else if ("oracle".equals(dbType)) {
            dialect = new OracleDialect();
        }
        if (dialect == null) {
            throw new RuntimeException("mybatis dialect error.");
        }
        DIALECT = dialect;
    }
}
