package com.example.demo.entity.sysDictValue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Table(name = "sys_dict_value")
public class SysDictValue {
    /**
     * 主键UUID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 名称
     */
    @Column(name = "value_name")
    private String valueName;

    /**
     * 标识
     */
    @Column(name = "value_code")
    private String valueCode;

    /**
     * 字典ID
     */
    @Column(name = "key_id")
    private String keyId;

    /**
     * 排序
     */
    @Column(name = "value_sort")
    private Integer valueSort;

    /**
     * 父Id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0-正常 1-删除
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

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
}
