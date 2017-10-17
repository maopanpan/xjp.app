package com.xjp.app.model.example;

import com.xjp.app.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by maopanpan on 2017/08/08.
 */
public class SysUser extends BaseEntity<SysUser> {

    private Long id;
    private String name;
    private String idCard;
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
