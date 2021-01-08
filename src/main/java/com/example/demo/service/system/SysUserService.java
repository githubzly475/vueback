package com.example.demo.service.system;

import com.example.demo.entity.system.SysUser;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 系统用户Service
 *
 * @author: N469
 * @date: 2019-12-04 14:45
 */
public interface SysUserService {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 根据用户Id查询菜单权限
     * @param userId
     * @return
     */
    Set<String> findPermsByUserId(String userId);

    /**
     * 根据用户Id查询用户角色
     * @param userId
     * @return
     */
    Set<String> findRoleIdByUserId(String userId);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    SysUser getUserInfoByUsername(String userName);

    List<SysUser> qyeryUserList(HashMap<String, Object> params);
}
