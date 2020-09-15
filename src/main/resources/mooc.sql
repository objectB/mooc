/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.12 : Database - mooc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mooc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mooc`;

/*Table structure for table `tab_permission` */

DROP TABLE IF EXISTS `tab_permission`;

CREATE TABLE `tab_permission` (
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tab_permission` */

insert  into `tab_permission`(`id`,`pid`,`title`,`icon`,`name`,`path`,`type`,`sort`,`hidden`,`create_time`,`update_time`) values 
(1,0,'个人中心',NULL,NULL,NULL,1,0,0,NULL,NULL),
(2,1,'个人信息',NULL,NULL,NULL,1,0,0,NULL,NULL),
(3,1,'修改资料',NULL,NULL,NULL,1,0,0,NULL,NULL),
(4,0,'文章中心',NULL,NULL,NULL,1,0,0,NULL,NULL),
(5,4,'编写文章',NULL,NULL,NULL,1,0,0,NULL,NULL),
(6,4,'管理文章',NULL,NULL,NULL,1,0,0,NULL,NULL),
(7,4,'文件管理',NULL,NULL,NULL,1,0,0,NULL,NULL),
(8,0,'管理中心',NULL,NULL,NULL,1,0,0,NULL,NULL),
(9,8,'用户管理',NULL,NULL,NULL,1,0,0,NULL,NULL),
(10,8,'角色管理',NULL,NULL,NULL,1,0,0,NULL,NULL),
(11,8,'菜单管理',NULL,NULL,NULL,1,0,0,NULL,NULL);

/*Table structure for table `tab_role` */

DROP TABLE IF EXISTS `tab_role`;

CREATE TABLE `tab_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `desc` varchar(20) DEFAULT NULL COMMENT '角色描述',
  `avatar` varchar(255) DEFAULT NULL COMMENT '默认角色图片',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tab_role` */

insert  into `tab_role`(`id`,`name`,`desc`,`avatar`,`sort`,`create_time`,`update_time`) values 
(1,'超管',NULL,NULL,0,NULL,NULL),
(2,'普通用户',NULL,NULL,0,NULL,NULL);

/*Table structure for table `tab_role_permission` */

DROP TABLE IF EXISTS `tab_role_permission`;

CREATE TABLE `tab_role_permission` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_role_permission` */

insert  into `tab_role_permission`(`role_id`,`permission_id`) values 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(2,6),
(2,7);

/*Table structure for table `tab_user` */

DROP TABLE IF EXISTS `tab_user`;

CREATE TABLE `tab_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `username` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `moto` varchar(255) DEFAULT NULL COMMENT '座右铭',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别(0:未知,1:男 ,2:女)',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态(0:启动,1:禁用)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除(0:未删除,1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tab_user` */

insert  into `tab_user`(`id`,`phone`,`nickname`,`username`,`password`,`avatar`,`moto`,`sex`,`email`,`status`,`create_time`,`update_time`,`del`) values 
(1,'',NULL,'zhangsan','123456',NULL,NULL,0,NULL,0,NULL,NULL,0),
(2,NULL,NULL,'lisi','123456',NULL,NULL,0,NULL,0,NULL,NULL,0);

/*Table structure for table `tab_user_role` */

DROP TABLE IF EXISTS `tab_user_role`;

CREATE TABLE `tab_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_user_role` */

insert  into `tab_user_role`(`user_id`,`role_id`) values 
(1,1),
(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
