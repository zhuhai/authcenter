/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : authcenter

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-06-16 18:24:54
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_organization
-- ----------------------------
INSERT INTO `auth_organization` VALUES ('1', '总公司', '这是总公司', '0', '2017-06-14 18:06:55', '2017-06-14 18:06:55');

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(45) NOT NULL COMMENT '权限名称',
  `type` tinyint(1) NOT NULL COMMENT '类型（1：目录  2：菜单 3：按钮）',
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', '', '系统组织管理', '1', '0', 'glyphicon glyphicon-wrench', null, '1', '', '1', '1', '2017-06-12 15:13:27', '2017-05-25 15:58:55');
INSERT INTO `auth_permission` VALUES ('2', 'auth:system:view', '系统管理', '2', '1', null, '/system', '1', null, '1', '1', '2017-06-12 15:52:39', '2017-05-25 16:00:33');
INSERT INTO `auth_permission` VALUES ('3', 'auth:organization:view', '组织管理', '2', '1', null, '/organization', '1', null, '1', '1', '2017-06-12 15:52:34', '2017-05-25 16:13:35');
INSERT INTO `auth_permission` VALUES ('4', '', '用户角色管理', '1', '0', 'glyphicon glyphicon-user', null, '1', null, '2', '1', '2017-06-12 15:05:47', '2017-05-26 13:23:02');
INSERT INTO `auth_permission` VALUES ('5', 'auth:user:view', '用户管理', '2', '4', null, '/user', '1', null, '3', '1', '2017-06-12 15:52:31', '2017-06-12 14:16:29');
INSERT INTO `auth_permission` VALUES ('6', 'auth:role:view', '角色管理', '2', '4', null, '/role', '1', null, '4', '1', '2017-06-12 15:52:28', '2017-06-12 14:17:06');
INSERT INTO `auth_permission` VALUES ('7', '', '权限资源管理', '1', '0', 'glyphicon glyphicon-lock', null, '1', null, '5', '1', '2017-06-12 15:21:30', '2017-06-12 14:21:44');
INSERT INTO `auth_permission` VALUES ('8', 'auth:permission:view', '权限管理', '2', '7', null, '/permission', '1', null, '6', '1', '2017-06-12 15:52:25', '2017-06-12 14:22:54');
INSERT INTO `auth_permission` VALUES ('9', null, '其他数据管理', '1', '0', 'glyphicon glyphicon-list', null, '1', null, '7', '1', '2017-06-12 15:27:49', '2017-06-12 14:23:41');
INSERT INTO `auth_permission` VALUES ('10', 'auth:session:view', '会话管理', '2', '9', null, '/session', '1', null, '1', '1', '2017-06-12 15:52:21', '2017-06-12 14:25:39');
INSERT INTO `auth_permission` VALUES ('11', 'auth:log:view', '日志管理', '2', '9', null, '/log', '1', null, '1', '1', '2017-06-12 15:52:18', '2017-06-12 14:26:20');
INSERT INTO `auth_permission` VALUES ('12', 'auth:user:create', '添加用户', '3', '5', null, '/user/create', '1', null, '1', '1', '2017-06-16 17:15:04', '2017-06-16 17:15:04');
INSERT INTO `auth_permission` VALUES ('13', 'auth:user:update', '修改用户', '3', '5', null, '/user/update', '1', null, '2', '1', '2017-06-16 17:16:05', '2017-06-16 17:16:05');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', 'admin', '管理员', '管理员角色', '2017-05-24 14:31:28', '2017-05-24 14:31:28');

-- ----------------------------
-- Table structure for auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
INSERT INTO `auth_role_permission` VALUES ('1', '1');
INSERT INTO `auth_role_permission` VALUES ('1', '2');
INSERT INTO `auth_role_permission` VALUES ('1', '3');
INSERT INTO `auth_role_permission` VALUES ('1', '4');
INSERT INTO `auth_role_permission` VALUES ('1', '5');
INSERT INTO `auth_role_permission` VALUES ('1', '6');
INSERT INTO `auth_role_permission` VALUES ('1', '7');
INSERT INTO `auth_role_permission` VALUES ('1', '8');
INSERT INTO `auth_role_permission` VALUES ('1', '9');
INSERT INTO `auth_role_permission` VALUES ('1', '10');
INSERT INTO `auth_role_permission` VALUES ('1', '11');
INSERT INTO `auth_role_permission` VALUES ('1', '12');
INSERT INTO `auth_role_permission` VALUES ('1', '13');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_system
-- ----------------------------
INSERT INTO `auth_system` VALUES ('1', 'authcenter', '统一认证中心', null, '1', '2017-06-09 15:37:20', '2017-06-09 15:37:20');

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
  `sex` tinyint(1) DEFAULT NULL COMMENT '0：男， 1：女',
  `avatar` varchar(45) DEFAULT NULL COMMENT '头像',
  `realname` varchar(20) DEFAULT NULL,
  `status` tinyint(1) NOT NULL COMMENT '状态（0：禁用  1：正常）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', 'admin', '1c81554a150472f4ca438bdd1c5cc67223bcf3e5', 'abcdefg', null, null, '0', '/static/avatars/user.jpg', null, '1', '2017-05-27 17:06:21', '2017-06-15 16:42:17');
INSERT INTO `auth_user` VALUES ('8', 'tom', 'sdf', 'fssfds', null, null, '1', null, null, '1', '2017-05-27 17:16:42', '2017-06-15 16:42:19');
INSERT INTO `auth_user` VALUES ('9', 'test', '123456', null, null, null, '0', null, null, '1', '2017-06-07 15:30:28', '2017-06-15 16:42:22');
INSERT INTO `auth_user` VALUES ('16', 'abc', '', null, null, null, '0', null, null, '0', '2017-06-08 14:08:27', '2017-06-15 16:42:24');
INSERT INTO `auth_user` VALUES ('17', '23424', '', null, null, null, '0', null, null, '1', '2017-06-08 14:11:00', '2017-06-15 16:42:26');
INSERT INTO `auth_user` VALUES ('18', 'bbbbbb', '', null, null, null, '1', null, null, '0', '2017-06-08 14:20:57', '2017-06-15 16:42:27');
INSERT INTO `auth_user` VALUES ('19', 'aaaaaa', '', null, null, null, '1', null, null, '1', '2017-06-08 14:21:53', '2017-06-15 16:42:27');
INSERT INTO `auth_user` VALUES ('20', 'bsffwfew', '', null, null, null, '0', null, null, '1', '2017-06-08 14:41:56', '2017-06-15 16:42:29');
INSERT INTO `auth_user` VALUES ('21', 'test1234', 'sdfsfd', null, null, null, '1', null, null, '1', '2017-06-09 15:40:32', '2017-06-15 16:42:32');

-- ----------------------------
-- Table structure for auth_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_organization`;
CREATE TABLE `auth_user_organization` (
  `user_id` int(11) NOT NULL,
  `organization_id` int(11) NOT NULL,
  UNIQUE KEY `uk_user_organization` (`user_id`,`organization_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_organization
-- ----------------------------
INSERT INTO `auth_user_organization` VALUES ('1', '1');

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  UNIQUE KEY `uk_user_role` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES ('1', '1');
SET FOREIGN_KEY_CHECKS=1;
