package com.zg.vueback.security.util;

import lombok.Getter;

/**
 * 认证结果
 *
 * @author:  N469
 * @date:  2019-12-11
 *
 */
@Getter
public enum ResultEnum {

    /**
     * 认证等操作枚举注释
     */
    SUCCESS(200, "操作成功", "SUCCESS"),
    USER_PWD_ERROR(4000,"用户名或密码错误","USER_PWD_ERROR"),
    USER_DISABLED(4001,"用户已被禁用","USER_DISABLED"),
    NOT_NETWORK(5000,"系统繁忙，请稍后再试。", "NOT_NETWORK"),
    LOGIN_FAILED(5001,"登录失败，请稍后重试。", "LOGIN_FAILED"),
    LOGIN_TIMEOUT(401,"登陆已失效", "LOGIN_TIMEOUT"),
    PERM_FAILED(4002, "权限验证失败，请联系管理员。","PERM_FAILED"),
    AUTH_FAILED(4003, "认证失败，请联系管理员。","AUTH_FAILED");

    private final Integer code;
    private final String text;
    private final String message;

    ResultEnum(Integer code, String text, String message) {
        this.code = code;
        this.text = text;
        this.message = message;
    }

}
