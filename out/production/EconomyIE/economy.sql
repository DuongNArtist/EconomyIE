/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 100109
Source Host           : localhost:3306
Source Database       : economy

Target Server Type    : MYSQL
Target Server Version : 100109
File Encoding         : 65001

Date: 2016-03-20 15:02:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for docs
-- ----------------------------
DROP TABLE IF EXISTS `docs`;
CREATE TABLE `docs` (
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_name` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of docs
-- ----------------------------
INSERT INTO `docs` VALUES ('18', 'Những nhóm mặt hàng nhập khẩu chính tháng 1-2016');
INSERT INTO `docs` VALUES ('19', 'Những nhóm hàng nhập khẩu chính năm 2015');
INSERT INTO `docs` VALUES ('20', '“Soi” top 10 mặt hàng xuất nhập khẩu lớn nhất 7 tháng');

-- ----------------------------
-- Table structure for ents
-- ----------------------------
DROP TABLE IF EXISTS `ents`;
CREATE TABLE `ents` (
  `ent_id` int(11) NOT NULL AUTO_INCREMENT,
  `ent_name` text COLLATE utf8_unicode_ci,
  `ent_owner` text COLLATE utf8_unicode_ci,
  `ent_address` text COLLATE utf8_unicode_ci,
  `ent_product` text COLLATE utf8_unicode_ci,
  `ent_profit` text COLLATE utf8_unicode_ci,
  `ent_export` text COLLATE utf8_unicode_ci,
  `ent_import` text COLLATE utf8_unicode_ci,
  `doc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=865 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ents
-- ----------------------------
INSERT INTO `ents` VALUES ('620', '', '', '', '', '25,96 tỷ USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('621', '', '', '', '', '12,6 tỷ USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('622', '', '', '', '', '12,6 tỷ USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('623', '', '', '', 'thiết bị, ', '2,14 tỷ USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('624', '', '', '', 'máy móc, thiết bị, ', '769 triệu USD, 376,7 triệu USD, 303,7 triệu USD, 129,5 tỷ USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('625', '', '', '', '', '1,97 tỷ USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('626', '', '', '', '', '564 triệu USD, 439 triệu USD, 217 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('627', '', '', '', 'giày, giày, ', '1,4 tỷ USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('628', '', '', '', '', '612 triệu USD, 204 triệu USD, 159 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('629', '', '', '', '', '744,9 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('630', '', '', '', '', '506 triệu USD, 201 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('631', '', '', '', '', '554,6 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('632', '', '', '', '', '444 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('633', '', '', '', '', '356 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('634', '', '', '', '', '314,6 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('635', '', '', '', '', '110 triệu USD, 89,4 triệu USD, 43,6 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('636', '', '', '', 'hóa chất, ', '278 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('637', '', '', '', 'hóa chất, ', '69 triệu USD, 41 triệu USD, 34 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('638', '', '', '', '', '251,6 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('639', '', '', '', 'xăng dầu các loại, ', '', '', '', '18');
INSERT INTO `ents` VALUES ('640', '', '', '', '', '9,1 triệu USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('641', '', '', '', '', '33,38 tỷ USD, ', '', '', '18');
INSERT INTO `ents` VALUES ('642', '', 'Nguyễn Mạnh Hùng, ', '', '', '', '', '', '18');
INSERT INTO `ents` VALUES ('643', '', '', '', '', '327,76 tỷ USD, 165,65 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('644', '', '', '', 'thiết bị, ', '2,52 tỷ USD, 27,59 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('645', '', '', '', '', '9,03 tỷ USD, 5,12 tỷ USD, 4,51 tỷ USD, 1,46 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('646', '', '', '', '', '1,76 tỷ USD, 23,13 tỷ USD, 21,19 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('647', '', '', '', '', '6,73 tỷ USD, 5,21 tỷ USD, 2,27 tỷ USD, 2,19 tỷ USD, 1,77 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('648', '', '', '', '', '647,5 triệu USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('649', '', '', '', '', '10,6 tỷ USD, 9,27 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('650', '', '', '', '', '6,9 tỷ USD, 3,02 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('651', '', '', '', '', '1,78 triệu USD, 667,86 triệu USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('652', '', '', '', 'sắt thép các loại, ', '7,49 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('653', '', '', '', '', '277 triệu USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('654', '', '', '', '', '3,81 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('655', '', '', '', '', '1,32 tỷ USD, 1,02 tỷ USD, 430 triệu USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('656', '', '', '', '', '5,36 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('657', '', '', '', '', '521 triệu USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('658', '', '', '', 'chất dẻo nguyên liệu, ', '5,96 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('659', '', '', '', '', '336,86 triệu USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('660', '', '', '', '', '3,76 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('661', '', '', '', 'giày, ', '1,15 tỷ USD, 1,07 tỷ USD, 635 triệu USD, 1,5 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('662', '', '', '', '', '18,3 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('663', '', '', '', '', '7,62 tỷ USD, 2,82 tỷ USD, 2,33 tỷ USD, 1,08 tỷ  USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('664', '', '', '', '', '337 triệu USD, 3,39 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('665', '', '', '', 'thức ăn gia súc và nguyên liệu, ', '1,44 tỷ USD, 429 triệu USD, 306 triệu USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('666', '', '', '', 'ô tô nguyên chiếc, ', '2,99 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('667', '', '', '', 'ô tô nguyên chiếc, ', '', '', '', '19');
INSERT INTO `ents` VALUES ('668', '', '', '', 'ô tô nguyên chiếc, ', '', '', '', '19');
INSERT INTO `ents` VALUES ('669', '', '', '', '', '9,1 triệu USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('670', '', '', '', '', '33,38 tỷ USD, ', '', '', '19');
INSERT INTO `ents` VALUES ('671', '', 'Nguyễn Mạnh Hùng, ', '', '', '', '', '', '19');
INSERT INTO `ents` VALUES ('832', '', 'Đinh La Thăng, ', 'Đồng Nai, ', 'Cao su, ', '', '', '', '20');
INSERT INTO `ents` VALUES ('833', '', '', 'Hà Nội, ', '', '', '', '', '20');
INSERT INTO `ents` VALUES ('834', '', 'Đinh La Thăng, ', '', '', '', '', '', '20');
INSERT INTO `ents` VALUES ('835', '', '', '', 'máy móc, thiết bị, ', '', '', '', '20');
INSERT INTO `ents` VALUES ('836', '', '', '', '', '29,3 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('837', '', '', '', '', '187,9 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('838', '', '', '', '', '300 triệu USD, 3,37 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('839', '', '', '', '', '92,3 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('840', '', '', '', '', '2,5 tỷ USD, 17,1 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('841', '', '', '', '', '2,3 tỷ USD, 12,56 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('842', '', '', '', 'giày, ', '1,2 tỷ USD, 7,05 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('843', '', '', '', 'Máy vi tính, sản phẩm điện tử và linh kiện, ', '1,35 tỷ USD, 8,71 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('844', '', '', '', '', '310 triệu USD, 2,45 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('845', '', '', '', 'thủy sản, ', '620 triệu USD, 3,62 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('846', '', '', '', 'thiết bị, ', '680 triệu USD, 4,49 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('847', '', '', '', 'Phương tiện vận tải và phụ tùng, ', '450 triệu USD, 3,11 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('848', '', '', '', 'Gỗ, ', '600 triệu USD, 3,77 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('849', '', '', '', '', '236 triệu USD, 1,65 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('850', '', '', '', '', '95,6 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('851', '', '', '', 'thiết bị, ', '2,6 tỷ USD, 16,56 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('852', '', '', '', '', '1,9 tỷ USD, 13,09 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('853', '', '', '', 'Vải các loại, ', '900 triệu USD, 5,88 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('854', '', '', '', 'Xăng dầu, ', '492triệu USD, 3,4 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('855', '', '', '', 'Điện thoại các loại và linh kiện, ', '900 triệu USD, 6,12 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('856', '', '', '', 'Sắt thép, ', '792 triệu USD, 4,61 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('857', '', '', '', '', '531 triệu USD, 3,35 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('858', '', '', '', 'giày, ', '460 triệu USD, 2,96 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('859', '', '', '', '', '369 triệu USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('860', '', '', '', 'Hóa chất, ', '300 triệu USD, 1,88 tỷ USD, ', '', '', '20');
INSERT INTO `ents` VALUES ('861', 'Công ty Cổ, ', '', '', '', '', '', '', '20');
INSERT INTO `ents` VALUES ('862', '', '', 'Hà Nội, ', '', '', '', '', '20');
INSERT INTO `ents` VALUES ('863', '', '', 'Hà Nội, ', '', '', '', '', '20');
INSERT INTO `ents` VALUES ('864', '', '', 'Hà Nội, ', '', '', '', '', '20');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- Procedure structure for delete_doc
-- ----------------------------
DROP PROCEDURE IF EXISTS `delete_doc`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_doc`(IN `doc_id` INT)
BEGIN
	#Routine body goes here...
	DELETE FROM docs
	WHERE docs.doc_id = doc_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for delete_ent
-- ----------------------------
DROP PROCEDURE IF EXISTS `delete_ent`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_ent`(IN `ent_id` INT)
BEGIN
	#Routine body goes here...
	DELETE FROM ents
	WHERE ents.ent_id = ent_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for delete_ent_by_doc_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `delete_ent_by_doc_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_ent_by_doc_id`(IN `doc_id` INT)
BEGIN
	#Routine body goes here...
	DELETE FROM ents
	WHERE ents.doc_id = doc_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insert_doc
-- ----------------------------
DROP PROCEDURE IF EXISTS `insert_doc`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_doc`(IN `doc_name` TEXT)
BEGIN
	#Routine body goes here...
	INSERT INTO docs(doc_name) VALUES(doc_name);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insert_ent
-- ----------------------------
DROP PROCEDURE IF EXISTS `insert_ent`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_ent`(IN `doc_id` INT, IN `ent_name` TEXT, IN `ent_owner` TEXT, IN `ent_address` TEXT, IN `ent_product` TEXT, IN `ent_profit` TEXT)
BEGIN
	#Routine body goes here...
	INSERT INTO ents(ent_name, ent_owner, ent_address, ent_product, ent_profit, doc_id) VALUES(ent_name, ent_owner, ent_address, ent_product, ent_profit, doc_id);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for select_doc
-- ----------------------------
DROP PROCEDURE IF EXISTS `select_doc`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `select_doc`(IN `keyword` TEXT, IN `start` INT, IN `end` INT)
BEGIN
	#Routine body goes here...
	SELECT * FROM docs WHERE
	docs.doc_id LIKE keyword OR
	docs.doc_name LIKE keyword
	ORDER BY docs.doc_id DESC
	LIMIT start, end;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for select_ent
-- ----------------------------
DROP PROCEDURE IF EXISTS `select_ent`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `select_ent`(IN `keyword` TEXT, IN `start` INT, IN `end` INT)
BEGIN
	#Routine body goes here...
	SELECT * FROM ents WHERE
	ents.doc_id like keyword
	ORDER BY ents.ent_id ASC
	LIMIT start, end;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for update_doc
-- ----------------------------
DROP PROCEDURE IF EXISTS `update_doc`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_doc`(IN `doc_id` INT, IN `doc_name` TEXT)
BEGIN
	#Routine body goes here...
	UPDATE docs SET
	docs.doc_name = doc_name
	WHERE docs.doc_id = doc_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for update_ent
-- ----------------------------
DROP PROCEDURE IF EXISTS `update_ent`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_ent`(IN `ent_id` INT, IN `ent_name` TEXT, IN `ent_owner` TEXT, IN `ent_address` TEXT, IN `ent_product` TEXT, IN `ent_profit` TEXT)
BEGIN
	#Routine body goes here...
	UPDATE ents SET
	ents.ent_name = ent_name,
	ents.ent_owner = ent_owner,
	ents.ent_address = ent_address,
	ents.ent_product = ent_product,
	ents.ent_profit = ent_profit
	WHERE ents.ent_id = ent_id;
END
;;
DELIMITER ;
