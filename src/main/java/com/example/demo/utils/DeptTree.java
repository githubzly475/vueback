package com.example.demo.utils;

import lombok.Data;

import java.util.List;

/**
 * @Description : 部门树
 * @Date: 2020-12-30
 */
@Data
public class DeptTree {

    private String id;
    private String name;
    private String parentId;
    private List<DeptTree> children;
}
