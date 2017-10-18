package com.xjp.app.vo.app;

import com.xjp.app.common.Constants;

/**
 * @Author: maopanpan
 * @Description: 响应对象
 * @Date: 2017/10/18.
 **/
public class ResponseObject {
    /**
     * 响应编号
     **/
    public String code;
    /**
     * 响应说明
     **/
    public String desc;
    /**
     * 自定义数据
     **/
    public Object data;

    public ResponseObject(Object data) {
        this(Constants.CODE_00, Constants.DESC_00, data);
    }

    public ResponseObject(String code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
