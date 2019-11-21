/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : ticket_manager

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 12/12/2019 21:25:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for train_detail
-- ----------------------------
DROP TABLE IF EXISTS `train_detail`;
CREATE TABLE `train_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trainNumber` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `classOne` int(4) DEFAULT NULL COMMENT '余量',
  `classTwo` int(4) DEFAULT NULL COMMENT '余量',
  `sumOne` int(4) DEFAULT NULL COMMENT '总量',
  `sumTwo` int(4) DEFAULT NULL COMMENT '总量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_detail
-- ----------------------------
INSERT INTO `train_detail` VALUES (2, 'G1121', '2019-12-17', 122, 223, 700, 700);
INSERT INTO `train_detail` VALUES (3, 'G1123', '2019-12-17', 87, 114, 700, 700);
INSERT INTO `train_detail` VALUES (5, 'G6', '2019-12-17', 112, 133, 700, 700);
INSERT INTO `train_detail` VALUES (6, 'G7', '2019-12-17', 555, 668, 700, 700);
INSERT INTO `train_detail` VALUES (7, 'G8', '2019-12-17', 555, 678, 700, 700);
INSERT INTO `train_detail` VALUES (8, 'G9', '2019-12-17', 123, 231, 700, 700);
INSERT INTO `train_detail` VALUES (9, 'G10', '2019-12-17', 567, 676, 700, 700);
INSERT INTO `train_detail` VALUES (10, 'G11', '2019-12-17', 67, 77, 700, 700);
INSERT INTO `train_detail` VALUES (11, 'G12', '2019-12-17', 55, 677, 700, 700);
INSERT INTO `train_detail` VALUES (12, 'G13', '2019-12-17', 157, 197, 700, 700);
INSERT INTO `train_detail` VALUES (13, '1122', '2019-12-17', 120, 223, 121, 222);

SET FOREIGN_KEY_CHECKS = 1;
