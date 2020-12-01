package com.example.demo.entity.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户表
 *
 * @author:  N469
 * @date:  2019-12-04
 *
 */
@Data
public class SysUser implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 机构ID
     */
    private String organizationId;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 岗位ID
     */
    private String jobId;

    /**
     * 真实姓名
     */
    private String trueName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 账号状态 0 正常 1 禁用
     */
    private Integer statusFlag;

    /**
     * 用户类型 0 员工 1 客户家属 2 客户
     */
    private Integer userType;

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
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * openid
     */
    private String openid;

    /**
     * 角色列表
     */
    private List<SysRole> sysRoleList;

}
