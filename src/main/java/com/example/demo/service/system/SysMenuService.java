package com.example.demo.service.system;


import com.example.demo.entity.system.SysMenu;
import com.example.demo.utils.ResultData;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单Service
 *
 * @author: N469
 * @date: 2019-12-04 14:45
 */
public interface SysMenuService {

    /**
     * 查询用户ID下菜单
     * @param userId
     * @return
     */
    List<SysMenu> queryMenuByUserId(String userId);

    /**
     * 查询用户ID下按钮权限
     * @param userId
     * @return
     */
    Set<String> queryButtonByUserId(String userId);

    /**
     * 根据菜单ID删除
     * @param id
     * @return
     */
    ResultData removeMenuById(String id);

    /**
     * 新增
     * @param bean
     * @return
     */
    ResultData save(SysMenu bean);

    /**
     * 修改
     * @param bean
     * @return
     */
    ResultData update(SysMenu bean);

    List<SysMenu> queryMenuTree(String userId);

    SysMenu queryById(String id);

    void deleteById(String id);
}
