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

 Date: 17/05/2020 20:56:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fams_global_param
-- ----------------------------
DROP TABLE IF EXISTS `fams_global_param`;
CREATE TABLE `fams_global_param`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '递增主键',
  `param_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数名',
  `param_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数值',
  `param_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数所属范围',
  `param_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数描述',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fams_global_param
-- ----------------------------
INSERT INTO `fams_global_param` VALUES (1, 'trade_date', '20200226', 'fams', '全系统交易日设置', '2020-02-26 12:46:51', '2020-02-26 12:46:54');
INSERT INTO `fams_global_param` VALUES (2, 'test_param_name', 'test_param_value', 'fams', NULL, '2020-02-26 12:49:04', '2020-02-26 12:49:04');

SET FOREIGN_KEY_CHECKS = 1;
