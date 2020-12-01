package com.example.demo.enums.system;

/**
 * 状态标识 status_flag
 *
 * @author:  N469
 * @date:  2019-12-05
 *
 */
public enum SysUserEnum {

    ENABLE(0,"ENABLE","未禁用"),
    DISABLED(1, "DISABLED","已禁用");

    private final int code;
    private final String name;
    private final String text;

    public String getText() {
        return text;
    }
    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    SysUserEnum(int code, String name, String text) {
        this.code = code;
        this.name = name;
        this.text = text;
    }

    public static SysUserEnum getByCode(int code) {
        for (SysUserEnum c : SysUserEnum.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public static SysUserEnum getByName(String name) {
        for (SysUserEnum c : SysUserEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public static SysUserEnum getByText(String text) {
        for (SysUserEnum c : SysUserEnum.values()) {
            if (c.getText().equals(text)) {
                return c;
            }
        }
        return null;
    }

}
