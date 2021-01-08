/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : vue

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-12-01 14:44:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(32) NOT NULL COMMENT '主键UUID',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级部门',
  `dept_sort` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` int(11) DEFAULT NULL COMMENT '是否删除  1：已删除  0：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Table structure for sys_dict_key
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_key`;
CREATE TABLE `sys_dict_key` (
  `id` varchar(32) NOT NULL COMMENT '主键UUID',
  `key_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `key_code` varchar(50) DEFAULT NULL COMMENT '标识',
  `key_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识0-正常，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表-键表';

-- ----------------------------
-- Table structure for sys_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_value`;
CREATE TABLE `sys_dict_value` (
  `id` varchar(32) NOT NULL COMMENT '主键UUID',
  `value_name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value_code` varchar(100) DEFAULT NULL COMMENT '标识',
  `key_id` varchar(32) DEFAULT NULL COMMENT '字典ID',
  `value_sort` int(11) DEFAULT NULL COMMENT '排序',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父Id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '0-正常 1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表-值表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(32) NOT NULL COMMENT '主键UUID32',
  `request_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `log_type` int(11) DEFAULT NULL COMMENT '操作类型 1操作记录 2异常记录',
  `user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `description` varchar(200) DEFAULT NULL COMMENT '操作描述',
  `action_method` varchar(500) DEFAULT NULL COMMENT '方法',
  `action_url` varchar(100) DEFAULT NULL COMMENT 'url',
  `action_params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `action_ua` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `class_path` varchar(255) DEFAULT NULL COMMENT '类路径',
  `request_method` varchar(50) DEFAULT NULL COMMENT '请求方法',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint(20) DEFAULT NULL COMMENT '消耗时间',
  `ex_desc` varchar(1000) DEFAULT NULL COMMENT '异常详情信息',
  `ex_detail` text COMMENT '异常描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(32) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_perms` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `menu_path` varchar(128) DEFAULT NULL COMMENT '前端跳转URL',
  `menu_component` varchar(255) DEFAULT NULL COMMENT '菜单组件',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父菜单ID',
  `menu_icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `menu_sort` int(11) DEFAULT NULL COMMENT '排序',
  `menu_type` int(11) DEFAULT NULL COMMENT '菜单类型 （类型   0：目录   1：菜单   2：按钮）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) DEFAULT NULL COMMENT '0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(32) NOT NULL COMMENT '主键UUID',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色标识',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识（0-正常,1-删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `data_type` int(11) DEFAULT NULL COMMENT '数据权限类型',
  `data_scope` int(11) DEFAULT NULL COMMENT '数据权限范围 1 全部 2 本级 3 本级以及子级 4 自定义',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` varchar(32) NOT NULL COMMENT '主键UUID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色部门';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(32) NOT NULL COMMENT '主键UUID',
  `role_id` varchar(32) DEFAULT NULL,
  `menu_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统表-角色菜单表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '主键UUID32位',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `organization_id` varchar(32) DEFAULT NULL COMMENT '机构ID',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `job_id` varchar(32) DEFAULT NULL COMMENT '岗位ID',
  `true_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `status_flag` int(11) DEFAULT NULL COMMENT '状态：锁定、正常、。。。',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识0-正常，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `openid` varchar(255) DEFAULT NULL COMMENT 'openid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键UUID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户角色';
