package com.example.demo.entity.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单
 *
 * @author:  N469
 * @date:  2019-12-11
 *
 */
@Data
public class SysMenu implements Serializable {

    /**
     * PK
     */
    private String menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单权限标识
     */
    private String menuPerms;

    /**
     * 前端跳转URL
     */
    private String menuPath;

    /**
     * 菜单组件
     */
    private String menuComponent;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 图标
     */
    private String menuIcon;

    /**
     * 排序
     */
    private Integer menuSort;

    /**
     * 菜单类型 （类型   0：目录   1：菜单   2：按钮）


     */
    private Integer menuType;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除 0正常 1删除
     */
    private Integer delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    private static final long serialVersionUID = 1L;

    // 拓展字段
    /**
     * 子菜单
     */
    private List<SysMenu> children;


    /**
     * 角色
     */
    private List<SysRole> roleList;

    /**
     * 父级菜单名称
     */
    private String parentName;

    /**
     * 菜单级别
     */
    private Integer level;

}
