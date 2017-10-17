package com.xjp.app.dao.example;

import com.xjp.app.common.annotation.MyBatisDao;
import com.xjp.app.dao.BaseDao;
import com.xjp.app.model.example.SysUser;

import java.util.Map;

@MyBatisDao
public interface UserDao extends BaseDao<SysUser>{

    public void saveSysUser(Map<String, Object> params);

    public SysUser findById(Map<String, Object> params);

    public SysUser findByIdCard(Map<String, Object> params);

    public void deleteById(Map<String, Object> params);
}
