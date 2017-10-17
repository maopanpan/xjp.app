package com.xjp.serial.fst;

import com.xjp.serial.Serializer;
import org.junit.Test;

/**
 * @Author: maopanpan
 * @Description: FST序列化、反序列化测试
 * @Date: 2017/10/13.
 **/
public class FstTest {
    Serializer ms = new FSTSerializableImpl();
    byte[] bytes = null;
    @Test
    public void serializeTest() {
        try {
            bytes = ms.serialize("中华人民共和国");
            System.out.println(String.valueOf(bytes));
            String result = (String) ms.deserialize(bytes);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
