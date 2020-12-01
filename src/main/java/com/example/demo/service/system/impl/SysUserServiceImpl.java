package com.example.demo.service.system.impl;

import com.example.demo.entity.system.SysUser;
import com.example.demo.mapper.system.SysMenuMapper;
import com.example.demo.mapper.system.SysRoleMapper;
import com.example.demo.mapper.system.SysUserMapper;
import com.example.demo.service.system.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 系统用户实现类
 *
 * @author: N469
 * @date: 2019-12-04 14:46
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    public Set<String> findPermsByUserId(String userId) {
        return sysMenuMapper.findPermsByUserId(userId).stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findRoleIdByUserId(String userId) {
        return sysRoleMapper.selectUserRoleListByUserId(userId).stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
    }

    @Override
    public SysUser getUserInfoByUsername(String userName) {
        return sysUserMapper.getUserInfoByUsername(userName);
    }
}
