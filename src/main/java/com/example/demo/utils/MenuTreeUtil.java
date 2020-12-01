package com.example.demo.utils;

import com.example.demo.entity.system.SysMenu;
import com.example.demo.entity.system.vo.MenuMetaVo;
import com.example.demo.entity.system.vo.MenuVo;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 相关树权限工具类
 *
 * @author: N469
 * @date: 2019-12-16 11:03
 */
@UtilityClass
public class MenuTreeUtil {

    public List<MenuVo> buildMenus(List<SysMenu> sysMenuList) {
        List<MenuVo> list = new LinkedList<>();
        sysMenuList.forEach(sysMenu -> {
                    if (sysMenu != null) {
                        List<SysMenu> menuDTOList = new ArrayList<>(sysMenu.getChildren());
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(sysMenu.getMenuName());
                        menuVo.setPath(sysMenu.getMenuPath());
                        if ("0".equals(sysMenu.getParentId())) {
                            //一级目录需要加斜杠，不然访问 会跳转404页面
                            menuVo.setPath("/" + sysMenu.getMenuPath());
                            menuVo.setComponent(StringUtils.isEmpty(sysMenu.getMenuComponent()) ? "Layout" : sysMenu.getMenuComponent());
                        } else if (!StringUtils.isEmpty(sysMenu.getMenuComponent())) {
                            menuVo.setComponent(sysMenu.getMenuComponent());
                        }
                        menuVo.setMeta(new MenuMetaVo(sysMenu.getMenuName(), sysMenu.getMenuIcon()));
                        if (menuDTOList != null && menuDTOList.size() != 0 && sysMenu.getMenuType() == 0) {
                            menuVo.setChildren(buildMenus(menuDTOList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if ("0".equals(sysMenu.getParentId())) {
                            menuVo.setAlwaysShow(false);
                            menuVo.setRedirect("noredirect");
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            menuVo1.setPath(sysMenu.getMenuPath());
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    /**
     * 遍历菜单
     *
     * @param menuList
     * @param menus
     * @param menuType
     */
    public void findChildren(List<SysMenu> menuList, List<SysMenu> menus, int menuType) {
        for (SysMenu sysMenu : menuList) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                if (menuType == 1 && menu.getMenuType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue;
                }
                if (sysMenu.getMenuId() != null && sysMenu.getMenuId().equals(menu.getParentId())) {
                    menu.setParentName(sysMenu.getMenuName());
                    menu.setLevel(sysMenu.getLevel() + 1);
                    if (exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            sysMenu.setChildren(children);
            children.sort(Comparator.comparing(SysMenu::getMenuSort));
            findChildren(children, menus, menuType);
        }
    }

    /**
     * 判断菜单是否存在
     *
     * @param sysMenus
     * @param sysMenu
     * @return
     */
    public boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
        boolean exist = false;
        for (SysMenu menu : sysMenus) {
            if (menu.getMenuId().equals(sysMenu.getMenuId())) {
                exist = true;
            }
        }
        return !exist;
    }

}
