package com.example.demo.cache;

import com.example.demo.entity.system.SysUser;
import com.example.demo.entity.system.vo.SysUserVo;
import com.example.demo.security.exception.JsonException;
import com.example.demo.security.util.ResultEnum;
import com.example.demo.security.util.SecurityUser;
import com.example.demo.service.system.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 用户信息
 *
 * @author: N469
 * @date: 2019-12-23 09:34
 */
@Component
public class UserCache {

    private static final Logger logger = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisCache redisCache;


    public SysUser getUserCache() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = (SysUser) userDetails;
        return sysUser;
    }
    /**
     * 获取用户
     * @return
     */
    public SecurityUser getUserDetails() {
        try {
            return (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            logger.info("Exception："+e.toString());
            throw new JsonException(ResultEnum.LOGIN_TIMEOUT.getCode(), ResultEnum.LOGIN_TIMEOUT.getText());
        }
    }

    /**
     * 获取用户名称
     * @return 用户名称
     */
    public String getUsername(){
        SecurityUser user = getUserDetails();
        String userName = user.getUsername();
        return userName;
    }

    /**
     * 获取用户id
     * @return 用户id
     */
    public String getUserId(){
        SecurityUser user = getUserDetails();
        String userId = user.getUserId();
        return userId;
    }

    /**
     * 获取用户基本信息
     * @return
     */
    public SysUser getBaseUserInfo(){
        String userName = getUsername();
        SysUser sysUser = sysUserService.getUserInfoByUsername(userName);
        return sysUser;
    }

    /**
     * 获取用户信息 角色 权限
     * @return
     */
    public SysUserVo getUserInfo(){
        String userName = getUsername();
        SysUserVo sysUserVo = null;
        if(StringUtils.isNotBlank(userName)){
            sysUserVo = (SysUserVo)redisCache.get(userName);
//            if(StringUtils.isNotBlank(json)) {
//                Gson gson = new Gson();
//                sysUserVo = gson.fromJson(json, SysUserVo.class);
//            }
        }
        return sysUserVo;
    }

}
