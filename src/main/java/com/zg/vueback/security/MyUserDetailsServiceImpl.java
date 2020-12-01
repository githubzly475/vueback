package com.zg.vueback.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * Created with IDEA
 * @author:hxb
 *
 * @Date: 2019/5/22 13:40
 * @Description:
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser sysUser = userService.findByUsername(username);
//        if (sysUser == null) {
            /**
             * 不返回用户不存在
             */
//            throw new UsernameNotFoundException(ResultEnum.USER_PWD_ERROR.getMessage());
//        }else if(SysUserEnum.DISABLED.getCode() == sysUser.getStatusFlag()){
//            throw new DisabledException(ResultEnum.USER_DISABLED.getMessage());
//        }
//        Collection<? extends GrantedAuthority> userAuthorities = getUserAuthorities(sysUser.getUserId());
//        return new SecurityUser(sysUser.getUserId(),sysUser.getUsername(), sysUser.getPassword(), userAuthorities);
        return null;
    }


    /**
     * 封装 根据用户Id获取权限
     *
     * @param userId
     * @return
     */
    private Collection<? extends GrantedAuthority> getUserAuthorities(String userId) {
        // 获取用户拥有的角色
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        // 权限集合
//        Set<String> permissions = userService.findPermsByUserId(userId);
        Set<String> permissions = null;

//        // 角色集合
//        Set<String> roleIds = userService.findRoleIdByUserId(userId);
//        permissions.addAll(roleIds);
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));
        return authorities;
    }

}
