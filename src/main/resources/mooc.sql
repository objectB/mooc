/*
Navicat MySQL Data Transfer

Source Server         : mooc
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : mooc

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-09-19 15:22:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tab_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tab_permission`;
CREATE TABLE `tab_permission` (
  `typename` varchar(255) NOT NULL DEFAULT 'menu',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) NOT NULL COMMENT '父ID',
  `title` varchar(32) NOT NULL COMMENT '菜单名称/权限名称',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单路由名称',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路由地址',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '菜单类型 1>菜单 2>按钮',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 隐藏 1 显示',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `permissionname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_permission
-- ----------------------------
INSERT INTO `tab_permission` VALUES ('menu', '1', '0', '个人中心', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '2', '1', '个人信息', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '3', '1', '修改资料', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '4', '0', '文章中心', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '5', '4', '编写文章', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '6', '4', '管理文章', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '7', '4', '文件管理', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '8', '0', '管理中心', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '9', '8', '用户管理', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '10', '8', '角色管理', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('menu', '11', '8', '菜单管理', null, null, null, '1', '0', '0', null, null, null);
INSERT INTO `tab_permission` VALUES ('button', '12', '9', '用户添加', null, '', null, '2', '0', '0', null, null, 'user:add');
INSERT INTO `tab_permission` VALUES ('button', '13', '9', '用户删除', null, '', null, '2', '0', '0', null, null, 'user:del');
INSERT INTO `tab_permission` VALUES ('button', '14', '9', '用户更新', null, '', null, '2', '0', '0', null, null, 'user:del');

-- ----------------------------
-- Table structure for `tab_role`
-- ----------------------------
DROP TABLE IF EXISTS `tab_role`;
CREATE TABLE `tab_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `desc` varchar(20) DEFAULT NULL COMMENT '角色描述',
  `avatar` varchar(255) DEFAULT NULL COMMENT '默认角色图片',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(11) NOT NULL DEFAULT 'user' COMMENT '角色标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_role
-- ----------------------------
INSERT INTO `tab_role` VALUES ('1', '超管', null, null, '0', null, null, 'admin');
INSERT INTO `tab_role` VALUES ('2', '普通用户', null, null, '0', null, null, 'user');

-- ----------------------------
-- Table structure for `tab_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tab_role_permission`;
CREATE TABLE `tab_role_permission` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_role_permission
-- ----------------------------
INSERT INTO `tab_role_permission` VALUES ('1', '1');
INSERT INTO `tab_role_permission` VALUES ('1', '2');
INSERT INTO `tab_role_permission` VALUES ('1', '3');
INSERT INTO `tab_role_permission` VALUES ('1', '4');
INSERT INTO `tab_role_permission` VALUES ('1', '5');
INSERT INTO `tab_role_permission` VALUES ('1', '6');
INSERT INTO `tab_role_permission` VALUES ('1', '7');
INSERT INTO `tab_role_permission` VALUES ('1', '8');
INSERT INTO `tab_role_permission` VALUES ('1', '9');
INSERT INTO `tab_role_permission` VALUES ('1', '10');
INSERT INTO `tab_role_permission` VALUES ('2', '1');
INSERT INTO `tab_role_permission` VALUES ('2', '2');
INSERT INTO `tab_role_permission` VALUES ('2', '3');
INSERT INTO `tab_role_permission` VALUES ('2', '4');
INSERT INTO `tab_role_permission` VALUES ('2', '5');
INSERT INTO `tab_role_permission` VALUES ('2', '6');
INSERT INTO `tab_role_permission` VALUES ('2', '7');
INSERT INTO `tab_role_permission` VALUES ('1', '11');
INSERT INTO `tab_role_permission` VALUES ('1', '12');
INSERT INTO `tab_role_permission` VALUES ('1', '13');
INSERT INTO `tab_role_permission` VALUES ('1', '14');

-- ----------------------------
-- Table structure for `tab_user`
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `username` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `moto` varchar(255) DEFAULT NULL COMMENT '座右铭',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别(0:未知,1:男 ,2:女)',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态(0:启动,1:禁用)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除(0:未删除,1:已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_name` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES ('1', '', null, 'zhangsan', '00b3187384f2708025074f28764a4a30', null, null, '0', null, '0', null, null, '0');
INSERT INTO `tab_user` VALUES ('2', null, null, 'lisi', '00b3187384f2708025074f28764a4a30', null, null, '0', null, '0', null, null, '0');

-- ----------------------------
-- Table structure for `tab_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tab_user_role`;
CREATE TABLE `tab_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_user_role
-- ----------------------------
INSERT INTO `tab_user_role` VALUES ('1', '1');
INSERT INTO `tab_user_role` VALUES ('2', '2');
