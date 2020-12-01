package com.example.demo.entity.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单中VO
 *
 * @author:  N469
 * @date:  2019-12-16
 *
 */
@Data
public class MenuMetaVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private String icon;

    public MenuMetaVo() {
    }

    public MenuMetaVo(String title, String icon){
        this.title = title;
        this.icon = icon;
    }

}
