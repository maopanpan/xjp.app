package com.xjp.app.common.annotation;

import java.lang.annotation.*;

/**
 * 数据访问层标注
 *
 * @author maopanpan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyBatisDao {

    String value() default "";

}
