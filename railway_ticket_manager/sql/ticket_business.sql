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

 Date: 12/12/2019 21:24:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ticket_business
-- ----------------------------
DROP TABLE IF EXISTS `ticket_business`;
CREATE TABLE `ticket_business`  (
  `tickId` int(11) NOT NULL AUTO_INCREMENT,
  `trainNumber` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `startSite` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `endSite` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `startTime` time(0) DEFAULT NULL,
  `endTime` time(0) DEFAULT NULL,
  `classOne` int(4) DEFAULT NULL,
  `classTwo` int(4) DEFAULT NULL,
  `priceOne` int(5) DEFAULT NULL,
  `priceTwo` int(5) DEFAULT NULL,
  `useTime` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`tickId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ticket_business
-- ----------------------------
INSERT INTO `ticket_business` VALUES (2, 'G1121', '2019-12-17', '济南', '北京', '20:00:00', '21:30:00', 700, 700, 1000, 500, '1:30');
INSERT INTO `ticket_business` VALUES (3, 'G1123', '2019-12-17', '济南', '北京', '08:00:00', '10:00:00', 700, 700, 997, 198, '2:00');
INSERT INTO `ticket_business` VALUES (5, 'G6', '2019-12-17', '济南', '北京', '11:00:00', '12:00:00', 700, 700, 999, 111, '1:00');
INSERT INTO `ticket_business` VALUES (6, 'G7', '2019-12-17', '济南', '北京', '13:00:00', '15:00:00', 700, 700, 777, 676, '2:00');
INSERT INTO `ticket_business` VALUES (7, 'G8', '2019-12-17', '济南', '北京', '12:00:00', '13:20:00', 700, 700, 899, 123, '1:20');
INSERT INTO `ticket_business` VALUES (9, 'G9', '2019-12-17', '济南', '北京', '14:00:00', '15:50:00', 700, 700, 333, 123, '1:50');
INSERT INTO `ticket_business` VALUES (10, 'G10', '2019-12-17', '济南', '北京', '15:00:00', '16:10:00', 700, 700, 777, 67, '2:10');
INSERT INTO `ticket_business` VALUES (11, 'G11', '2019-12-17', '济南', '北京', '16:00:00', '18:00:00', 700, 700, 990, 876, '2:00');
INSERT INTO `ticket_business` VALUES (12, 'G12', '2019-12-17', '济南', '北京', '17:00:00', '18:20:00', 700, 700, 123, 21, '1:20');
INSERT INTO `ticket_business` VALUES (13, 'G13', '2019-12-17', '济南', '北京', '18:00:00', '19:50:00', 700, 700, 332, 331, '1:50');
INSERT INTO `ticket_business` VALUES (14, '1122', '2019-12-17', '济南', '北京', '00:04:00', '01:04:00', 121, 222, 112, 121, '1:00');

SET FOREIGN_KEY_CHECKS = 1;
