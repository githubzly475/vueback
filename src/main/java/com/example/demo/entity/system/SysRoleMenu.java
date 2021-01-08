package com.example.demo.entity.system;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_role_menu")
public class SysRoleMenu {
    /**
     * 主键UUID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "menu_id")
    private String menuId;
}