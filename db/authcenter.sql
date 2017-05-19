/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : authcenter

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-05-19 18:12:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_log
-- ----------------------------
DROP TABLE IF EXISTS `auth_log`;
CREATE TABLE `auth_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL COMMENT '操作人',
  `start_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `send_time` int(11) DEFAULT NULL COMMENT '消耗时间',
  `url` varchar(100) DEFAULT NULL COMMENT '请求路径',
  `ip` varchar(30) DEFAULT NULL COMMENT 'ip地址',
  `method` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `parameter` text COMMENT '请求参数',
  `user_agent` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `result` text COMMENT '请求结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_log
-- ----------------------------

-- ----------------------------
-- Table structure for auth_organization
-- ----------------------------
DROP TABLE IF EXISTS `auth_organization`;
CREATE TABLE `auth_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '部门名称',
  `description` varchar(500) DEFAULT NULL COMMENT '部门描述',
  `pid` int(11) DEFAULT NULL COMMENT '上级部门',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_organization
-- ----------------------------

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '权限名称',
  `type` tinyint(1) NOT NULL COMMENT '类型（0：菜单  1：按钮）',
  `pid` int(11) DEFAULT NULL COMMENT '父级',
  `icon` varchar(45) DEFAULT NULL COMMENT '图标',
  `url` varchar(100) DEFAULT NULL,
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `description` varchar(500) DEFAULT NULL COMMENT '权限描述',
  `orders` int(11) DEFAULT NULL COMMENT '排序值',
  `status` tinyint(1) NOT NULL COMMENT '状态（0：禁用  1：正常）',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL COMMENT '角色码',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------

-- ----------------------------
-- Table structure for auth_system
-- ----------------------------
DROP TABLE IF EXISTS `auth_system`;
CREATE TABLE `auth_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '系统名称',
  `title` varchar(50) DEFAULT NULL COMMENT '系统标题',
  `description` varchar(500) DEFAULT NULL COMMENT '系统描述',
  `status` tinyint(1) NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_system
-- ----------------------------

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT '盐',
  `email` varchar(45) DEFAULT NULL COMMENT '邮件',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `sex` tinyint(1) DEFAULT NULL,
  `avatar` varchar(45) DEFAULT NULL COMMENT '头像',
  `realname` varchar(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------

-- ----------------------------
-- Table structure for auth_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_organization`;
CREATE TABLE `auth_user_organization` (
  `user_id` int(11) NOT NULL,
  `organization_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_organization
-- ----------------------------

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for user_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_role_permission`;
CREATE TABLE `user_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_permission
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
