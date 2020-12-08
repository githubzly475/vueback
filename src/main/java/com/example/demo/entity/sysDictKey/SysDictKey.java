package com.example.demo.entity.sysDictKey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Table(name = "sys_dict_key")
public class SysDictKey {
    /**
     * 主键UUID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 名称
     */
    @Column(name = "key_name")
    private String keyName;

    /**
     * 标识
     */
    @Column(name = "key_code")
    private String keyCode;

    /**
     * 备注
     */
    @Column(name = "key_remark")
    private String keyRemark;

    /**
     * 删除标识0-正常，1-删除
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
