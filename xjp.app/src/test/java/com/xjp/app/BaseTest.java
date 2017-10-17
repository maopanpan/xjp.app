package com.xjp.app;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: maopanpan
 * @Description:SpringBoot单元测试测试类
 * @Date: 2017/10/17.
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartXjpAppService.class)
public abstract class BaseTest {
}
