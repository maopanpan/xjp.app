package com.xjp.app.service;

import com.xjp.app.common.interceptor.pageinterceptor.Page;
import com.xjp.app.dao.BaseDao;
import com.xjp.app.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/16.
 **/
@Transactional(readOnly = true)
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
