package com.example.demo.enums.system;

/**
 * 系统相关公共枚举类
 *
 * @author:  N469
 * @date:  2019-12-05
 *
 */
public enum CommonEnum {

    NOT_DEL(0,"NOT_DEL","未删除"),
    IS_DEL(1, "IS_DEL","已删除");

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

    CommonEnum(int code, String name, String text) {
        this.code = code;
        this.name = name;
        this.text = text;
    }

    public static CommonEnum getByCode(int code) {
        for (CommonEnum c : CommonEnum.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public static CommonEnum getByName(String name) {
        for (CommonEnum c : CommonEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public static CommonEnum getByText(String text) {
        for (CommonEnum c : CommonEnum.values()) {
            if (c.getText().equals(text)) {
                return c;
            }
        }
        return null;
    }

}
