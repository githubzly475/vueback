package com.example.demo.mapper.system;

import com.example.demo.entity.system.SysUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * *此处修改为对应实体类名称*
 *
 * @author:  N469
 * @date:  2019-12-04
 *
 */
@Repository
public interface SysUserMapper {

    /**
     * 根据用户名查询用户(登录用)
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 根据用户ID查询用户信息
     * @param userName
     * @return
     */
    SysUser getUserInfoByUsername(String userName);

    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


    List<SysUser> qyeryUserList(HashMap<String, Object> params);
}
