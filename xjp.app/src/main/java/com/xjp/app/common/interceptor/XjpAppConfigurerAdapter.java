package com.xjp.app.common.interceptor;

import com.xjp.app.common.interceptor.exception.ErrorInterceptor;
import com.xjp.app.common.interceptor.xjp.XjpAppInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: maopanpan
 * @Description: 定义系统资源拦截器
 * @Date: 2017/10/12.
 **/
@Configuration
public class XjpAppConfigurerAdapter extends WebMvcConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(XjpAppConfigurerAdapter.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         *  使用说明：
         *  excludePathPatterns 配置忽视拦截路径
         *  addPathPatterns 配置拦截路径
         */

        //对为所有方法进行异常拦截
        registry.addInterceptor(new ErrorInterceptor()).addPathPatterns("/**");
        //拦截用户标识accessToken
        registry.addInterceptor(new XjpAppInterceptor()).excludePathPatterns("/app/user/register").addPathPatterns("/app/**");
        super.addInterceptors(registry);
    }
}
