package com.example.demo.entity.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author:  N469
 * @date:  2019-12-27
 *
 */
@Data
public class SysLog implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * PK
     */
    private String id;

    /**
     * 请求IP地址
     */
    private String requestIp;

    /**
     * 类型 1操作日志 2异常日志
     */
    private Integer logType;

    /**
     * 操作人
     */
    private String userName;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 请求方法
     */
    private String actionMethod;

    /**
     * 请求参数
     */
    private String actionParams;

    /**
     * 浏览器
     */
    private String actionUa;

    /**
     * 类路径
     */
    private String classPath;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求url
     */
    private String actionUrl;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 消耗时间
     */
    private Long consumingTime;

    /**
     * 异常详情信息
     */
    private String exDesc;

    /**
     * 异常描述
     */
    private String exDetail;

}
