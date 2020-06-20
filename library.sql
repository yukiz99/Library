/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-06-12 18:22:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `publishPlace` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('A10', '莫言小说集', '莫言', '未评级', '中国', '9787532146758', '700');
INSERT INTO `book` VALUES ('A11', '孩子你慢慢来', '龙应台', '未评级', '中国', '9787108033635', '138');
INSERT INTO `book` VALUES ('A12', '野火集', '龙应台', '未评级', '中国', '9787806767245', '236');
INSERT INTO `book` VALUES ('A13', '小王子', ' 安托万·德·圣埃克苏佩里', '未评级', '法国', '9787535055736', '144');
INSERT INTO `book` VALUES ('A15', '呐喊', '鲁迅', '未评级', '中国', '9787201070339', '130');
INSERT INTO `book` VALUES ('A16', '狂人日记', '鲁迅', '未评级', '中国', '9787544369480', '288');
INSERT INTO `book` VALUES ('A18', '钢铁是怎样炼成的', '奥斯特洛夫斯基', '未评级', '长江', '9787535497239', '200');
INSERT INTO `book` VALUES ('A19', '草房子', '曹文轩', '未评级', '江苏', '9787534618727', '300');
INSERT INTO `book` VALUES ('A20', '红瓦黑瓦', '曹文轩', '未评级', '江苏', '9787534633041', '350');
INSERT INTO `book` VALUES ('A22', '乖，摸摸头', '大冰', '未评级', '中国', '9787540468798', '336');
INSERT INTO `book` VALUES ('A23', '老人与海', '海明威', '未评级', '美国', '9787512508712', '162');
INSERT INTO `book` VALUES ('A24', '我们仨', '杨绛', '未评级', '中国', '9787108021441', '165');
INSERT INTO `book` VALUES ('A25', '追风筝的人', '卡勒德·胡赛尼', '未评级', '美国', '9787208061644', '180');
INSERT INTO `book` VALUES ('A26', '看见', '柴静', '未评级', '中国', '9787549529322', '200');
INSERT INTO `book` VALUES ('A27', '洗澡', '杨绛', '未评级', '中国', '9787020090501', '200');
INSERT INTO `book` VALUES ('A28', '这些人，那些事', '吴念真', '未评级', '中国', '9787544717731', '200');
INSERT INTO `book` VALUES ('A29', '目送', '龙应台 ', '未评级', '中国', '9787108032911', '281');
INSERT INTO `book` VALUES ('A3', '月亮和六便士', '毛姆', '未评级', '英国', '9787540484743 ', '336');
INSERT INTO `book` VALUES ('A30', '围城', '钱钟书 ', '未评级', '中国', '9787020024759', '359');
INSERT INTO `book` VALUES ('A31', '最后的耍猴人', '马宏杰', '未评级', '中国', '9787213064951', '200');
INSERT INTO `book` VALUES ('A32', '金句：金星的语言艺术', '李少聪', '未评级', '中国', '9787519012427', '400');
INSERT INTO `book` VALUES ('A33', '爱的教育', '埃德蒙多·德·亚米契斯', '未评级', '意大利', '9787544761086', '500');
INSERT INTO `book` VALUES ('A34', '罪与罚', '陀思妥耶夫斯基', '未评级', '俄罗斯', '9787020115822', '300');
INSERT INTO `book` VALUES ('A35', '傲慢与偏见', '简·奥斯丁', '未评级', '英国', '9787544769228', '320');
INSERT INTO `book` VALUES ('A36', '了不起的盖茨比', '菲茨杰拉德', '未评级', '美国', '9787020104529', '234');
INSERT INTO `book` VALUES ('A37', '红与黑', '司汤达', '未评级', '法国', '9787020104451', '322');
INSERT INTO `book` VALUES ('A38', '飘', '玛格丽特·米切尔', '未评级', '美国', '9787544761628', '430');
INSERT INTO `book` VALUES ('A39', '双城记', '查尔斯·狄更斯', '未评级', '英国', '9787020104307', '242');
INSERT INTO `book` VALUES ('A4', '幻夜（上）', '东野圭吾', '未评级', '日本', '9789866562082', '368');
INSERT INTO `book` VALUES ('A40', '鲁滨孙历险记', '笛福', '未评级', '英国', '9787532758944', '406');
INSERT INTO `book` VALUES ('A41', '白夜行', '东野圭吾', '未评级', '日本', '9787544291163', '392');
INSERT INTO `book` VALUES ('A42', '凰权', '天下归元', '未评级', '中国', '9787539943039', '768');
INSERT INTO `book` VALUES ('A5', '幻夜（下）', '东野圭吾', '未评级', '日本', '9789866562099', '320');
INSERT INTO `book` VALUES ('A6', 'C++面向对象程序设计', '赵付青', '未评级', '中国', '9787118067002', '363');
INSERT INTO `book` VALUES ('A7', 'C语言程序设计', '张磊', '未评级', '中国', '9787040272994', '317');
INSERT INTO `book` VALUES ('A8', '蛙', '莫言', '未评级', '中国', '9787506366847', '347');

-- ----------------------------
-- Table structure for dvd
-- ----------------------------
DROP TABLE IF EXISTS `dvd`;
CREATE TABLE `dvd` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `publishYear` varchar(255) DEFAULT NULL,
  `playTime` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dvd
-- ----------------------------
INSERT INTO `dvd` VALUES ('B1', '画皮', '陈嘉上', '未评级', '庞洪', '2008', '103');
INSERT INTO `dvd` VALUES ('B10', '哈利·波特与死亡圣器（上）', 'J·K·罗琳', '未评级', '大卫·叶茨', '2010', '146');
INSERT INTO `dvd` VALUES ('B11', '暮光之城', '凯瑟琳·哈德威克', '未评级', '梅莉莎·罗森伯格', '2009', '117');
INSERT INTO `dvd` VALUES ('B12', '暮光之城：新月', '斯蒂芬妮·梅耶', '未评级', '格雷格·摩尔迪安', '2009', '100');
INSERT INTO `dvd` VALUES ('B13', '暮光之城：月食', '斯蒂芬妮·梅耶', '未评级', '克里斯汀·斯图尔特', '2010', '124');
INSERT INTO `dvd` VALUES ('B14', '战狼', '吕建民', '未评级', '吴京', '2015', '96');
INSERT INTO `dvd` VALUES ('B15', '战狼2', '吴京', '未评级', '吴京', '2017', '123');
INSERT INTO `dvd` VALUES ('B16', '唐人街探案', '陈思诚', '未评级', '陈祉希', '2015', '135');
INSERT INTO `dvd` VALUES ('B18', '饥饿游戏', '加里·罗斯', '未评级', '詹妮弗·劳伦斯', '2012', '142');
INSERT INTO `dvd` VALUES ('B19', '饥饿游戏2：星火燎原', '弗朗西斯·劳伦斯', '未评级', '詹妮弗·劳伦斯', '2013', '146');
INSERT INTO `dvd` VALUES ('B2', '蜘蛛侠：英雄归来', '乔恩·沃茨', '未评级', '约翰·弗朗西斯·戴利', '2017', '133');
INSERT INTO `dvd` VALUES ('B20', '饥饿游戏3：嘲笑鸟（上）', '弗朗西斯·劳伦斯', '未评级', '詹妮弗·劳伦斯', '2014', '123');
INSERT INTO `dvd` VALUES ('B21', '饥饿游戏3：嘲笑鸟（下）', '弗朗西斯·劳伦斯', '未评级', '詹妮弗·劳伦斯', '2015', '137');
INSERT INTO `dvd` VALUES ('B23', '闪光少女', '王冉', '未评级', '江志强', '2016', '100');
INSERT INTO `dvd` VALUES ('B24', '影', '张艺谋', '未评级', '张昭', '2018', '115');
INSERT INTO `dvd` VALUES ('B25', '我的少女时代', '陈玉珊', '未评级', '叶如芬', '2015', '134');
INSERT INTO `dvd` VALUES ('B26', '七月与安生', '曾国祥', '未评级', '陈乐', '2016', '110');
INSERT INTO `dvd` VALUES ('B27', '狼图腾', '让.雅克.阿诺', '未评级', '王为民', '2015', '116');
INSERT INTO `dvd` VALUES ('B28', '你的名字', '新海诚', '未评级', '武井克弘', '2016', '107');
INSERT INTO `dvd` VALUES ('B3', '狼的孩子雨和雪', '细田守', '未评级', '细田守', '2012', '117');
INSERT INTO `dvd` VALUES ('B31', '情书', '岩井俊二', '未评级', '未知', '1995', '117');
INSERT INTO `dvd` VALUES ('B32', '芳华', '冯小刚', '未评级', '胡晓峰', '2017', '136');
INSERT INTO `dvd` VALUES ('B33', '妖猫传', '陈凯歌', '未评级', '陈红', '2017', '129');
INSERT INTO `dvd` VALUES ('B34', '霸王别姬', '陈凯歌', '未评级', '未知', ' 1993', '171');
INSERT INTO `dvd` VALUES ('B35', '夏洛特烦恼', ' 彭大魔', '未评级', '刘洪涛', '2015', '104');
INSERT INTO `dvd` VALUES ('B36', '后会无期', '韩寒', '未评级', '方励', '2014', '106');
INSERT INTO `dvd` VALUES ('B37', '匆匆那年', '张一白', '未评级', '未知', '2014', '106');
INSERT INTO `dvd` VALUES ('B39', '前任攻略3', '田羽生', '未评级', '王中军', '2017', '120');
INSERT INTO `dvd` VALUES ('B40', '露水红颜', '高希希', '未评级', ' 未知', ' 2014', '99');
INSERT INTO `dvd` VALUES ('B41', '四大名铺1', '陈嘉上', '未评级', ' 未知', '2012', '118');
INSERT INTO `dvd` VALUES ('B5', '哈利·波特与魔法石', 'J·K·罗琳', '未评级', '丹尼尔·雷德克里夫', '2001', '152');
INSERT INTO `dvd` VALUES ('B6', '哈利·波特与密室', 'J·K·罗琳', '未评级', '克里斯·哥伦布', '2002', '161');
INSERT INTO `dvd` VALUES ('B7', '哈利·波特与阿兹卡班的囚徒', 'J·K·罗琳', '未评级', '阿方索·卡隆', '2004', '141');
INSERT INTO `dvd` VALUES ('B8', '哈利·波特与火焰杯', 'J·K·罗琳', '未评级', '迈克·纽维', '2005', '157');

-- ----------------------------
-- Table structure for painting
-- ----------------------------
DROP TABLE IF EXISTS `painting`;
CREATE TABLE `painting` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `publishCountry` varchar(255) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of painting
-- ----------------------------
INSERT INTO `painting` VALUES ('C10', '星月夜', '森特·梵高', '未评级', '法国', '73', '92');
INSERT INTO `painting` VALUES ('C11', '创造亚当', '米开朗基罗·博纳罗蒂', '未评级', '意大利', '280', '570');
INSERT INTO `painting` VALUES ('C12', '大宫女', '让·奥古斯特·多米尼克·安格尔', '未评级', '法国', '91', '162');
INSERT INTO `painting` VALUES ('C13', '西斯廷圣母', '拉斐尔·桑西', '未评级', '意大利', '265', '196');
INSERT INTO `painting` VALUES ('C15', '洛神赋图', '顾恺之', '未评级', '东晋', '572', '27');
INSERT INTO `painting` VALUES ('C16', '女史箴图', '顾恺之', '未评级', '东晋', '24', '348');
INSERT INTO `painting` VALUES ('C17', '游春图', '展子虔', '未评级', '隋朝', '43', '80');
INSERT INTO `painting` VALUES ('C18', '送子天王图', '吴道子', '未评级', '唐代', '35', '338');
INSERT INTO `painting` VALUES ('C2', '蒙娜丽莎', '达芬奇', '未评级', '意大利', '53', '77');
INSERT INTO `painting` VALUES ('C20', '捣练图', '张萱', '未评级', '唐代', '37', '145');
INSERT INTO `painting` VALUES ('C21', '簪花仕女图', '周昉', '未评级', '唐代', '46', '180');
INSERT INTO `painting` VALUES ('C22', '清明上河图', '张择端', '未评级', '中国', '25', '528');
INSERT INTO `painting` VALUES ('C3', '花瓶里的三朵向日葵', '森特·梵高', '未评级', '法国', '73', '60');
INSERT INTO `painting` VALUES ('C4', '花瓶里的五朵向日葵', '森特·梵高', '未评级', '法国', '98', '69');
INSERT INTO `painting` VALUES ('C5', '花瓶里的十二朵向日葵', '森特·梵高', '未评级', '法国', '91', '72');
INSERT INTO `painting` VALUES ('C6', '花瓶里的十五朵向日葵', '森特·梵高', '未评级', '法国', '92', '73');
INSERT INTO `painting` VALUES ('C7', '花瓶里的十四朵向日葵', '森特·梵高', '未评级', '法国', '100', '76');
INSERT INTO `painting` VALUES ('C8', '花瓶里的十五朵向日葵', '森特·梵高', '未评级', '法国', '95', '73');
INSERT INTO `painting` VALUES ('C9', '花瓶里的十二朵向日葵', '森特·梵高', '未评级', '法国', '92', '72');
