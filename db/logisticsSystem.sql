/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.23-log : Database - logisticssystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`logisticssystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `logisticssystem`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                         `username` varchar(30) DEFAULT NULL COMMENT '管理员登录账号',
                         `password` varchar(100) DEFAULT NULL COMMENT '管理员登录密码',
                         `name` varchar(30) DEFAULT NULL COMMENT '管理员姓名',
                         `phone` varchar(11) DEFAULT NULL COMMENT '管理员联系电话',
                         `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `username` varchar(30) DEFAULT NULL COMMENT '员工账号',
                            `password` varchar(100) DEFAULT NULL COMMENT '员工密码',
                            `name` varchar(30) DEFAULT NULL COMMENT '员工姓名',
                            `type_name` varchar(30) DEFAULT NULL COMMENT '工种名',
                            `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
                            `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `deleted` int(11) DEFAULT '0' COMMENT '逻辑删除字段',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

/*Table structure for table `employee_repository` */

DROP TABLE IF EXISTS `employee_repository`;

CREATE TABLE `employee_repository` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `repository_id` int(11) DEFAULT NULL COMMENT '分公司id',
                                       `employee_id` int(11) DEFAULT NULL COMMENT '员工id',
                                       `created` datetime DEFAULT CURRENT_TIMESTAMP,
                                       `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `employee_repository` */

/*Table structure for table `employee_type` */

DROP TABLE IF EXISTS `employee_type`;

CREATE TABLE `employee_type` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                 `type_name` varchar(30) DEFAULT NULL COMMENT '工种名',
                                 `created` datetime DEFAULT CURRENT_TIMESTAMP,
                                 `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `employee_type` */

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `order_id` varchar(11) DEFAULT NULL COMMENT '反馈订单号',
                            `title` varchar(50) DEFAULT NULL COMMENT '标题',
                            `desc` varchar(500) DEFAULT NULL COMMENT '内容',
                            `photos_url` varchar(500) DEFAULT NULL COMMENT '图片url路径',
                            `status` tinyint(3) DEFAULT '0' COMMENT '反馈处理状态',
                            `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                         `orders_key` varchar(50) DEFAULT NULL COMMENT '保存到redis的所有订单集合',
                         `total_weight` varchar(50) DEFAULT NULL COMMENT '该批货物总重',
                         `total_price` varchar(50) DEFAULT NULL COMMENT '货物总单价',
                         `start_point` varchar(50) DEFAULT NULL COMMENT '货物配送起点',
                         `end_point` varchar(50) DEFAULT NULL COMMENT '货物配送终点',
                         `created` datetime DEFAULT CURRENT_TIMESTAMP,
                         `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                          `order_id` varchar(50) DEFAULT NULL COMMENT '订单号',
                          `start_point` varchar(50) DEFAULT NULL COMMENT '发货点',
                          `end_point` varchar(50) DEFAULT NULL COMMENT '收货点',
                          `distance` varchar(50) DEFAULT NULL COMMENT '总距离',
                          `consignor` varchar(30) DEFAULT NULL COMMENT '发货人姓名',
                          `consignor_phone` varchar(11) DEFAULT NULL COMMENT '发货人电话',
                          `addressee` varchar(30) DEFAULT NULL COMMENT '收件人姓名',
                          `addressee_phone` varchar(11) DEFAULT NULL COMMENT '收件人电话',
                          `weight` varchar(40) DEFAULT NULL COMMENT '寄件规格/kg',
                          `total_price` varchar(50) DEFAULT NULL COMMENT '寄件总价',
                          `pay_status` tinyint(3) DEFAULT '0' COMMENT '付款状态：[0]未付款 [1]已付款 [2]已退款',
                          `notes` varchar(300) DEFAULT NULL COMMENT '寄件备注信息',
                          `order_status` tinyint(3) DEFAULT '0' COMMENT '订单状态：[0]未收件 [1]已收件 [2]配送中 [3]已收货',
                          `id_card_one` varchar(100) DEFAULT NULL COMMENT '身份证正面照',
                          `id_card_two` varchar(100) DEFAULT NULL COMMENT '身份证反面照',
                          `created` datetime DEFAULT CURRENT_TIMESTAMP,
                          `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `deleted` int(11) DEFAULT '0' COMMENT '逻辑删除字段',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

/*Table structure for table `orders_employee` */

DROP TABLE IF EXISTS `orders_employee`;

CREATE TABLE `orders_employee` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `orders_id` varchar(50) DEFAULT NULL COMMENT '存储所有订单的key',
                                   `employee_id` int(11) DEFAULT NULL COMMENT '收件员工号',
                                   `status` tinyint(3) DEFAULT '0' COMMENT '[0]未送达公司 [1]已送达公司 [2]已送出公司',
                                   `repository_id` int(11) DEFAULT '0' COMMENT '存储货物的公司id',
                                   `created` datetime DEFAULT CURRENT_TIMESTAMP,
                                   `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Data for the table `orders_employee` */

/*Table structure for table `repository` */

DROP TABLE IF EXISTS `repository`;

CREATE TABLE `repository` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `repository_name` varchar(50) DEFAULT NULL COMMENT '公司名',
                              `address` varchar(50) DEFAULT NULL COMMENT '公司地址',
                              `created` datetime DEFAULT CURRENT_TIMESTAMP,
                              `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `repository` */

/*Table structure for table `truck_repository` */

DROP TABLE IF EXISTS `truck_repository`;

CREATE TABLE `truck_repository` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `car_no` varchar(30) DEFAULT NULL COMMENT '货车车牌',
                                    `repository_id` int(11) DEFAULT NULL COMMENT '分公司仓库id',
                                    `is_transport` tinyint(3) DEFAULT '0' COMMENT '[0]未在运输 [1]正在运输',
                                    `created` datetime DEFAULT CURRENT_TIMESTAMP,
                                    `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `truck_repository` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(32) DEFAULT NULL,
                        `password` varchar(32) DEFAULT NULL,
                        `created` datetime DEFAULT CURRENT_TIMESTAMP,
                        `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`created`,`updated`) values (1,'username','123456','2020-06-07 14:59:44','2020-06-07 14:59:44'),(2,'222222222222','wwwwwwwwwwwwww','2020-06-07 15:38:55','2020-06-07 16:07:11'),(3,'sssssss','aaaaaaaaaaaaa','2020-06-07 15:40:14','2020-06-07 16:06:44'),(4,'admin4-test','admin','2020-06-07 16:09:13','2020-06-07 16:09:45');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
