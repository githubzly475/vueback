package com.example.demo.controller.system;

import com.example.demo.cache.RedisCache;
import com.example.demo.cache.UserCache;
import com.example.demo.entity.system.SysMenu;
import com.example.demo.log.annotation.SysLog;
import com.example.demo.service.system.SysMenuService;
import com.example.demo.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理-菜单控制器
 *
 * @author: N469
 * @date: 2019-12-11 16:26
 */
@RestController
@RequestMapping("sysMenuSetting")
public class SysMenuSettingController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserCache userCache;

    @SysLog(description = "获取菜单数")
    @GetMapping("/menu")
//    @PreAuthorize("hasAnyAuthority(sys:menu:view)")
    public ResultData getMenuTree(){
        String userId = userCache.getUserId();
        List<SysMenu> list = sysMenuService.queryMenuByUserId(userId);
        return ResultData.ok(list);
    }

    @SysLog(description = "删除菜单")
//    @PreAuthorize("hasAnyAuthority('sys:menu:delete')")
    @DeleteMapping("/menu/{id}")
    public ResultData deleteMenu(@PathVariable("id") String id){
        return sysMenuService.removeMenuById(id);
    }

    @SysLog(description = "新增菜单")
//    @PreAuthorize("hasAnyAuthority('sys:menu:insert')")
    @PostMapping("/menu")
    public ResultData insertMenu(@RequestBody SysMenu bean){
        return sysMenuService.save(bean);
    }

    @SysLog(description = "修改菜单")
//    @PreAuthorize("hasAnyAuthority('sys:menu:insert')")
    @PutMapping("/menu")
    public ResultData updateMenu(@RequestBody SysMenu bean){
        return sysMenuService.update(bean);
    }

}
