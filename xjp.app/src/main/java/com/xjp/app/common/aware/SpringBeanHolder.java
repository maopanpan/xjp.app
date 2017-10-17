package com.xjp.app.common.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Author: maopanpan
 * @Description:资源共享（获取Spring bean实例（通过实现ApplicationContextAware,获取ApplicationContext实例））
 * @Date: 2017/10/13.
 **/
@Service
public class SpringBeanHolder implements ApplicationContextAware {

    //Spring上下文
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanHolder.applicationContext = applicationContext;
    }

    /**
     * 获取指定的Spring bean对象
     *
     * @param name
     * @return
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

}
