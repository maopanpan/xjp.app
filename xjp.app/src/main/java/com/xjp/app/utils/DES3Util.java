package com.xjp.app.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: maopanpan
 * @Description: DES加密解密工具
 * @Date: 2017/10/16.
 **/
public class DES3Util {

    private static Logger logger = LoggerFactory.getLogger(DES3Util.class);

    // 定义加密算法，有DES、DESede(即3DES)、Blowfish
    private static final String Algorithm = "DESede";
    private static final String PASSWORD_CRYPT_KEY = "2017/10/10xianjingdaijiayou!!!";

    /**
     * 加密方法
     *
     * @param data 加密字符串
     * @return
     */
    public static String encryptMode(String data, String PASSWORD_CRYPT_KEY) {
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm); // 生成密钥
            Cipher c1 = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
            c1.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
            byte[] bytes = c1.doFinal(data.getBytes());
            String encryptStr = Base64.encodeBase64String(bytes);
            logger.info("DES3加密字符串为:{}，加密后的字符串为:{}", data, encryptStr);
            return encryptStr;
        } catch (java.security.NoSuchAlgorithmException e) {
            logger.error("字符串：{}加密失败，加密密钥为：{}", data, PASSWORD_CRYPT_KEY, e);
            e.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e) {
            logger.error("字符串：{}加密失败，加密密钥为：{}", data, PASSWORD_CRYPT_KEY, e);
            e.printStackTrace();
        } catch (java.lang.Exception e) {
            logger.error("字符串：{}加密失败，加密密钥为：{}", data, PASSWORD_CRYPT_KEY, e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密函数
     *
     * @param data 解密字符串
     * @return
     */
    public static String decryptMode(String data, String PASSWORD_CRYPT_KEY) {
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
            byte[] bytes = Base64.decodeBase64(data);
            String decryptStr = new String(c1.doFinal(bytes));
            logger.info("DES3解密字符串为：{}，解密后的字符串为：{}", data, decryptStr);
            return decryptStr;
        } catch (java.security.NoSuchAlgorithmException e) {
            logger.error("字符串：{}解密失败，解密密钥为：{}", data, PASSWORD_CRYPT_KEY, e);
            e.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e) {
            logger.error("字符串：{}解密失败，解密密钥为：{}", data, PASSWORD_CRYPT_KEY, e);
            e.printStackTrace();
        } catch (java.lang.Exception e) {
            logger.error("字符串：{}解密失败，解密密钥为：{}", data, PASSWORD_CRYPT_KEY, e);
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 根据字符串生成密钥字节数组
     *
     * @param keyStr 密钥字符串
     *
     * @return
     *
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组

		/*
         * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
		 */
        if (key.length > temp.length) {
            // 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            // 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

    public static void main(String[] args) {
        String msg = "{\"data\":[{\" countyName \":\"宁都\",\" count \":\"30\",\" scale \":\"20\"}],\" code \":\"000000\",\" description \":\"操作成功\"}";
        System.out.println("【加密前】：" + msg);

        // 加密
        String secretArr = DES3Util.encryptMode(msg, PASSWORD_CRYPT_KEY);
        System.out.println("【加密后】：" + secretArr);

        // 解密
        //byte[] myMsgArr = Base64.decodeBase64(secretArr);
        System.out.println("【解密后】：" + DES3Util.decryptMode(secretArr, PASSWORD_CRYPT_KEY));

        System.out.println(PASSWORD_CRYPT_KEY.length());

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Map<String, Object> params = new HashMap<>();
        params.put("opSource", "IOS");
        params.put("imei", uuid);
        params.put("version", "v1.0");
        params.put("mobile", "13264768087");

        params.put("pswd", "!@#$%^&*()123");

        System.out.println(JSON.toJSONString(params));

        String result = DES3Util.encryptMode(JSON.toJSONString(params), PASSWORD_CRYPT_KEY);
        System.out.println("【加密后】：" + result);

    }
}
