/*
 Navicat Premium Data Transfer

 Source Server         : FSTP
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : fams

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 17/05/2020 20:56:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fams_exchange
-- ----------------------------
DROP TABLE IF EXISTS `fams_exchange`;
CREATE TABLE `fams_exchange`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exch` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'fams系统中的市场代码',
  `exch_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '中文名',
  `ts_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'tushare中的市场代码',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fams_exchange
-- ----------------------------
INSERT INTO `fams_exchange` VALUES (1, 'sh', '上交所', 'SSE', '2020-02-28 20:11:41', '2020-02-28 20:11:41');
INSERT INTO `fams_exchange` VALUES (2, 'sz', '深交所', 'SZSE', '2020-02-28 20:12:11', '2020-02-28 20:12:11');
INSERT INTO `fams_exchange` VALUES (3, 'hk', '港交所', 'XHKG', '2020-02-28 20:12:31', '2020-02-28 20:12:31');
INSERT INTO `fams_exchange` VALUES (4, 'cffex', '中金所', 'CFFEX', '2020-02-28 20:13:39', '2020-02-28 20:13:05');
INSERT INTO `fams_exchange` VALUES (5, 'shfe', '上期所', 'SHFE', '2020-02-28 20:13:37', '2020-02-28 20:13:37');
INSERT INTO `fams_exchange` VALUES (6, 'czce', '郑商所', 'CZCE', '2020-02-28 20:14:24', '2020-02-28 20:14:24');
INSERT INTO `fams_exchange` VALUES (7, 'dce', '大商所', 'DCE', '2020-02-28 20:14:46', '2020-02-28 20:14:46');
INSERT INTO `fams_exchange` VALUES (8, 'ine', '上能源', 'INE', '2020-02-28 20:15:09', '2020-02-28 20:15:09');
INSERT INTO `fams_exchange` VALUES (9, 'ib', '银行间市场', 'IB', '2020-02-28 20:15:30', '2020-02-28 20:15:30');

SET FOREIGN_KEY_CHECKS = 1;
