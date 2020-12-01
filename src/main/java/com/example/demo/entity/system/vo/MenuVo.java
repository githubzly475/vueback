package com.example.demo.entity.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单VO
 *
 * @author: N469
 * @date: 2019-12-16 13:12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String path;
    private String redirect;
    private String component;
    private Boolean alwaysShow;
    private MenuMetaVo meta;
    private List<MenuVo> children;
}
