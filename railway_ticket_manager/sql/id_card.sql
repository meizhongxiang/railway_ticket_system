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

 Date: 12/12/2019 21:30:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for id_card
-- ----------------------------
DROP TABLE IF EXISTS `id_card`;
CREATE TABLE `id_card`  (
  `userId` int(11) NOT NULL,
  `cardId` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号码',
  `userName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户姓名',
  `userBankId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行卡',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of id_card
-- ----------------------------
INSERT INTO `id_card` VALUES (2019019, '321232200111190107', '梅忠翔', '6212612221232532691');
INSERT INTO `id_card` VALUES (2019020, '213212199012321256', '独角戏', '1212121212121212121');

SET FOREIGN_KEY_CHECKS = 1;
