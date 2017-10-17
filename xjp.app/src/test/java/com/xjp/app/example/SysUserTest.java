package com.xjp.app.example;

import com.alibaba.fastjson.JSON;
import com.xjp.app.BaseTest;
import com.xjp.app.common.pageinterceptor.Page;
import com.xjp.app.model.example.SysUser;
import com.xjp.app.service.example.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: maopanpan
 * @Description: 单元测试样例
 * @Date: 2017/10/17.
 **/
public class SysUserTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(SysUserTest.class);

    @Autowired
    UserService userService;

    @Test
    public void findUserById() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 2);
        SysUser sysUser = userService.findById(params);
        Assert.assertNotNull(sysUser);
        logger.info("测试结果为：{}", JSON.toJSONString(sysUser));
    }

    @Test(timeout = 1000)
    public void findPage() {
        Page<SysUser> page = userService.findPage(new Page<SysUser>(), new SysUser());
        Assert.assertNotNull(page);
        logger.info("测试结果为：{}", JSON.toJSONString(page));
    }
}
