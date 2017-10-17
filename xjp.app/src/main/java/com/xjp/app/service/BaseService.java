package com.xjp.app.service;

import com.xjp.app.common.pageinterceptor.Page;
import com.xjp.app.dao.BaseDao;
import com.xjp.app.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/16.
 **/
public abstract class BaseService<B extends BaseDao<T>, T extends BaseEntity<T>> {

    @Autowired
    private B dao;

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;
    }
}
