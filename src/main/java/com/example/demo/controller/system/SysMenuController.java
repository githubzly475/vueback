package com.example.demo.controller.system;

import com.example.demo.cache.RedisCache;
import com.example.demo.cache.UserCache;
import com.example.demo.entity.system.SysMenu;
import com.example.demo.entity.system.vo.MenuVo;
import com.example.demo.entity.system.vo.SysUserVo;
import com.example.demo.log.annotation.SysLog;
import com.example.demo.service.system.SysMenuService;
import com.example.demo.utils.MenuTreeUtil;
import com.example.demo.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统菜单控制器
 *
 * @author: N469
 * @date: 2019-12-11 16:26
 */
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserCache userCache;

    @SysLog(description = "获取菜单路由")
    @GetMapping("/menu/getRouters")
    public ResultData getRouters(){
        // TODO 查询mysql
//        SysUserVo sysUserVo = UserCache.getUserInfo();
        List<MenuVo> menuVoList;
        Set<String> buttonPerms;
//        if(sysUserVo != null){
//            menuVoList = sysUserVo.getMenuList();
//            buttonPerms = sysUserVo.getButtonSet();
//        } else {
            String userId = userCache.getUserId();
            String userName = userCache.getUsername();
            List<SysMenu> list = sysMenuService.queryMenuByUserId(userId);
            menuVoList = MenuTreeUtil.buildMenus(list);
            buttonPerms = sysMenuService.queryButtonByUserId(userId);
            SysUserVo vo = new SysUserVo().setUsername(userName).setMenuList(menuVoList).setButtonSet(buttonPerms);
            redisCache.set(userName, vo, 3600 * 24 * 7);
//        }

        Map<String, Object> result = new HashMap<>(2);
        result.put("menuRouters", menuVoList);
        result.put("buttonPerms", buttonPerms);


        return ResultData.ok(result);

    }


}
