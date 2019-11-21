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

 Date: 12/12/2019 21:23:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL COMMENT '用户标识',
  `accountId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户，邮箱',
  `userName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户实名',
  `userPhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `userMail` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `userBankId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '绑定银行卡',
  `userBankPhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行预留手机号',
  `payPassword` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付密码',
  `balance` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '余额',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `userStatus` int(1) NOT NULL COMMENT '用户状态  0注销  1实名在用  2未实名',
  `endTime` datetime(0) DEFAULT NULL COMMENT '注销时间',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2019019, 'meizxbj@gmail.com', '梅忠翔', '16601387513', 'meizxbj@gmail.com', '6212612221232532691', '16601387513', '123456', NULL, '2019-11-29 19:19:59', 1, NULL);
INSERT INTO `user` VALUES (2019020, '2509395927@qq.com', '独角戏', '12312312333', '2509395927@qq.com', '1212121212121212121', '11121212122', '123456', NULL, '2019-12-02 15:24:33', 0, NULL);
INSERT INTO `user` VALUES (2019021, '2509395927@qq.com', NULL, NULL, '2509395927@qq.com', NULL, NULL, NULL, NULL, '2019-12-02 15:39:13', 2, NULL);

SET FOREIGN_KEY_CHECKS = 1;
