package com.xjp.app.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/18.
 **/
public class PrintUtil {
    public static void print(HttpServletResponse response, String data) {
        if (!StringUtils.isEmpty(data)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter pw = null;
            try {
                pw = response.getWriter();
                pw.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        }
    }
}
