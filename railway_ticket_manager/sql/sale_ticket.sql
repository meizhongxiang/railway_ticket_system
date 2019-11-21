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

 Date: 12/12/2019 21:28:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sale_ticket
-- ----------------------------
DROP TABLE IF EXISTS `sale_ticket`;
CREATE TABLE `sale_ticket`  (
  `ticketId` int(11) NOT NULL AUTO_INCREMENT,
  `trainNumber` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `passenger` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ticketClass` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ticketNum` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `startSite` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `endSite` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `startTime` time(0) DEFAULT NULL,
  `endTime` time(0) DEFAULT NULL,
  `useTime` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` int(5) DEFAULT NULL,
  `integral` int(5) DEFAULT NULL,
  `ticketStatus` int(1) DEFAULT NULL,
  `userAccountId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `buyDate` date DEFAULT NULL,
  PRIMARY KEY (`ticketId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale_ticket
-- ----------------------------
INSERT INTO `sale_ticket` VALUES (9, 'G512', '2019-12-17', '梅忠翔', '1', '4车1E', '济南', '北京', '08:00:00', '09:20:00', '1:20', 997, 9, 1, 'meizxbj@gmail.com', 2019019, '2019-11-25');
INSERT INTO `sale_ticket` VALUES (10, 'G5', '2019-12-17', '梅忠翔', '1', '4车2A', '济南', '北京', '08:00:00', '09:20:00', '1:20', 997, 9, 1, 'meizxbj@gmail.com', 2019019, '2019-11-26');
INSERT INTO `sale_ticket` VALUES (11, 'G5', '2019-12-17', '梅忠翔', '1', '4车2B', '济南', '北京', '08:00:00', '09:20:00', '1:20', 997, 9, 1, 'meizxbj@gmail.com', 2019019, '2019-11-26');
INSERT INTO `sale_ticket` VALUES (12, 'G5', '2019-12-17', '梅忠翔', '1', '4车2C', '济南', '北京', '08:00:00', '09:20:00', '1:20', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-01');
INSERT INTO `sale_ticket` VALUES (13, 'G5', '2019-12-17', '梅忠翔', '1', '4车2E', '济南', '北京', '08:00:00', '09:20:00', '1:20', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-07');
INSERT INTO `sale_ticket` VALUES (14, 'G8', '2019-12-17', '梅忠翔', '2', '7车9C', '济南', '北京', '12:00:00', '13:20:00', '1:20', 123, 1, 0, 'meizxbj@gmail.com', 2019019, '2019-12-07');
INSERT INTO `sale_ticket` VALUES (15, 'G6', '2019-12-17', '梅忠翔', '1', '6车17B', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-10');
INSERT INTO `sale_ticket` VALUES (16, 'G12', '2019-12-17', '梅忠翔', '1', '7车10E', '济南', '北京', '17:00:00', '18:20:00', '1:20', 123, 1, 0, 'meizxbj@gmail.com', 2019019, '2019-12-07');
INSERT INTO `sale_ticket` VALUES (17, 'G6', '2019-12-17', '梅忠翔', '1', '6车17C', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-03');
INSERT INTO `sale_ticket` VALUES (18, 'G8', '2019-12-17', '梅忠翔', '1', '2车10C', '济南', '北京', '12:00:00', '13:20:00', '1:20', 899, 8, 0, 'meizxbj@gmail.com', 2019019, '2019-12-01');
INSERT INTO `sale_ticket` VALUES (19, 'G1123', '2019-12-17', '梅忠翔', '1', '7车2A', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-10');
INSERT INTO `sale_ticket` VALUES (20, 'G6', '2019-12-17', '梅忠翔', '1', '6车17E', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-05');
INSERT INTO `sale_ticket` VALUES (21, 'G1123', '2019-12-17', '梅忠翔', '2', '12车1B', '济南', '北京', '08:00:00', '10:00:00', '2:00', 198, 1, 0, 'meizxbj@gmail.com', 2019019, '2019-11-26');
INSERT INTO `sale_ticket` VALUES (22, 'G1123', '2019-12-17', '梅忠翔', '1', '7车2B', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-28');
INSERT INTO `sale_ticket` VALUES (23, 'G1123', '2019-12-17', '梅忠翔', '1', '7车2C', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-29');
INSERT INTO `sale_ticket` VALUES (24, 'G6', '2019-12-17', '梅忠翔', '1', '6车17E', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-03');
INSERT INTO `sale_ticket` VALUES (25, 'G6', '2019-12-17', '梅忠翔', '1', '6车18A', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-10');
INSERT INTO `sale_ticket` VALUES (26, 'G6', '2019-12-17', '梅忠翔', '1', '6车18B', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-30');
INSERT INTO `sale_ticket` VALUES (27, 'G1123', '2019-12-17', '梅忠翔', '1', '7车2E', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-20');
INSERT INTO `sale_ticket` VALUES (28, 'G6', '2019-12-17', '梅忠翔', '1', '6车18C', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-23');
INSERT INTO `sale_ticket` VALUES (29, 'G1123', '2019-12-17', '梅忠翔', '1', '7车2E', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-30');
INSERT INTO `sale_ticket` VALUES (30, 'G8', '2019-12-17', '梅忠翔', '1', '2车10E', '济南', '北京', '12:00:00', '13:20:00', '1:20', 899, 8, 0, 'meizxbj@gmail.com', 2019019, '2019-12-06');
INSERT INTO `sale_ticket` VALUES (31, 'G6', '2019-12-17', '梅忠翔', '1', '6车18E', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-06');
INSERT INTO `sale_ticket` VALUES (32, 'G7', '2019-12-17', '梅忠翔', '2', '7车9E', '济南', '北京', '13:00:00', '15:00:00', '2:00', 676, 6, 0, 'meizxbj@gmail.com', 2019019, '2019-11-25');
INSERT INTO `sale_ticket` VALUES (33, 'G1123', '2019-12-17', '梅忠翔', '1', '7车3A', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-03');
INSERT INTO `sale_ticket` VALUES (34, 'G1123', '2019-12-17', '梅忠翔', '1', '7车3B', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-06');
INSERT INTO `sale_ticket` VALUES (35, 'G6', '2019-12-17', '梅忠翔', '2', '11车17E', '济南', '北京', '11:00:00', '12:00:00', '1:00', 111, 1, 0, 'meizxbj@gmail.com', 2019019, '2019-12-04');
INSERT INTO `sale_ticket` VALUES (36, 'G1123', '2019-12-17', '梅忠翔', '1', '7车3C', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-09');
INSERT INTO `sale_ticket` VALUES (37, 'G8', '2019-12-17', '梅忠翔', '2', '7车9E', '济南', '北京', '12:00:00', '13:20:00', '1:20', 123, 1, 0, 'meizxbj@gmail.com', 2019019, '2019-12-08');
INSERT INTO `sale_ticket` VALUES (38, 'G1123', '2019-12-17', '梅忠翔', '1', '7车3E', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-26');
INSERT INTO `sale_ticket` VALUES (39, 'G6', '2019-12-17', '梅忠翔', '2', '11车17E', '济南', '北京', '11:00:00', '12:00:00', '1:00', 111, 1, 0, 'meizxbj@gmail.com', 2019019, '2019-12-03');
INSERT INTO `sale_ticket` VALUES (40, 'G1123', '2019-12-17', '梅忠翔', '1', '7车3E', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-10');
INSERT INTO `sale_ticket` VALUES (41, 'G6', '2019-12-17', '梅忠翔', '1', '6车18E', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-03');
INSERT INTO `sale_ticket` VALUES (42, 'G1123', '2019-12-17', '梅忠翔', '1', '7车4A', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-06');
INSERT INTO `sale_ticket` VALUES (43, 'G6', '2019-12-17', '梅忠翔', '1', '6车19A', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-26');
INSERT INTO `sale_ticket` VALUES (44, 'G1123', '2019-12-17', '梅忠翔', '1', '7车4B', '济南', '北京', '08:00:00', '10:00:00', '2:00', 997, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-11-26');
INSERT INTO `sale_ticket` VALUES (45, 'G6', '2019-12-17', '梅忠翔', '1', '6车19B', '济南', '北京', '11:00:00', '12:00:00', '1:00', 999, 9, 0, 'meizxbj@gmail.com', 2019019, '2019-12-01');
INSERT INTO `sale_ticket` VALUES (46, 'G7', '2019-12-17', '梅忠翔', '1', '2车10E', '济南', '北京', '13:00:00', '15:00:00', '2:00', 777, 7, 0, 'meizxbj@gmail.com', 2019019, '2019-12-10');
INSERT INTO `sale_ticket` VALUES (47, '1122', '2019-12-17', '梅忠翔', '1', '1车1E', '济南', '北京', '00:04:00', '01:04:00', '1:00', 112, 1, 0, 'meizxbj@gmail.com', 2019019, '2019-12-12');
INSERT INTO `sale_ticket` VALUES (48, 'G1123', '2019-12-17', '梅忠翔', '2', '12车3C', '济南', '北京', '08:00:00', '10:00:00', '2:00', 198, 1, 0, 'meizxbj@gmail.com', 2019019, '2019-12-12');

SET FOREIGN_KEY_CHECKS = 1;
