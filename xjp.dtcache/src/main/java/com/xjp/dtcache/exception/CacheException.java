package com.xjp.dtcache.exception;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/13.
 **/
public class CacheException extends Exception {

    public CacheException() {
        super();
    }

    public CacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }
}
