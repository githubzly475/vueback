package com.example.demo.controller.system;

import com.example.demo.cache.UserCache;
import com.example.demo.entity.system.SysMenu;
import com.example.demo.entity.system.SysRole;
import com.example.demo.entity.system.SysRoleMenu;
import com.example.demo.mapper.system.SysRoleMapper;
import com.example.demo.mapper.system.SysRoleMenuMapper;
import com.example.demo.utils.ResultData;
import com.example.demo.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description : 角色
 * @Date: 2021-01-07
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private UserCache userCache;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @RequestMapping("queryRoles")
    public ResultData queryRoles(@RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                 @RequestParam(value ="pageSize",defaultValue = "5",required = false) Integer pageSize, SysRole sysRole){
        Map<String,Object> params=new HashMap<>();
        PageHelper.startPage(page,pageSize);
        List<SysRole> list=sysRoleMapper.queryRoles(params);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(list);

        return ResultData.ok(sysRolePageInfo);
    }

    @Transactional
    @RequestMapping("/add")
    public ResultData add(@RequestBody SysRole sysRole){
        if(null !=sysRole.getRoleId() && StringUtils.isNotBlank(sysRole.getRoleId())){
            sysRoleMapper.updateByPrimaryKeySelective(sysRole);
            sysRoleMenuMapper.deleteByRoleId(sysRole.getRoleId());
        }else{
            sysRole.setRoleId(UUIDUtils.get32UUID());
            sysRole.setCreateTime(new Date());
            sysRole.setCreateBy(userCache.getUserId());
            sysRoleMapper.insertSelective(sysRole);
        }
        List<SysMenu> menuResource = sysRole.getMenuResource();
        if(menuResource.size()>0 && !CollectionUtils.isEmpty(menuResource)){
            for (SysMenu sm:menuResource) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(UUIDUtils.get32UUID());
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenu.setMenuId(sm.getMenuId());
                sysRoleMenuMapper.insertSelective(sysRoleMenu);
            }
        }
        return ResultData.ok();
    }

    @RequestMapping("/queryRoleById")
    public ResultData queryRoleById(String id){

       SysRole sysRole=sysRoleMapper.queryRoleById(id);
        return ResultData.ok(sysRole);
    }


}
