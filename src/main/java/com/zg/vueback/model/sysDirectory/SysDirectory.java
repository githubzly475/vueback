package com.zg.vueback.model.sysDirectory;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_directory")
public class SysDirectory {
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    private String code;

    private String value;

    @Column(name = "parent_code")
    private String parentCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 未删0已删1
     */
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * 排序
     */
    private Integer sort;

    @Column(name = "create_user")
    private String createUser;

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

    @Column(name = "update_user")
    private String updateUser;

    /**
     * 数据字典类型key_code
     */
    @Column(name = "dty_key_code")
    private String dtyKeyCode;

  /*  @Transient
    private List<SysDirectoryType> dictTypeList;*/


}
