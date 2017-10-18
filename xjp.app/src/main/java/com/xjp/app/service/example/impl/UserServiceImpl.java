package com.xjp.app.service.example.impl;

import com.xjp.app.common.interceptor.pageinterceptor.Page;
import com.xjp.app.dao.example.UserDao;
import com.xjp.app.model.example.SysUser;
import com.xjp.app.service.BaseService;
import com.xjp.app.service.example.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/12.
 **/
@Service
public class UserServiceImpl extends BaseService<UserDao, SysUser> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysUser(Map<String, Object> params) throws Exception {
        userDao.saveSysUser(params);
    }

    @Override
    public SysUser findById(Map<String, Object> params) {
        return userDao.findById(params);
    }

    @Override
    public SysUser findByIdCard(Map<String, Object> params) {
        return userDao.findByIdCard(params);
    }

    @Override
    public void deleteById(Map<String, Object> params) {
        userDao.deleteById(params);
    }

    public Page<SysUser> findPage(Page<SysUser> page, SysUser sysUser) {
        return super.findPage(page, sysUser);
    }
}
