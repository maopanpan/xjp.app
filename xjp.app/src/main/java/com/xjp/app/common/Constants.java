package com.xjp.app.common;

/**
 * @Author: maopanpan
 * @Description: 全局变量
 * @Date: 2017/10/18.
 **/
public class Constants {
    /**
     * REDIS数据库划分
     */

    //存储SESSION
    public static final int _0 = 0;
    //存储分布式锁
    public static final int _1 = 1;
    //未分配
    public static final int _2 = 2;
    //未分配
    public static final int _3 = 3;
    //未分配
    public static final int _4 = 4;
    //未分配
    public static final int _5 = 5;
    //未分配
    public static final int _6 = 6;
    //未分配
    public static final int _7 = 7;
    //未分配
    public static final int _8 = 8;
    //未分配
    public static final int _9 = 9;
    //未分配
    public static final int _10 = 10;
    //未分配
    public static final int _11 = 11;
    //未分配
    public static final int _12 = 12;
    //未分配
    public static final int _13 = 13;
    //未分配
    public static final int _14 = 14;
    //测试
    public static final int _15 = 15;

    /**
     * 定义用户标识
     */
    public static final String REDISSESSION = "xjp-app-session:";

    /**
     * SESSION生命周期30天
     */
    public static final int LIVESECONDS = 10 * 60;

    /**
     * 系统变量
     */
    public static final String CODE_00 = "000000";
    public static final String DESC_00 = "成功";

    public static final String CODE_01 = "000001";
    public static final String DESC_01 = "用户未登录";

    public static final String CODE_02 = "000002";
    public static final String DESC_02 = "用户已登录";

    public static final String CODE_03 = "000003";
    public static final String DESC_03 = "数据源异常";

    public static final String CODE_04 = "000004";
    public static final String DESC_04 = "参数格式错误，或参数不能为空";

    public static final String CODE_05 = "000005";
    public static final String DESC_05 = "文件上传失败";

    public static final String CODE_06 = "000006";
    public static final String DESC_06 = "访问地址不存在";

    public static final String CODE_07 = "000007";
    public static final String DESC_07 = "accessToken异常";

    public static final String CODE_08 = "000008";
    public static final String DESC_08 = "系统异常";
}
