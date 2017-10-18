package com.xjp.app.common.interceptor.exception;

import com.alibaba.fastjson.JSON;
import com.xjp.app.common.Constants;
import com.xjp.app.utils.PrintUtil;
import com.xjp.app.vo.app.ResponseObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/18.
 **/
public class ErrorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        int status = response.getStatus();
        ResponseObject responseObject = null;
        if (status == 404) {
            responseObject = new ResponseObject(Constants.CODE_06, Constants.DESC_06, "");
        } else if (status == 500) {
            responseObject = new ResponseObject(Constants.CODE_08, Constants.DESC_08, "");
        }

        PrintUtil.print(response, JSON.toJSONString(responseObject));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
