package com.xjp.serial;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author: maopanpan
 * @Description: xjp资源序列化定义
 * @Date: 2017/10/12.
 **/
public interface Serializer {

    /**
     * 序列化组件名称
     *
     * @return
     */
    String getName();

    /**
     * 序列化
     *
     * @param objects
     * @return
     * @throws Exception
     */
    byte[] serialize(Object... objects) throws Exception;

    /**
     * 反序列化
     *
     * @param objects
     * @return
     * @throws Exception
     */
    Object deserialize(Object... objects) throws Exception;


    /**
     * 资源释放
     *
     * @param closeable
     */
    default void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
