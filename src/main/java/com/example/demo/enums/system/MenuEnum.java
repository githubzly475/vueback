package com.example.demo.enums.system;
/**
 * 菜单枚举类
 *
 * @author:  N469
 * @date:  2019-12-12
 *
 */
public enum MenuEnum {

    /**
     * 菜单类型
     */
    CATALOG(0, "目录","CATALOG"),
    MENU(1,"菜单","MENU"),
    BUTTON(2,"按钮", "BUTTON");

    private final int code;
    private final String name;
    private final String text;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    MenuEnum(int code, String name, String text) {
        this.code = code;
        this.name = name;
        this.text = text;
    }

    public static MenuEnum getByCode(int code) {
        for (MenuEnum c : MenuEnum.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public static MenuEnum getByName(String name) {
        for (MenuEnum c : MenuEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public static MenuEnum getByText(String text) {
        for (MenuEnum c : MenuEnum.values()) {
            if (c.getText().equals(text)) {
                return c;
            }
        }
        return null;
    }

}
