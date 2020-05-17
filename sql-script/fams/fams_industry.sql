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

 Date: 17/05/2020 20:57:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fams_industry
-- ----------------------------
DROP TABLE IF EXISTS `fams_industry`;
CREATE TABLE `fams_industry`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `industry` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pull_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_update_date` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 330 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fams_industry
-- ----------------------------
INSERT INTO `fams_industry` VALUES (221, 'IT设备', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:10');
INSERT INTO `fams_industry` VALUES (222, '专用机械', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:10');
INSERT INTO `fams_industry` VALUES (223, '中成药', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:10');
INSERT INTO `fams_industry` VALUES (224, '乳制品', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:18');
INSERT INTO `fams_industry` VALUES (225, '互联网', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:18');
INSERT INTO `fams_industry` VALUES (226, '仓储物流', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:18');
INSERT INTO `fams_industry` VALUES (227, '供气供热', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:19');
INSERT INTO `fams_industry` VALUES (228, '保险', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:19');
INSERT INTO `fams_industry` VALUES (229, '元器件', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:19');
INSERT INTO `fams_industry` VALUES (230, '全国地产', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:19');
INSERT INTO `fams_industry` VALUES (231, '公共交通', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:19');
INSERT INTO `fams_industry` VALUES (232, '公路', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:19');
INSERT INTO `fams_industry` VALUES (233, '其他商业', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:19');
INSERT INTO `fams_industry` VALUES (234, '其他建材', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:19');
INSERT INTO `fams_industry` VALUES (235, '农业综合', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:20');
INSERT INTO `fams_industry` VALUES (236, '农用机械', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:20');
INSERT INTO `fams_industry` VALUES (237, '农药化肥', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:20');
INSERT INTO `fams_industry` VALUES (238, '出版业', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:28');
INSERT INTO `fams_industry` VALUES (239, '化学制药', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (240, '化工原料', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (241, '化工机械', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (242, '化纤', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (243, '区域地产', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (244, '医疗保健', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (245, '医药商业', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (246, '半导体', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (247, '商品城', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:29');
INSERT INTO `fams_industry` VALUES (248, '商贸代理', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:30');
INSERT INTO `fams_industry` VALUES (249, '啤酒', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:30');
INSERT INTO `fams_industry` VALUES (250, '园区开发', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:30');
INSERT INTO `fams_industry` VALUES (251, '塑料', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:34');
INSERT INTO `fams_industry` VALUES (252, '多元金融', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:34');
INSERT INTO `fams_industry` VALUES (253, '家居用品', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:34');
INSERT INTO `fams_industry` VALUES (254, '家用电器', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:34');
INSERT INTO `fams_industry` VALUES (255, '小金属', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:34');
INSERT INTO `fams_industry` VALUES (256, '工程机械', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:34');
INSERT INTO `fams_industry` VALUES (257, '广告包装', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:35');
INSERT INTO `fams_industry` VALUES (258, '建筑工程', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:35');
INSERT INTO `fams_industry` VALUES (259, '影视音像', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:35');
INSERT INTO `fams_industry` VALUES (260, '房产服务', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:35');
INSERT INTO `fams_industry` VALUES (261, '批发业', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:35');
INSERT INTO `fams_industry` VALUES (262, '摩托车', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:35');
INSERT INTO `fams_industry` VALUES (263, '文教休闲', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:35');
INSERT INTO `fams_industry` VALUES (264, '新型电力', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:35');
INSERT INTO `fams_industry` VALUES (265, '旅游景点', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (266, '旅游服务', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (267, '日用化工', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (268, '普钢', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (269, '服饰', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (270, '机场', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (271, '机床制造', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (272, '机械基件', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (273, '林业', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:36');
INSERT INTO `fams_industry` VALUES (274, '染料涂料', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:37');
INSERT INTO `fams_industry` VALUES (275, '橡胶', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:37');
INSERT INTO `fams_industry` VALUES (276, '水力发电', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:37');
INSERT INTO `fams_industry` VALUES (277, '水务', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:37');
INSERT INTO `fams_industry` VALUES (278, '水泥', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:37');
INSERT INTO `fams_industry` VALUES (279, '水运', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:37');
INSERT INTO `fams_industry` VALUES (280, '汽车整车', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:37');
INSERT INTO `fams_industry` VALUES (281, '汽车服务', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:37');
INSERT INTO `fams_industry` VALUES (282, '汽车配件', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:38');
INSERT INTO `fams_industry` VALUES (283, '渔业', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:38');
INSERT INTO `fams_industry` VALUES (284, '港口', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:38');
INSERT INTO `fams_industry` VALUES (285, '火力发电', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:38');
INSERT INTO `fams_industry` VALUES (286, '焦炭加工', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:38');
INSERT INTO `fams_industry` VALUES (287, '煤炭开采', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:38');
INSERT INTO `fams_industry` VALUES (288, '特种钢', 'Y', '20200309', '2020-04-11 17:13:49', '2020-03-09 23:22:38');
INSERT INTO `fams_industry` VALUES (289, '环境保护', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:38');
INSERT INTO `fams_industry` VALUES (290, '玻璃', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:39');
INSERT INTO `fams_industry` VALUES (291, '生物制药', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:39');
INSERT INTO `fams_industry` VALUES (292, '电信运营', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:39');
INSERT INTO `fams_industry` VALUES (293, '电器仪表', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:39');
INSERT INTO `fams_industry` VALUES (294, '电器连锁', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:39');
INSERT INTO `fams_industry` VALUES (295, '电气设备', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:39');
INSERT INTO `fams_industry` VALUES (296, '白酒', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:39');
INSERT INTO `fams_industry` VALUES (297, '百货', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:39');
INSERT INTO `fams_industry` VALUES (298, '石油加工', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:40');
INSERT INTO `fams_industry` VALUES (299, '石油开采', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:40');
INSERT INTO `fams_industry` VALUES (300, '石油贸易', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:40');
INSERT INTO `fams_industry` VALUES (301, '矿物制品', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:48');
INSERT INTO `fams_industry` VALUES (302, '种植业', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:48');
INSERT INTO `fams_industry` VALUES (303, '空运', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:49');
INSERT INTO `fams_industry` VALUES (304, '红黄酒', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:49');
INSERT INTO `fams_industry` VALUES (305, '纺织', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:49');
INSERT INTO `fams_industry` VALUES (306, '纺织机械', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:49');
INSERT INTO `fams_industry` VALUES (307, '综合类', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:49');
INSERT INTO `fams_industry` VALUES (308, '航空', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:49');
INSERT INTO `fams_industry` VALUES (309, '船舶', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:49');
INSERT INTO `fams_industry` VALUES (310, '装修装饰', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:49');
INSERT INTO `fams_industry` VALUES (311, '证券', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:50');
INSERT INTO `fams_industry` VALUES (312, '超市连锁', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:50');
INSERT INTO `fams_industry` VALUES (313, '路桥', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:50');
INSERT INTO `fams_industry` VALUES (314, '软件服务', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:54');
INSERT INTO `fams_industry` VALUES (315, '软饮料', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:54');
INSERT INTO `fams_industry` VALUES (316, '轻工机械', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:54');
INSERT INTO `fams_industry` VALUES (317, '运输设备', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:54');
INSERT INTO `fams_industry` VALUES (318, '通信设备', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:54');
INSERT INTO `fams_industry` VALUES (319, '造纸', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:54');
INSERT INTO `fams_industry` VALUES (320, '酒店餐饮', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:54');
INSERT INTO `fams_industry` VALUES (321, '钢加工', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:54');
INSERT INTO `fams_industry` VALUES (322, '铁路', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');
INSERT INTO `fams_industry` VALUES (323, '铅锌', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');
INSERT INTO `fams_industry` VALUES (324, '铜', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');
INSERT INTO `fams_industry` VALUES (325, '铝', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');
INSERT INTO `fams_industry` VALUES (326, '银行', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');
INSERT INTO `fams_industry` VALUES (327, '陶瓷', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');
INSERT INTO `fams_industry` VALUES (328, '食品', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');
INSERT INTO `fams_industry` VALUES (329, '饲料', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');
INSERT INTO `fams_industry` VALUES (330, '黄金', 'Y', '20200309', '2020-04-05 19:04:39', '2020-03-09 23:22:55');

SET FOREIGN_KEY_CHECKS = 1;
