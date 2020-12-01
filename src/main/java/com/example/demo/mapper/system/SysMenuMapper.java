package com.example.demo.mapper.system;

import com.example.demo.entity.system.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 菜单Mapper
 *
 * @author:  N469
 * @date:  2019-12-12
 *
 */
@Repository
public interface SysMenuMapper {

    /**
     * 查询所有菜单
     * @return
     *
     */
    Set<SysMenu> findAll();

    /**
     * 新增菜单
     * @param record
     * @return
     */
    int insert(SysMenu record);

    /**
     * 单一查询菜单
     * @param menuId
     * @return
     */
    SysMenu selectByPrimaryKey(String menuId);

    /**
     * 更新菜单
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * 更新菜单
     * @param record
     * @return
     */
    int update(SysMenu record);

    /**
     * 根据用户id查询菜单
     * @param userId
     * @return
     *
     */
    List<SysMenu> queryMenuList(String userId);

    /**
     * 根据用户Id查询菜单权限
     * @param userId
     * @return
     */
    List<String> findPermsByUserId(String userId);

    /**
     * 根据用户ID查询按钮权限
     * @param userId
     * @return
     */
    List<String> queryButtonPermsListByUserId(String userId);

    /**
     * 根据菜单ID查询子菜单个数，用于删除校验
     * @param menuId
     * @return
     */
    int findChildrenMenuCountByParentId(@Param(value = "menuId") String menuId);

    /**
     * 根据主键删除
     * @param menuId
     * @return
     */
    int deleteByPrimaryKey(@Param(value = "menuId") String menuId);

}
