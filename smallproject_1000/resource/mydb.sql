/*
Navicat MySQL Data Transfer

Source Server         : yuanjie
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-11-22 12:47:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('37');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `available` bit(1) NOT NULL,
  `birthday` date DEFAULT NULL,
  `clazz` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '', '2012-11-12', '1', '2017-11-12 18:13:24', '2017-11-14 23:29:34', '卜寒云');
INSERT INTO `student` VALUES ('6', '', '2011-11-12', '1', '2017-11-12 23:28:12', '2017-11-14 18:39:55', 'yuanx');
INSERT INTO `student` VALUES ('8', '', '2009-05-20', '1', '2017-11-13 18:09:08', '2017-11-14 18:39:06', 'yuanx');
INSERT INTO `student` VALUES ('9', '', '2010-07-16', '3', '2017-11-13 22:44:14', '2017-11-13 22:44:14', 'cc');
INSERT INTO `student` VALUES ('11', '', '2002-08-09', '4', '2017-11-14 16:29:21', '2017-11-14 16:29:21', 'dd');
INSERT INTO `student` VALUES ('12', '', '2010-01-05', '5', '2017-11-14 22:27:12', '2017-11-14 22:27:12', 'ee');
INSERT INTO `student` VALUES ('16', '', '2017-11-15', '1', '2017-11-15 17:07:04', '2017-11-15 17:07:04', 'yuanx');
INSERT INTO `student` VALUES ('18', '', '2013-11-15', '7', '2017-11-15 17:13:25', '2017-11-15 17:21:52', 'yuanx');
INSERT INTO `student` VALUES ('22', '\0', '2012-02-01', '8', '2017-11-17 23:52:31', '2017-11-17 23:56:30', 'hh');
INSERT INTO `student` VALUES ('30', '', '2013-11-08', '1', '2017-11-18 14:41:53', '2017-11-18 19:37:10', 'yuanx');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `available` bit(1) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('35', '', '2017-11-21 09:54:18', '11111111111', 'yuanj', 'c0dab9ae0327b7016f6134bb986ec463');
INSERT INTO `users` VALUES ('36', '', '2017-11-22 00:18:55', '12121212121', 'yuan', '5e443ffee758e756a70c816fb7f5d9f7');
