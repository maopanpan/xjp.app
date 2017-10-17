package com.xjp.app.common.interceptor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: maopanpan
 * @Description: 拦截accessToken
 * @Date: 2017/10/12.
 **/
public class XjpAppInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(XjpAppInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getParameter("accessToken");
        if (!StringUtils.isEmpty(accessToken)) {
            return true;
        }

        logger.info("无访问权限！");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            if (StringUtils.isEmpty(accessToken)) {
                pw.write("{\"status\":false}");
            }
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
