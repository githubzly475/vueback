package com.zg.vueback.model.pollutionSources;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "pollution_sources")
public class PollutionSources {
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 地点
     */
    private String address;

    /**
     * 坐标点
     */
    @Column(name = "address_points")
    private String addressPoints;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 类型(工地/餐饮/汽修/其他)
     */
    private String type;

    /**
     * 未删0已删1
     */
    @Column(name = "is_del")
    private Integer isDel;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;

    private String remark5;

    private String remark6;

    private String remark7;

    private String remark8;

    private String remark9;

    private String remark10;

    @Column(name = "other_remark")
    private String otherRemark;

    @Column(name = "creator_user")
    private String creatorUser;

    /**
     * create_time
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String userName;

    @Transient
    private String typeName;
    @Transient
    private String typeId;


}
