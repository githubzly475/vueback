/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.99_3306
Source Server Version : 50728
Source Host           : 192.168.0.99:3306
Source Database       : vue

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-12-08 14:56:24
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
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '1', '1', '1', '0', '2020-12-02 13:14:17', '2020-12-02 13:14:21', '1', '1');

-- ----------------------------
-- Table structure for sys_dict_key
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_key`;
CREATE TABLE `sys_dict_key` (
  `id` varchar(32) NOT NULL COMMENT '主键UUID',
  `key_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `key_code` varchar(50) DEFAULT NULL COMMENT '标识',
  `key_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标识0-正常，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表-键表';

-- ----------------------------
-- Records of sys_dict_key
-- ----------------------------
INSERT INTO `sys_dict_key` VALUES ('1', '中文', 'zh', '语言', '0', '2020-12-02 13:14:30', '2020-12-02 13:14:33', '1', '1');
INSERT INTO `sys_dict_key` VALUES ('a59ec8346e1a49c8aa8c1c6ef5284527', '测试', 'TEST', '测试', '0', '2020-12-08 10:58:54', '2020-12-08 10:59:16', '1', '1');

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
  `del_flag` int(11) DEFAULT '0' COMMENT '0-正常 1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表-值表';

-- ----------------------------
-- Records of sys_dict_value
-- ----------------------------
INSERT INTO `sys_dict_value` VALUES ('1', '中文简体', 'zh-CHS', '1', '1', '0', '中文简体', '0', '2020-12-02 13:14:44', '2020-12-02 13:14:46', '1', '1');
INSERT INTO `sys_dict_value` VALUES ('2', '中文繁体', 'zh-CHT', '1', '2', '0', '中文繁体', '0', '2020-12-08 09:19:23', '2020-12-08 09:19:27', '1', '1');
INSERT INTO `sys_dict_value` VALUES ('3', '测试一', 'test-1', 'a59ec8346e1a49c8aa8c1c6ef5284527', '1', '0', '测试一', '0', null, '2020-12-08 13:44:33', '1', '1');
INSERT INTO `sys_dict_value` VALUES ('50ef79da1bda4a98a42ddaf20e7fe7f9', '测试三', 'test-3', 'a59ec8346e1a49c8aa8c1c6ef5284527', '3', '0', '测试三', '0', '2020-12-08 14:31:17', '2020-12-08 14:31:39', '1', '1');
INSERT INTO `sys_dict_value` VALUES ('5eaf634d4ae442f7a6890b6a00800bc8', '测试四', 'test-4', 'a59ec8346e1a49c8aa8c1c6ef5284527', '4', null, '测试四', '0', '2020-12-08 14:32:55', null, '1', null);
INSERT INTO `sys_dict_value` VALUES ('d1848715287c40368753b4bcc4b47e9b', '测试二', 'test-2', 'a59ec8346e1a49c8aa8c1c6ef5284527', '2', '0', '测试二', '0', '2020-12-08 13:45:10', null, '1', null);

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
-- Records of sys_log
-- ----------------------------

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
  `del_flag` int(11) DEFAULT '0' COMMENT '0正常 1删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('051de277cc734eebb74e797dc5b2e225', '图标管理', '', 'icon', '', '0', 'picture', '0', '0', '2020-12-03 15:17:45', null, '1', null, '0');
INSERT INTO `sys_menu` VALUES ('064f5b93cc8147249509094721e51ee5', '字典管理', 'sys:directory', 'dictionary', 'dict', '1', 'search', '7', '1', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, 'system', null, '0', 'aboutMe', '0', '0', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('10', '菜单新增', 'sys:menu:add', null, null, '16', '', '0', '2', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('11', '部门新增', 'sys:dept:add', null, null, '3', '', '0', '2', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('13', '角色管理', 'sys:role', 'role', 'role', '1', 'role', '2', '1', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('16', '菜单管理', 'sys:menu', 'menu', 'menu', '1', 'menu', '9999', '1', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', 'sys:user', 'user', 'user', '1', 'user', '1', '1', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('3', '部门管理', 'sys:dept', 'dept', 'dict/DeptTempIndex', '1', 'dept', '3', '1', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('3dc9d7b362c6410d8ad157fd78ace458', '用户列表', 'sys:user:list', null, null, '2', 'list', '0', '2', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('47ac744b4c7745eeb3696f7419ec9dc7', '图标管理', 'sys:icon', '/icon', 'svgIcons', '051de277cc734eebb74e797dc5b2e225', 'icon', '0', '1', '2020-12-03 15:27:32', null, '1', null, '0');
INSERT INTO `sys_menu` VALUES ('5', '用户新增', 'sys:user:add', null, null, '2', 'authority', '0', '2', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');
INSERT INTO `sys_menu` VALUES ('7cdbaeec1bd64d2a9dd47b4f238646c7', '菜单删除', 'sys:menu:delete', null, null, '16', null, '0', '2', '2020-12-03 08:25:09', '2020-12-03 08:25:09', '1', '1', '0');

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
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '1', '1', '0', '2020-12-02 13:13:42', '2020-12-02 13:13:46', '1', '1', '1', '1');

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
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('1', '1', '1');

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
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('04345f1e285f4c16b01d4969dfefe8b0', '1', '064f5b93cc8147249509094721e51ee5');
INSERT INTO `sys_role_menu` VALUES ('051de277cc734eebb74e797dc5b2e224', '1', '051de277cc734eebb74e797dc5b2e225');
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('1aeb63f607da497ab88af6a9d4866e25', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('2083e668e25b49fc8320ce59149036b3', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('2ac9a13ab3a548598b6ca2b2d76ebb6f', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('2ca5c717a39d44d692dac81bdd49bc33', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('2ee82ca8d4d04c0fb738a4b9b3b06bfc', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('3051a20b41a44a6f95ca1b46deb9cffb', '1', '3dc9d7b362c6410d8ad157fd78ace458');
INSERT INTO `sys_role_menu` VALUES ('3f65b78ce47b47bbaf6107886d6bfe5c', '1', '42e260af7a364d0dab15f606f4fd3fcb');
INSERT INTO `sys_role_menu` VALUES ('3f798cfad4554be78327219cee471409', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('4618d137952246969348212fde5c7685', '1', '7cdbaeec1bd64d2a9dd47b4f238646c7');
INSERT INTO `sys_role_menu` VALUES ('47ac744b4c7745eeb3696f7419ec9dc6', '1', '47ac744b4c7745eeb3696f7419ec9dc7');
INSERT INTO `sys_role_menu` VALUES ('48082c22ac1741819ed997253f72ff49', '1', 'e96e6c48828f4e75ad56b55d7c5a9255');

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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$6piAZo7djNYIcpD2dOmNS.pifvkWyrd43b0paRhSR0O/YtaPUwMX.', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '2020-12-02 10:33:38', '2020-12-02 10:33:47', '1', '1', '1');

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

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
