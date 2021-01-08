package com.example.demo.entity.system.sysdept;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_dept")
public class SysDept {
    /**
     * 主键UUID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 部门名称
     */
    @Column(name = "dept_name")
    private String deptName;

    /**
     * 上级部门
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 排序
     */
    @Column(name = "dept_sort")
    private Integer deptSort;

    /**
     * 是否删除  1：已删除  0：正常
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    @Transient
    private String createUser;
}
