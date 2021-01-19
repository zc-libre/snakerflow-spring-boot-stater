/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : snaker

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 19/01/2021 15:13:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wf_cc_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_cc_order`;
CREATE TABLE `wf_cc_order`  (
                                `order_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例ID',
                                `actor_Id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参与者ID',
                                `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发起人',
                                `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抄送时间',
                                `finish_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '完成时间',
                                `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
                                INDEX `IDX_CCORDER_ORDER`(`order_Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抄送实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_cc_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_order`;
CREATE TABLE `wf_hist_order`  (
                                  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                                  `process_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程定义ID',
                                  `order_State` tinyint(1) NOT NULL COMMENT '状态',
                                  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发起人',
                                  `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发起时间',
                                  `end_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '完成时间',
                                  `expire_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '期望完成时间',
                                  `priority` tinyint(1) NULL DEFAULT NULL COMMENT '优先级',
                                  `parent_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父流程ID',
                                  `order_No` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例编号',
                                  `variable` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附属变量json存储',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  INDEX `IDX_HIST_ORDER_PROCESSID`(`process_Id`) USING BTREE,
                                  INDEX `IDX_HIST_ORDER_NO`(`order_No`) USING BTREE,
                                  INDEX `FK_HIST_ORDER_PARENTID`(`parent_Id`) USING BTREE,
                                  CONSTRAINT `FK_HIST_ORDER_PARENTID` FOREIGN KEY (`parent_Id`) REFERENCES `wf_hist_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                  CONSTRAINT `FK_HIST_ORDER_PROCESSID` FOREIGN KEY (`process_Id`) REFERENCES `wf_process` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '历史流程实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_hist_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task`;
CREATE TABLE `wf_hist_task`  (
                                 `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                                 `order_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程实例ID',
                                 `task_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
                                 `display_Name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务显示名称',
                                 `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
                                 `perform_Type` tinyint(1) NULL DEFAULT NULL COMMENT '参与类型',
                                 `task_State` tinyint(1) NOT NULL COMMENT '任务状态',
                                 `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理人',
                                 `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务创建时间',
                                 `finish_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务完成时间',
                                 `expire_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务期望完成时间',
                                 `action_Url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理url',
                                 `parent_Task_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父任务ID',
                                 `variable` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附属变量json存储',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `IDX_HIST_TASK_ORDER`(`order_Id`) USING BTREE,
                                 INDEX `IDX_HIST_TASK_TASKNAME`(`task_Name`) USING BTREE,
                                 INDEX `IDX_HIST_TASK_PARENTTASK`(`parent_Task_Id`) USING BTREE,
                                 CONSTRAINT `FK_HIST_TASK_ORDERID` FOREIGN KEY (`order_Id`) REFERENCES `wf_hist_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '历史任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_hist_task
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task_actor`;
CREATE TABLE `wf_hist_task_actor`  (
                                       `task_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务ID',
                                       `actor_Id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参与者ID',
                                       INDEX `IDX_HIST_TASKACTOR_TASK`(`task_Id`) USING BTREE,
                                       CONSTRAINT `FK_HIST_TASKACTOR` FOREIGN KEY (`task_Id`) REFERENCES `wf_hist_task` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '历史任务参与者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_hist_task_actor
-- ----------------------------

-- ----------------------------
-- Table structure for wf_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_order`;
CREATE TABLE `wf_order`  (
                             `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                             `parent_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父流程ID',
                             `process_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程定义ID',
                             `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发起人',
                             `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发起时间',
                             `expire_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '期望完成时间',
                             `last_Update_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次更新时间',
                             `last_Updator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次更新人',
                             `priority` tinyint(1) NULL DEFAULT NULL COMMENT '优先级',
                             `parent_Node_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父流程依赖的节点名称',
                             `order_No` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例编号',
                             `variable` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附属变量json存储',
                             `version` int(3) NULL DEFAULT NULL COMMENT '版本',
                             PRIMARY KEY (`id`) USING BTREE,
                             INDEX `IDX_ORDER_PROCESSID`(`process_Id`) USING BTREE,
                             INDEX `IDX_ORDER_NO`(`order_No`) USING BTREE,
                             INDEX `FK_ORDER_PARENTID`(`parent_Id`) USING BTREE,
                             CONSTRAINT `FK_ORDER_PARENTID` FOREIGN KEY (`parent_Id`) REFERENCES `wf_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                             CONSTRAINT `FK_ORDER_PROCESSID` FOREIGN KEY (`process_Id`) REFERENCES `wf_process` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_process
-- ----------------------------
DROP TABLE IF EXISTS `wf_process`;
CREATE TABLE `wf_process`  (
                               `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                               `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程名称',
                               `display_Name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程显示名称',
                               `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程类型',
                               `instance_Url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实例url',
                               `state` tinyint(1) NULL DEFAULT NULL COMMENT '流程是否可用',
                               `content` longblob NULL COMMENT '流程模型定义',
                               `version` int(2) NULL DEFAULT NULL COMMENT '版本',
                               `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
                               `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `IDX_PROCESS_NAME`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_process
-- ----------------------------

-- ----------------------------
-- Table structure for wf_surrogate
-- ----------------------------
DROP TABLE IF EXISTS `wf_surrogate`;
CREATE TABLE `wf_surrogate`  (
                                 `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                                 `process_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程名称',
                                 `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权人',
                                 `surrogate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理人',
                                 `odate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作时间',
                                 `sdate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开始时间',
                                 `edate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结束时间',
                                 `state` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `IDX_SURROGATE_OPERATOR`(`operator`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '委托代理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_surrogate
-- ----------------------------

-- ----------------------------
-- Table structure for wf_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_task`;
CREATE TABLE `wf_task`  (
                            `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
                            `order_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程实例ID',
                            `task_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
                            `display_Name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务显示名称',
                            `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
                            `perform_Type` tinyint(1) NULL DEFAULT NULL COMMENT '参与类型',
                            `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理人',
                            `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务创建时间',
                            `finish_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务完成时间',
                            `expire_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务期望完成时间',
                            `action_Url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理的url',
                            `parent_Task_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父任务ID',
                            `variable` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附属变量json存储',
                            `version` tinyint(1) NULL DEFAULT NULL COMMENT '版本',
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `IDX_TASK_ORDER`(`order_Id`) USING BTREE,
                            INDEX `IDX_TASK_TASKNAME`(`task_Name`) USING BTREE,
                            INDEX `IDX_TASK_PARENTTASK`(`parent_Task_Id`) USING BTREE,
                            CONSTRAINT `FK_TASK_ORDERID` FOREIGN KEY (`order_Id`) REFERENCES `wf_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_task
-- ----------------------------

-- ----------------------------
-- Table structure for wf_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_task_actor`;
CREATE TABLE `wf_task_actor`  (
                                  `task_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务ID',
                                  `actor_Id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参与者ID',
                                  INDEX `IDX_TASKACTOR_TASK`(`task_Id`) USING BTREE,
                                  CONSTRAINT `FK_TASK_ACTOR_TASKID` FOREIGN KEY (`task_Id`) REFERENCES `wf_task` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务参与者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_task_actor
-- ----------------------------

-- ----------------------------
-- Function structure for rand_num
-- ----------------------------
DROP FUNCTION IF EXISTS `rand_num`;
delimiter ;;
CREATE FUNCTION `rand_num`(from_num INT ,to_num INT)
    RETURNS int(11)
BEGIN
    DECLARE i INT DEFAULT 0;
    SET i = FLOOR(from_num +RAND()*(to_num -from_num+1)) ;
    RETURN i;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for rand_string
-- ----------------------------
DROP FUNCTION IF EXISTS `rand_string`;
delimiter ;;
CREATE FUNCTION `rand_string`(n INT)
    RETURNS varchar(255) CHARSET utf8mb4
BEGIN
    DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
    DECLARE return_str VARCHAR(255) DEFAULT '';
    DECLARE i INT DEFAULT 0;
    WHILE i < n DO
            SET return_str =CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1)); SET i = i + 1;
        END WHILE;
    RETURN return_str;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
