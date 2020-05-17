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

 Date: 17/05/2020 20:55:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ace_trade_order
-- ----------------------------
DROP TABLE IF EXISTS `ace_trade_order`;
CREATE TABLE `ace_trade_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `related_order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联订单单号，撤单时填写',
  `exchange_order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易所订单编号',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '交易账户',
  `security_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '证券标的',
  `exch` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '交易市场',
  `order_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单状态机中的状态：\r\nInit(初始化订单)、New（新订单未报）、Pending(新订单已报)、Part-Exec（部分成交）、Full-Exec（完全成交）、Rejected（订单被拒绝）、Withdrawing-trade（已报交易所的订单撤单）、Withdraw（订单未成交，全部撤回）\r\n订单状态机中的状态：\r\nInit(初始化订单)、New（新订单未报）、Pending(新订单已报)、Part-Exec（部分成交）、Full-Exec（完全成交）、Rejected（订单被拒绝）、Withdrawing-trade（已报交易所的订单撤单）、Withdraw（订单未成交，全部撤回）\r\n订单状态机中的状态：\r\nInit(初始化订单)、New（新订单未报）、Pending(新订单已报)、Part-Exec（部分成交）、Full-Exec（完全成交）、Rejected（订单被拒绝）、Withdrawing-trade（已报交易所的订单撤单）、Withdraw（订单未成交，全部撤回）\r\n订单状态机中的状态：\r\nInit(初始化订单)、New（新订单未报）、Pending(新订单已报)、Part-Exec（部分成交）、Full-Exec（完全成交）、Rejected（订单被拒绝）、Withdrawing-trade（已报交易所的订单撤单）、Withdraw（订单未成交，全部撤回）\r\n订单状态机中的状态：Init(初始化订单)、New（新订单未报）、Pending(新订单已报)、Part-Exec（部分成交）、Full-Exec（完全成交）、Rejected（订单被拒绝）、Withdrawing-trade（已报交易所的订单撤单）、Withdraw（订单未成交，全部撤回',
  `order_reject_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '填写拒单代码：\r\n交易所拒单：EXCH_REJECT\r\nOMS拒单：OMS_REJECT\r\n填写拒单代码：交易所拒单：EXCH_REJECT\r\nOMS拒单：OMS_REJECT\r\n',
  `order_reject_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '拒单具体原因',
  `trade_time` datetime(0) NOT NULL COMMENT '交易时间',
  `order_executor` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单来源名称，对应king的orderexecutor组件。用于回报等信息',
  `strategy_container` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '如果是策略单，需要',
  `order_price` decimal(7, 2) NOT NULL COMMENT '委托价格',
  `order_volume` bigint(20) NOT NULL COMMENT '委托量，以股为单位',
  `order_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单类型：limit、fak、fok等',
  `order_multiply` int(11) NOT NULL COMMENT '证券乘数，例如1手=100股，此处为100',
  `order_direction` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '委托方向：BUY/SELL/LONG/SHORT',
  `exec_price` decimal(7, 2) NULL DEFAULT NULL COMMENT '成交均价',
  `exec_volume` bigint(20) NULL DEFAULT NULL COMMENT '成交数量',
  `exec_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单执行状态：\r\nOPEN：等待执行，处于new、pengding、part-exec、withdraw-trade的订单，即还未完全执行结束的委托\r\nCLOSE：执行完成，处于full-exec、Rejected、withdraw的订单，即已经结束的委托\r\n订单执行状态：',
  `withdraw_flag` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '撤单标志Y/F',
  `withdraw_volume` bigint(20) NULL DEFAULT NULL COMMENT '已撤单数量',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
