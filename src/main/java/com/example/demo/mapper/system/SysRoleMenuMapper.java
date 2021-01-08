package com.example.demo.mapper.system;

import com.example.demo.entity.system.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface SysRoleMenuMapper extends Mapper<SysRoleMenu> {
    void deleteByRoleId(@Param(value = "roleId") String roleId);
}
