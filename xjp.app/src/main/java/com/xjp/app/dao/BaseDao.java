package com.xjp.app.dao;

import java.util.List;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/16.
 **/
public interface BaseDao<T> {

    public List<T> findList(T entity);
}
