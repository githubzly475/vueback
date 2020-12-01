package com.example.demo.log.annotation;


import java.lang.annotation.*;

/**
 * 系统日志
 *
 * @author:  N469
 * @date:  2019-12-27
 *
 * 元注解，定义注解被保留策略，一般有三种策略
 *      1、RetentionPolicy.SOURCE 注解只保留在源文件中，在编译成class文件的时候被遗弃
 *      2、RetentionPolicy.CLASS 注解被保留在class中，但是在jvm加载的时候被抛弃，这个是默认的声明周期
 *      3、RetentionPolicy.RUNTIME 注解在jvm加载的时候仍被保留
 *
 *  定义了注解声明在哪些元素之前
 *
 */
@Retention(RetentionPolicy.RUNTIME)

@Target({ElementType.METHOD})
@Documented
public @interface SysLog {
    //定义成员 //描述
    String description() default "" ;
}
