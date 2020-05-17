/*
 Navicat Premium Data Transfer

 Source Server         : FSTP
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : ace

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 17/05/2020 20:55:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ace_apc_position
-- ----------------------------
DROP TABLE IF EXISTS `ace_apc_position`;
CREATE TABLE `ace_apc_position`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `security_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `security_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exch` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `total_qty` bigint(20) NOT NULL COMMENT '持有总量=可用数量+冻结数量+在途数量',
  `available_qty` bigint(20) NULL DEFAULT 0 COMMENT '可用数量',
  `freeze_qty` bigint(20) NULL DEFAULT 0 COMMENT '冻结数量(卖出时冻结)',
  `onway_qty` bigint(20) NULL DEFAULT NULL COMMENT '在途数量（买入时在途 T+1）',
  `ave_cost_price` decimal(7, 2) NOT NULL COMMENT '持仓成本均价',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
