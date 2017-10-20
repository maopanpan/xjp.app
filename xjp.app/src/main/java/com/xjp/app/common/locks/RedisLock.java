package com.xjp.app.common.locks;

import com.xjp.app.common.Constants;
import com.xjp.app.common.manager.JedisManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: maopanpan
 * @Description: Redis分页式锁
 * @Date: 2017/10/17.
 **/
@Component
public class RedisLock {

    private Logger logger = LoggerFactory.getLogger(RedisLock.class);
    /**
     * 单个业务持有锁的时间30s，防止死锁
     **/
    private final static int LOCK_EXPIRE = 30;
    /**
     * 默认30ms尝试一次
     **/
    private final static int LOCK_TRY_INTERVAL = 30;
    /**
     * 默认尝试20s
     **/
    private final static int LOCK_TRY_TIMEOUT = 20 * 1000;

    private int dbIndex = Constants._1;

    @Autowired
    JedisManager jedisManager;


    /**
     * 尝试获取全局锁
     *
     * @param lock 锁的名称
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock    锁的名称
     * @param timeout 获取超时时间 单位ms
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock        锁的名称
     * @param timeout     获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取锁的超时时间
     * @param tryInterval    多少毫秒尝试获取一次
     * @param lockExpireTime 锁的过期
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval, int lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }


    /**
     * 操作redis获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取的超时时间
     * @param tryInterval    多少ms尝试一次
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return true 获取成功，false获取失败
     */
    public boolean getLock(Lock lock, long timeout, long tryInterval, int lockExpireTime) {
        try {
            if (StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue()))
                return false;

            long startTime = System.currentTimeMillis();
            do {
                if (!jedisManager.hasKey(dbIndex, lock.getName())) {
                    jedisManager.set(dbIndex, lock.getName(), lock.getValue(), lockExpireTime);
                    //logger.info("redis获得锁");
                    return true;
                } else {//存在锁
                    logger.debug("Lock is exist!!!");
                }

                long timeDiff = System.currentTimeMillis() - startTime;
                if (timeDiff > timeout) {//尝试超过了设定值之后直接跳出循环
                    return false;
                }
                Thread.sleep(tryInterval);
            }
            while (jedisManager.hasKey(dbIndex, lock.getName()));
        } catch (Exception e) {
            logger.error("分布式锁异常！", e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 释放锁
     */
    public void releaseLock(Lock lock) {
        if (!StringUtils.isEmpty(lock.getName()))
            jedisManager.remove(dbIndex, lock.getName());

    }

}
