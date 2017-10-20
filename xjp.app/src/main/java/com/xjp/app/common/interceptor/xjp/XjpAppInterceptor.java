package com.xjp.app.common.interceptor.xjp;

import com.alibaba.fastjson.JSON;
import com.xjp.app.common.Constants;
import com.xjp.app.common.aware.SpringBeanHolder;
import com.xjp.app.common.manager.JedisManager;
import com.xjp.app.common.manager.JedisSessionManager;
import com.xjp.app.config.GlobalConfig;
import com.xjp.app.utils.PrintUtil;
import com.xjp.app.vo.app.ResponseObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: maopanpan
 * @Description: 拦截accessToken
 * @Date: 2017/10/12.
 **/
public class XjpAppInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(XjpAppInterceptor.class);

    /**
     * SESSION管理器
     */
    final static JedisSessionManager jedisSessionManager = (JedisSessionManager) SpringBeanHolder.getBean("jedisSessionManager");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getParameter("accessToken");
        PrintWriter pw = null;
        if (!StringUtils.isEmpty(accessToken)) {
            Object object = jedisSessionManager.getSession(accessToken);
            if (object != null) {
                //刷新SESSION
                jedisSessionManager.expire(accessToken);
                return true;
            } else {
                ResponseObject responseObject = new ResponseObject(Constants.CODE_01, Constants.DESC_01, "");
                PrintUtil.print(response, JSON.toJSONString(responseObject));
                return false;
            }
        }

        ResponseObject responseObject = new ResponseObject(Constants.CODE_07, Constants.DESC_07, "");
        PrintUtil.print(response, JSON.toJSONString(responseObject));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
