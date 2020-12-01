package com.example.demo.entity.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色表
 *
 * @author:  N469
 * @date:  2019-12-05
 *
 */
@Data
public class SysRole implements Serializable {

    /**
     * PK
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识名称
     */
    private String roleCode;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 逻辑删除标识
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 数据权限类别
     */
    private Integer dataType;

    /**
     * 数据权限范围 1 全部 2 本级 3 本级以及子级 4 自定义
     */
    private String dataScope;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

}
