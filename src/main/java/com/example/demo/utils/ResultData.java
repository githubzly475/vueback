package com.example.demo.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 响应体
 *
 * @author: N469
 * @date: 2019-12-17 08:28
 */
@Getter
@Setter
@Accessors(chain = true)
public class ResultData implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code = 200;
    private String msg;
    private Object data;

    public static ResultData ok() {
        ResultData r = new ResultData();
        r.setMsg("操作成功");
        return r;
    }

    public static ResultData ok(Object data) {
        ResultData r = new ResultData();
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static ResultData ok(String msg, Object data) {
        ResultData r = new ResultData();
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static ResultData error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static ResultData error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static ResultData error(int code, String msg) {
        ResultData r = new ResultData();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

}
