package com.example.demo.service.system.impl;

import com.example.demo.cache.RedisCache;
import com.example.demo.cache.UserCache;
import com.example.demo.entity.system.SysMenu;
import com.example.demo.mapper.system.SysMenuMapper;
import com.example.demo.service.system.SysMenuService;
import com.example.demo.utils.MenuTreeUtil;
import com.example.demo.utils.ResultData;
import com.example.demo.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 系统菜单实现类
 *
 * @author: N469
 * @date: 2019-12-04 14:46
 */
@Service
//@Transactional(rollbackFor = {Exception.class})
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private RedisCache redisCache;
    @Resource
    private UserCache userCache;

    @Override
    public List<SysMenu> queryMenuByUserId(String userId) {
        List<SysMenu> sysMenus = new ArrayList<>();
        List<SysMenu> menuList = sysMenuMapper.queryMenuList(userId);
        menuList.forEach(menu -> {
            if(menu.getParentId() == null || "0".equals(menu.getParentId())){
                menu.setLevel(0);
                if(MenuTreeUtil.exists(sysMenus, menu)){
                    sysMenus.add(menu);
                }
            }
        });
        sysMenus.sort(Comparator.comparing(SysMenu::getMenuSort));
        MenuTreeUtil.findChildren(sysMenus, menuList, 0);
        return sysMenus;
    }

    @Override
    public Set<String> queryButtonByUserId(String userId) {
        return sysMenuMapper.queryButtonPermsListByUserId(userId).stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
    }

    @Override
    public ResultData removeMenuById(String id) {
        int count = sysMenuMapper.findChildrenMenuCountByParentId(id);
        if(count > 0){
            return ResultData.error("菜单含有下级菜单不能删除");
        }
        redisCache.del(userCache.getUsername());
        return ResultData.ok(sysMenuMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultData save(SysMenu bean) {
        bean.setMenuId(UUIDUtils.get32UUID());
        bean.setCreateBy(userCache.getUserId());
        int count = sysMenuMapper.insert(bean);
        if(count > 0){
            redisCache.del(userCache.getUsername());
            return ResultData.ok();
        }
        return ResultData.error("添加失败");
    }

    @Override
    public ResultData update(SysMenu bean) {
        bean.setUpdateBy(userCache.getUserId());
        int count = sysMenuMapper.update(bean);
        if(count > 0){
            redisCache.del(userCache.getUsername());
            return ResultData.ok();
        }
        return ResultData.error("添加失败");
    }


}
