package com.example.demo.controller.system;


import com.example.demo.entity.system.SysUser;
import com.example.demo.mapper.system.SysUserMapper;
import com.example.demo.service.system.SysUserService;
import com.example.demo.utils.ResultData;
import com.example.demo.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description : 用户
 * @Date: 2020-12-23
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @RequestMapping("/queryUserList")
    public ResultData queryUserList(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize, SysUser sysUser){
        HashMap<String, Object> params = new HashMap<>();
        if(null != sysUser){
            params.put("userName",sysUser.getUsername());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> userList = sysUserService.qyeryUserList(params);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(userList);
        return ResultData.ok("成功",sysUserPageInfo);
    }

    /**
     *  保存用户  待完善。。
     * @param sysUser
     * @return
     */
    @RequestMapping("/save")
    public ResultData save(@RequestBody SysUser sysUser){
        // TODO:  一系列待完善
       int i=0;
        if(null !=sysUser.getUserId() && StringUtils.isNotBlank(sysUser.getUserId())){
            sysUser.setUpdateTime(new Date());
             i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }else{
            sysUser.setUserId(UUIDUtils.get32UUID());
            sysUser.setPassword(new BCryptPasswordEncoder().encode("123456"));
            sysUser.setCreateTime(new Date());
             i = sysUserMapper.insertSelective(sysUser);
        }

        if(1 ==i){
            return ResultData.ok();
        }
        return ResultData.error("添加失败");
    }


    @RequestMapping("/getByUserId")
    public ResultData getByUserId(String id){

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if(null !=sysUser){
            return ResultData.ok("成功",sysUser);
        }
        return ResultData.error("失败");
    }

    @RequestMapping("/delByUserId")
    public ResultData delByUserId(String id){

        int i = sysUserMapper.deleteByPrimaryKey(id);
        if(1 ==i) {
            return ResultData.ok("成功");
        }
        return ResultData.error("失败");
    }


}
