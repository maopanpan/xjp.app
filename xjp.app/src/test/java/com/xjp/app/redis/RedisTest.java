package com.xjp.app.redis;

import com.xjp.app.BaseTest;

import com.xjp.app.common.Constants;
import com.xjp.app.common.locks.Lock;
import com.xjp.app.common.locks.RedisLock;
import com.xjp.app.common.manager.JedisManager;
import com.xjp.app.common.manager.JedisSessionManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: maopanpan
 * @Description: REDIS分布式锁测试
 * @Date: 2017/10/17.
 **/
public class RedisTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private JedisManager jedisManager;
    @Autowired
    private JedisSessionManager jedisSessionManager;
    @Autowired
    private RedisLock redisLock;

    @Test
    public void test() {
        try {
            jedisSessionManager.saveSession("15011479990", "abcdefafsdfsf");
            jedisSessionManager.expire("15011479990");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void incr() {
        Lock lock = new Lock("localLock", "localLock");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (redisLock.tryLock(lock)) {
                        jedisManager.incr(Constants._15,"id");
                        redisLock.releaseLock(lock);
                    }
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (redisLock.tryLock(lock)) {
                        jedisManager.incr(Constants._15,"id");
                        redisLock.releaseLock(lock);
                    }
                }
            }
        });

        thread.start();
        thread1.start();
        try {
            Thread.sleep(10000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
