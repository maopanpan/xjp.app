package com.xjp.serial.fst;


import com.xjp.serial.Serializer;

/**
 * @Author: maopanpan
 * @Description: FST序列化、FST反序列化封装
 * @Date: 2017/10/12.
 **/
public class FSTSerializableUtil {
    private static Serializer serializer;

    static {
        serializer = new FSTSerializableImpl();
    }

    public static byte[] serialize(Object... objects) throws Exception {
        if (objects == null && objects.length > 0) return null;
        return serializer.serialize(objects);
    }

    public static Object deserialize(Object... objects) throws Exception {
        if (objects == null && objects.length > 0) return null;
        return serializer.deserialize(objects);
    }
}
