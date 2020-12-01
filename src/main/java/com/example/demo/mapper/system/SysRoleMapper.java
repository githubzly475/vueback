package com.example.demo.mapper.system;

import com.example.demo.entity.system.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {

    /**
     * 根据用户Id查询角色列表
     * @param userId
     * @return
     */
    List<String> selectUserRoleListByUserId(String userId);

    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

}
