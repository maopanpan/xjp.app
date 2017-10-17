package com.xjp.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xjp.app.common.pageinterceptor.Page;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/16.
 **/
public abstract  class BaseEntity<T> implements Serializable {

    /**
     * 当前实体分页对象
     */
    protected Page<T> page;

    @JsonIgnore
    @XmlTransient
    public Page<T> getPage() {
        if (page == null){
            page = new Page<T>();
        }
        return page;
    }

    public Page<T> setPage(Page<T> page) {
        this.page = page;
        return page;
    }
}
