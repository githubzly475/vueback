package com.zg.vueback.security.util;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IDEA
 * @author:hxb
 *
 * @Date: 2019/5/22 09:53
 * @Description:
 */
@Data
public class AjaxResponseBody implements Serializable {

    private Object code;
    private String msg;
    private Object data;
    private String result;

}
