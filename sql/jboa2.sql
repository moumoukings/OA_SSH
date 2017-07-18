/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : jboa2

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-07-18 10:20:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dept_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '产品研发');
INSERT INTO `department` VALUES ('2', '市场部');

-- ----------------------------
-- Table structure for details
-- ----------------------------
DROP TABLE IF EXISTS `details`;
CREATE TABLE `details` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `remarks` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `FK5CD8F24274CCCB7D` (`voucher_id`),
  CONSTRAINT `FK5CD8F24274CCCB7D` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`voucher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of details
-- ----------------------------

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(40) DEFAULT NULL,
  `dept` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phoneNum` varchar(12) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `state` int(4) DEFAULT '1',
  PRIMARY KEY (`emp_id`),
  KEY `fk_emp_pos` (`role`),
  KEY `fk_emp_dept` (`dept`),
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`dept`) REFERENCES `department` (`dept_id`),
  CONSTRAINT `fk_emp_role` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'e', '1234', '1', '1', '21', '13455667788', '就在路', '男', '1');
INSERT INTO `employee` VALUES ('2', 'm', '1234', '1', '2', '13', '15544334455', '留在路', '男', '1');
INSERT INTO `employee` VALUES ('3', 'mm', '12345', '1', '3', '35', '13344555578', '望江路', '女', '1');
INSERT INTO `employee` VALUES ('4', 'f', '1234', '1', '4', '15', '55544477766', '全路', '女', '1');
INSERT INTO `employee` VALUES ('5', 'nm1', '1234', '2', '2', '23', '13344455554', '订单', '女', '1');
INSERT INTO `employee` VALUES ('6', 'mm1', '1234', '1', '2', '23', '13355667788', '长江路', '女', '1');
INSERT INTO `employee` VALUES ('7', 'mm3', '1234', '1', '1', '23', '15544332233', '天书了', '男', '1');
INSERT INTO `employee` VALUES ('8', 'e1', '1234', '1', '1', '24', '15522334455', '霍山路', '男', '1');
INSERT INTO `employee` VALUES ('9', 'e3', '1234', '2', '2', '34', '13344556677', 'ddd', '男', '1');

-- ----------------------------
-- Table structure for leave_l
-- ----------------------------
DROP TABLE IF EXISTS `leave_l`;
CREATE TABLE `leave_l` (
  `vacation_id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` int(11) DEFAULT NULL,
  `l_resutl_id` int(11) DEFAULT NULL,
  `create_Time` datetime DEFAULT NULL,
  `start_Date` date DEFAULT NULL,
  `end_Date` date DEFAULT NULL,
  `event` varchar(200) DEFAULT NULL,
  `leaveType` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`vacation_id`),
  KEY `FK_emp_leave` (`creator`) USING BTREE,
  KEY `FK_leave_has` (`l_resutl_id`) USING BTREE,
  CONSTRAINT `fk_leave_create` FOREIGN KEY (`creator`) REFERENCES `employee` (`emp_id`),
  CONSTRAINT `FK_leave_has` FOREIGN KEY (`l_resutl_id`) REFERENCES `leave_result` (`l_resutl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_l
-- ----------------------------
INSERT INTO `leave_l` VALUES ('1', '1', '1', '2017-07-11 15:44:37', '2017-07-12', '2017-07-12', '真的有事', '1', '2');
INSERT INTO `leave_l` VALUES ('7', '1', null, '2017-06-14 09:33:28', '2017-06-16', '2017-06-21', 'kk', '2', '2');
INSERT INTO `leave_l` VALUES ('8', '1', '14', '2017-07-12 12:28:49', '2017-07-13', '2017-07-14', 'ss', '2', '2');
INSERT INTO `leave_l` VALUES ('9', '2', null, '2017-07-13 11:08:11', '2017-07-14', '2017-07-21', 'ceshi', '0', '1');
INSERT INTO `leave_l` VALUES ('10', '2', null, '2017-07-13 13:30:50', '2017-07-14', '2017-07-20', 'lot thing todo', '0', '1');
INSERT INTO `leave_l` VALUES ('13', '1', null, '2017-07-14 19:49:42', '2017-07-15', '2017-07-19', 'tired', '0', '1');
INSERT INTO `leave_l` VALUES ('14', '1', '24', '2017-07-15 11:04:29', '2017-07-15', '2017-07-19', 'mmm', '0', '1');
INSERT INTO `leave_l` VALUES ('15', '2', '28', '2017-07-15 11:14:10', '2017-07-16', '2017-07-19', 'vacation', '2', '1');
INSERT INTO `leave_l` VALUES ('16', '2', '21', '2017-07-15 11:14:20', '2017-07-19', '2017-07-27', 'ddddddd', '0', '0');
INSERT INTO `leave_l` VALUES ('17', '1', '22', '2017-07-15 11:15:03', '2017-07-16', '2017-07-20', 'fdfff', '0', '1');
INSERT INTO `leave_l` VALUES ('18', '1', '25', '2017-07-17 08:40:34', '2017-07-18', '2017-07-28', 'aaasssdd', '0', '0');
INSERT INTO `leave_l` VALUES ('19', '2', '27', '2017-07-17 08:44:02', '2017-07-27', '2017-07-28', 'ffff', '0', '0');
INSERT INTO `leave_l` VALUES ('20', '2', '26', '2017-07-17 08:45:04', '2017-07-18', '2017-07-27', 'eeee', '0', '0');

-- ----------------------------
-- Table structure for leave_result
-- ----------------------------
DROP TABLE IF EXISTS `leave_result`;
CREATE TABLE `leave_result` (
  `l_resutl_id` int(11) NOT NULL AUTO_INCREMENT,
  `approver` int(11) NOT NULL,
  `comment` varchar(200) NOT NULL,
  `approval_Time` datetime NOT NULL,
  `result` int(11) NOT NULL,
  PRIMARY KEY (`l_resutl_id`),
  KEY `FK_approve_leave` (`approver`),
  CONSTRAINT `fk_leave_approver` FOREIGN KEY (`approver`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_result
-- ----------------------------
INSERT INTO `leave_result` VALUES ('1', '2', '同意', '2017-07-12 17:46:54', '0');
INSERT INTO `leave_result` VALUES ('2', '2', '同意', '2017-07-12 11:16:45', '1');
INSERT INTO `leave_result` VALUES ('9', '2', 'dd', '2017-07-13 01:04:20', '0');
INSERT INTO `leave_result` VALUES ('10', '2', 'gggg', '2017-07-13 01:10:53', '0');
INSERT INTO `leave_result` VALUES ('11', '2', 'agree', '2017-07-13 11:08:41', '1');
INSERT INTO `leave_result` VALUES ('13', '2', 'I aggree', '2017-07-13 11:51:02', '1');
INSERT INTO `leave_result` VALUES ('14', '2', 'butongyi', '2017-07-13 11:52:21', '0');
INSERT INTO `leave_result` VALUES ('15', '2', 'work hard', '2017-07-13 13:29:52', '0');
INSERT INTO `leave_result` VALUES ('17', '2', 'aggree', '2017-07-15 11:09:06', '1');
INSERT INTO `leave_result` VALUES ('18', '2', 'im change', '2017-07-15 11:09:28', '0');
INSERT INTO `leave_result` VALUES ('19', '2', 'im change', '2017-07-15 11:12:39', '1');
INSERT INTO `leave_result` VALUES ('20', '3', 'good', '2017-07-15 11:15:37', '1');
INSERT INTO `leave_result` VALUES ('21', '3', 'not ok', '2017-07-15 11:15:45', '0');
INSERT INTO `leave_result` VALUES ('22', '2', 'gggg', '2017-07-15 11:20:08', '1');
INSERT INTO `leave_result` VALUES ('23', '2', 'im change', '2017-07-17 08:42:52', '0');
INSERT INTO `leave_result` VALUES ('24', '2', 'im change', '2017-07-17 08:43:04', '1');
INSERT INTO `leave_result` VALUES ('25', '2', 'dsddddfff', '2017-07-17 08:43:15', '1');
INSERT INTO `leave_result` VALUES ('26', '3', '213123', '2017-07-17 08:45:21', '1');
INSERT INTO `leave_result` VALUES ('27', '3', '234324324242', '2017-07-17 08:45:27', '1');
INSERT INTO `leave_result` VALUES ('28', '3', '3333333', '2017-07-17 08:45:48', '0');

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `mail_id` int(11) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `receive_state` int(2) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `send_state` int(2) DEFAULT NULL,
  PRIMARY KEY (`mail_id`),
  KEY `fk_mailContent` (`content_id`),
  KEY `fk_sender` (`sender`),
  KEY `fk_receiver` (`receiver`),
  CONSTRAINT `fk_mailContent` FOREIGN KEY (`content_id`) REFERENCES `mail_content` (`content_id`),
  CONSTRAINT `fk_receiver` FOREIGN KEY (`receiver`) REFERENCES `employee` (`emp_id`),
  CONSTRAINT `fk_sender` FOREIGN KEY (`sender`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mail
-- ----------------------------
INSERT INTO `mail` VALUES ('1', '2', '2017-07-14 21:38:37', '1', '1', '9', 'titlwe', '4');
INSERT INTO `mail` VALUES ('2', '3', '2017-07-14 23:14:36', '1', '9', '1', 'tttt', '4');
INSERT INTO `mail` VALUES ('3', '4', '2017-07-14 23:35:12', '3', '9', '1', 'feedback', '4');
INSERT INTO `mail` VALUES ('4', '5', '2017-07-15 00:49:59', '1', '1', '8', 'test1', '4');
INSERT INTO `mail` VALUES ('5', '6', '2017-07-15 00:51:01', '1', '8', '1', 'hhdd', '4');
INSERT INTO `mail` VALUES ('6', '7', '2017-07-15 11:20:56', '2', '2', '3', 'gooddmm', '4');
INSERT INTO `mail` VALUES ('7', '8', '2017-07-15 11:21:54', '1', '2', '3', 'tttttggg', '4');
INSERT INTO `mail` VALUES ('8', '9', '2017-07-15 11:23:22', '1', '3', '2', 'ooookkkk', '4');
INSERT INTO `mail` VALUES ('9', '10', '2017-07-17 08:38:49', '1', '1', '2', '1234', '4');
INSERT INTO `mail` VALUES ('10', '11', '2017-07-17 09:16:14', '1', '1', '2', 'asdfasdfasdf', '4');
INSERT INTO `mail` VALUES ('11', '12', '2017-07-17 09:16:23', '0', '1', '2', 'dfsdfsdf', '4');
INSERT INTO `mail` VALUES ('12', '13', '2017-07-17 09:16:40', '0', '1', '2', 'sdfds', '4');

-- ----------------------------
-- Table structure for mail_content
-- ----------------------------
DROP TABLE IF EXISTS `mail_content`;
CREATE TABLE `mail_content` (
  `content_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `attach_file` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mail_content
-- ----------------------------
INSERT INTO `mail_content` VALUES ('2', 'hello', 'b6884afb1a76435e8fcae408d522c200.txt');
INSERT INTO `mail_content` VALUES ('3', 'ttddddddddddddddddddddddddddssssssssssssss', 'ae72f8a4caa0490bb1bb9cda2acbb434.txt');
INSERT INTO `mail_content` VALUES ('4', 'hello world', null);
INSERT INTO `mail_content` VALUES ('5', 'test22', null);
INSERT INTO `mail_content` VALUES ('6', 'teeeeedddxxxxxxx', '0a0b204b200d4ba2910167abcbf49e9b.txt');
INSERT INTO `mail_content` VALUES ('7', 'ccccffffggggghhhbbbbnnn', '11343ba57d204d7f8d1e60b2ef978804.txt');
INSERT INTO `mail_content` VALUES ('8', 'dddeeellleeeetttee', 'b6791e108c7544ae9bddc2f5786d332b.txt');
INSERT INTO `mail_content` VALUES ('9', 'ggggggggoooooootttttttttttiiiiiiitttttttttttttt', '85a885b0de1a4b45a6f975150ff241d9.log');
INSERT INTO `mail_content` VALUES ('10', '1234567890', '1bfbefba619a4e1fbb9ff006152f4caf.html');
INSERT INTO `mail_content` VALUES ('11', 'asdfasdfasdfasdfsd', null);
INSERT INTO `mail_content` VALUES ('12', 'sdfsadfasdfwerqwrqwer', null);
INSERT INTO `mail_content` VALUES ('13', 'fdsfsdfsdfds', null);

-- ----------------------------
-- Table structure for right_r
-- ----------------------------
DROP TABLE IF EXISTS `right_r`;
CREATE TABLE `right_r` (
  `right_code` varchar(20) COLLATE utf8_bin NOT NULL,
  `right_parent_code` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `right_title` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `right_url` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`right_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of right_r
-- ----------------------------
INSERT INTO `right_r` VALUES ('1001', 'ROOT_MENU', '信息管理', null);
INSERT INTO `right_r` VALUES ('100101', '1001', '个人信息', 'employee/check_init.action');
INSERT INTO `right_r` VALUES ('100102', '1001', '修改密码', 'jsp/others/alterPwd.jsp');
INSERT INTO `right_r` VALUES ('1002', 'ROOT_MENU', '邮件管理', null);
INSERT INTO `right_r` VALUES ('100201', '1002', '写邮件', 'jsp/mail/add.jsp');
INSERT INTO `right_r` VALUES ('100202', '1002', '收件箱', '');
INSERT INTO `right_r` VALUES ('10020201', '100202', '已读', 'mail/readedList_init.action');
INSERT INTO `right_r` VALUES ('10020202', '100202', '未读', 'mail/unreadList_init.action');
INSERT INTO `right_r` VALUES ('100203', '1002', '发件箱', 'mail/sendList_init.action');
INSERT INTO `right_r` VALUES ('100204', '1002', '垃圾箱', 'mail/deletedList_init.action');
INSERT INTO `right_r` VALUES ('1003', 'ROOT_MENU', '考勤管理', null);
INSERT INTO `right_r` VALUES ('100301', '10031', '申请休假', 'leave/add_init.action');
INSERT INTO `right_r` VALUES ('100302', '10031', '休假记录', 'leave/applyList_init.action');
INSERT INTO `right_r` VALUES ('100303', '10032', '审批休假', 'leave/approveList_init.action');
INSERT INTO `right_r` VALUES ('100304', '10032', '正在休假', 'leave/leavingList_init.action');
INSERT INTO `right_r` VALUES ('100305', '10032', '已审批', 'leave/approvedList_init.action');
INSERT INTO `right_r` VALUES ('10031', '1003', '个人考勤', null);
INSERT INTO `right_r` VALUES ('10032', '1003', '考勤审核', null);
INSERT INTO `right_r` VALUES ('1004', 'ROOT_MENU', '用户管理', 'employee/list_init.action');
INSERT INTO `right_r` VALUES ('1005', 'ROOT_MENU', '报销单管理', null);
INSERT INTO `right_r` VALUES ('100501', '1005', '新建报销单', 'voucher/addInit');
INSERT INTO `right_r` VALUES ('100502', '1005', '未提交', 'voucher/uncommitedInit');
INSERT INTO `right_r` VALUES ('100503', '1005', '已提交', 'voucher/commitedInit');
INSERT INTO `right_r` VALUES ('100504', '1005', '未审批', 'voucher/unapproveInit');
INSERT INTO `right_r` VALUES ('100505', '1005', '已审批', 'voucher/approveInit');
INSERT INTO `right_r` VALUES ('100506', '1005', '待付款', 'voucher/unpaidInit');
INSERT INTO `right_r` VALUES ('100507', '1005', '已付款', 'voucher/paidInit');
INSERT INTO `right_r` VALUES ('1006', 'ROOT_MENU', '退出系统', 'exitSystem.action');
INSERT INTO `right_r` VALUES ('temp', 'dd', '', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('-1', '公共');
INSERT INTO `role` VALUES ('1', '员工');
INSERT INTO `role` VALUES ('2', '部门经理');
INSERT INTO `role` VALUES ('3', '经理');
INSERT INTO `role` VALUES ('4', '财务');

-- ----------------------------
-- Table structure for role_right
-- ----------------------------
DROP TABLE IF EXISTS `role_right`;
CREATE TABLE `role_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `right_code` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_right_role` (`role_id`),
  KEY `fk_role_right_right` (`right_code`),
  CONSTRAINT `fk_role_right_right` FOREIGN KEY (`right_code`) REFERENCES `right_r` (`right_code`),
  CONSTRAINT `fk_role_right_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role_right
-- ----------------------------
INSERT INTO `role_right` VALUES ('1', '100101', '-1');
INSERT INTO `role_right` VALUES ('2', '100102', '-1');
INSERT INTO `role_right` VALUES ('3', '100201', '-1');
INSERT INTO `role_right` VALUES ('4', '100202', '-1');
INSERT INTO `role_right` VALUES ('5', '100203', '-1');
INSERT INTO `role_right` VALUES ('6', '100301', '1');
INSERT INTO `role_right` VALUES ('7', '100302', '4');
INSERT INTO `role_right` VALUES ('10', '100302', '1');
INSERT INTO `role_right` VALUES ('11', '1004', '3');
INSERT INTO `role_right` VALUES ('20', '1001', '-1');
INSERT INTO `role_right` VALUES ('21', '1002', '-1');
INSERT INTO `role_right` VALUES ('22', '1003', '-1');
INSERT INTO `role_right` VALUES ('25', '100304', '2');
INSERT INTO `role_right` VALUES ('26', '100304', '3');
INSERT INTO `role_right` VALUES ('27', '100302', '2');
INSERT INTO `role_right` VALUES ('29', '100303', '2');
INSERT INTO `role_right` VALUES ('30', '100303', '4');
INSERT INTO `role_right` VALUES ('31', '100301', '2');
INSERT INTO `role_right` VALUES ('32', '100301', '4');
INSERT INTO `role_right` VALUES ('33', '100305', '2');
INSERT INTO `role_right` VALUES ('34', '100305', '3');
INSERT INTO `role_right` VALUES ('35', '10031', '2');
INSERT INTO `role_right` VALUES ('36', '10031', '4');
INSERT INTO `role_right` VALUES ('37', '10031', '1');
INSERT INTO `role_right` VALUES ('38', '10032', '2');
INSERT INTO `role_right` VALUES ('39', '10032', '3');
INSERT INTO `role_right` VALUES ('41', '100303', '3');
INSERT INTO `role_right` VALUES ('42', '10020201', '-1');
INSERT INTO `role_right` VALUES ('43', '10020202', '-1');
INSERT INTO `role_right` VALUES ('44', '100204', '-1');
INSERT INTO `role_right` VALUES ('45', '1006', '-1');

-- ----------------------------
-- Table structure for voucher
-- ----------------------------
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE `voucher` (
  `voucher_id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` int(11) DEFAULT NULL,
  `create_Time` datetime DEFAULT NULL,
  `totalCost` int(11) DEFAULT NULL,
  `event` varchar(200) NOT NULL,
  `state` int(11) NOT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`voucher_id`),
  KEY `FK_emp_voucher` (`creator`),
  KEY `FK26288EAED2A1CA7D` (`emp_id`),
  CONSTRAINT `FK26288EAED2A1CA7D` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`),
  CONSTRAINT `fk_voucher_create` FOREIGN KEY (`creator`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of voucher
-- ----------------------------
INSERT INTO `voucher` VALUES ('1', '1', '2017-07-05 11:04:31', '3000', '时间1', '1', null);
INSERT INTO `voucher` VALUES ('2', '1', '2017-07-04 11:06:08', '3000', '时间2', '2', null);
INSERT INTO `voucher` VALUES ('3', '1', '2017-07-05 11:06:50', '5001', '等待经理审核', '1', null);
INSERT INTO `voucher` VALUES ('4', '1', '2017-07-05 11:08:25', '6000', '部门经理拒绝', '4', null);
INSERT INTO `voucher` VALUES ('5', '1', '2017-07-05 11:09:59', '8000', '经理拒绝', '4', null);
INSERT INTO `voucher` VALUES ('6', '1', '2017-07-25 11:12:14', '7000', '部门经理尚未审批', '1', null);

-- ----------------------------
-- Table structure for voucher_details
-- ----------------------------
DROP TABLE IF EXISTS `voucher_details`;
CREATE TABLE `voucher_details` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `remarks` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `FK_voucher_detail` (`voucher_id`),
  CONSTRAINT `FK_voucher_detail` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`voucher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of voucher_details
-- ----------------------------

-- ----------------------------
-- Table structure for voucher_result
-- ----------------------------
DROP TABLE IF EXISTS `voucher_result`;
CREATE TABLE `voucher_result` (
  `v_result_id` int(11) NOT NULL AUTO_INCREMENT,
  `approver` int(11) DEFAULT NULL,
  `voucher_id` int(11) DEFAULT NULL,
  `comment` varchar(200) NOT NULL,
  `approval_Time` datetime NOT NULL,
  `result` int(11) NOT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`v_result_id`),
  KEY `FK_voucher_approve` (`approver`),
  KEY `FK_voucher_result` (`voucher_id`),
  KEY `FK885F6CED2A1CA7D` (`emp_id`),
  CONSTRAINT `FK885F6CED2A1CA7D` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`),
  CONSTRAINT `fk_voucher_approver` FOREIGN KEY (`approver`) REFERENCES `employee` (`emp_id`),
  CONSTRAINT `FK_voucher_result` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`voucher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of voucher_result
-- ----------------------------
INSERT INTO `voucher_result` VALUES ('1', '2', '3', '通过', '2017-07-05 11:07:35', '1', null);
INSERT INTO `voucher_result` VALUES ('2', '2', '4', '拒绝', '2017-07-05 11:09:19', '0', null);
INSERT INTO `voucher_result` VALUES ('3', '2', '5', '通过', '2017-07-05 11:10:42', '1', null);
INSERT INTO `voucher_result` VALUES ('4', '3', '5', '拒绝', '2017-07-05 11:10:59', '0', null);

-- ----------------------------
-- Procedure structure for proUpdateLeaveState
-- ----------------------------
DROP PROCEDURE IF EXISTS `proUpdateLeaveState`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proUpdateLeaveState`()
    MODIFIES SQL DATA
BEGIN

SET @SQL2 = 'update leave_l set state = 1
							where state=0 and year(start_date) = year(CURRENT_date) and 
							DAYOFYEAR(start_date) <= DAYOFYEAR(CURRENT_date) 
							or year(start_Date) < YEAR(CURRENT_DATE)';
PREPARE stmt2
FROM
	@SQL2;
EXECUTE stmt2;

SET @SQL = 'update leave_l set state = 2
							where state=1 and year(end_date) = year(CURRENT_date) and 
							DAYOFYEAR(end_date) < DAYOFYEAR(CURRENT_date) 
							or year(end_Date) < YEAR(CURRENT_DATE)';
PREPARE stmt1
FROM
	@SQL;
EXECUTE stmt1;
END
;;
DELIMITER ;

-- ----------------------------
-- Event structure for clockUpdateState
-- ----------------------------
DROP EVENT IF EXISTS `clockUpdateState`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `clockUpdateState` ON SCHEDULE AT '2017-07-13 00:00:00' ON COMPLETION PRESERVE DISABLE DO CALL proUpdateLeaveState
;;
DELIMITER ;
