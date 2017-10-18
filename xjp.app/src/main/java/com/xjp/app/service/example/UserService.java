package com.xjp.app.service.example;

import com.xjp.app.common.interceptor.pageinterceptor.Page;
import com.xjp.app.model.example.SysUser;

import java.util.Map;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/12.
 **/
public interface UserService {

    public void saveSysUser(Map<String, Object> params) throws Exception;

    public SysUser findById(Map<String, Object> params);

    public SysUser findByIdCard(Map<String, Object> params);

    public void deleteById(Map<String, Object> params);

    public Page<SysUser> findPage(Page<SysUser> page, SysUser sysUser);
}