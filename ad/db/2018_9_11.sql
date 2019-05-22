/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.21-log : Database - ad
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ad` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ad`;

/*Table structure for table `material` */

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `cid` int(11) unsigned DEFAULT NULL COMMENT '分类ID',
  `file_name` varchar(300) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(300) DEFAULT NULL COMMENT '文件路径',
  `package_name` varchar(300) DEFAULT NULL COMMENT '包名',
  `create_user` bigint(20) unsigned DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小 字节',
  `check_status` tinyint(1) DEFAULT NULL COMMENT '0.未审核 1.测试阶段，2.审核通过 3.审核失败',
  `type` int(2) unsigned DEFAULT NULL COMMENT '素材类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `material` */

insert  into `material`(`id`,`name`,`cid`,`file_name`,`file_path`,`package_name`,`create_user`,`create_time`,`file_size`,`check_status`,`type`) values (3,'22',2,'395DF1DE-CCEA-4004-BDC1-795B3F0A9D12.png','material/1536645257005.png',NULL,1,'2018-09-11 13:46:19',130185,0,2);
insert  into `material`(`id`,`name`,`cid`,`file_name`,`file_path`,`package_name`,`create_user`,`create_time`,`file_size`,`check_status`,`type`) values (4,'第二',8,'395DF1DE-CCEA-4004-BDC1-795B3F0A9D12.png','material/1536648402823.png',NULL,1,'2018-09-11 14:46:52',130185,0,2);
insert  into `material`(`id`,`name`,`cid`,`file_name`,`file_path`,`package_name`,`create_user`,`create_time`,`file_size`,`check_status`,`type`) values (5,'1',2,'jianjian.png','material/1536650166391.png',NULL,1,'2018-09-11 15:16:09',130185,0,2);

/*Table structure for table `material_class` */

DROP TABLE IF EXISTS `material_class`;

CREATE TABLE `material_class` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `pid` int(10) unsigned DEFAULT NULL COMMENT '父id',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creat_user` bigint(20) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `material_class` */

insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (1,'视频',0,'2018-09-10 14:58:35',NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (2,'图片',0,'2018-09-10 14:58:52',NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (3,'文本',0,'2018-09-10 14:59:19',NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (4,'音乐',0,'2018-09-10 14:59:34',NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (5,'动画',0,'2018-09-10 14:59:44',NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (6,'应用',0,'2018-09-10 15:00:12',NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (7,'电视剧',1,NULL,NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (8,'111',2,NULL,NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (20,'hell',7,NULL,NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (21,'1',7,NULL,NULL);
insert  into `material_class`(`id`,`name`,`pid`,`creat_time`,`creat_user`) values (22,'2',21,NULL,NULL);

/* Function  structure for function  `getMtChild` */

/*!50003 DROP FUNCTION IF EXISTS `getMtChild` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `getMtChild`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
    DECLARE ptemp varchar(1000);
    DECLARE ctemp varchar(1000);
    SET ptemp = '#';
    SET ctemp =cast(rootId as CHAR);
    WHILE ctemp is not null DO
      SET ptemp = concat(ptemp,',',ctemp);
      SELECT group_concat(id) INTO ctemp FROM `material_class`
      WHERE FIND_IN_SET(pid,ctemp)>0;
    END WHILE;
    RETURN ptemp;
  END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
