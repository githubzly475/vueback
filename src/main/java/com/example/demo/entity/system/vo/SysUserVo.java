package com.example.demo.entity.system.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * redis user info vo
 *
 * @author: N469
 * @date: 2019-12-23 15:30
 */
@Data
@Accessors(chain=true)
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private List<MenuVo> menuList;
    private Set<String> buttonSet;

}
