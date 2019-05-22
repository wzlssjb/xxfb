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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ad` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `ad`;

/*Table structure for table `publish` */

DROP TABLE IF EXISTS `publish`;

CREATE TABLE `publish` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `uid` bigint(20) DEFAULT NULL COMMENT '创建人',
  `status` tinyint(1) DEFAULT '1' COMMENT '发布状态;1.正常，2.下线',
  `play_mode` tinyint(1) DEFAULT '1' COMMENT '播放模式;1.轮播2.单次',
  `start_time` date DEFAULT NULL COMMENT '开始时间',
  `end_time` date DEFAULT NULL COMMENT '结束时间',
  `check_status` tinyint(1) DEFAULT '0' COMMENT '审核状态;0.未审核1.审核成功2.审核失败',
  `checker` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核人',
  `check_remark` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核备注',
  `inter_cut` tinyint(1) DEFAULT '0' COMMENT '是否插播;0.正常播放1.插播',
  `cycle_week` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '周循环',
  `cycle_mode` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='publish;发布主表';

/*Data for the table `publish` */

insert  into `publish`(`id`,`name`,`create_time`,`uid`,`status`,`play_mode`,`start_time`,`end_time`,`check_status`,`checker`,`check_remark`,`inter_cut`,`cycle_week`,`cycle_mode`) values (18,'发布2018_11_07_16_35_31','2018-11-07 16:35:52',1,1,2,'2018-11-07','2018-11-07',0,NULL,NULL,0,'3',2),(19,'发布2018_11_07_16_36_14','2018-11-07 16:36:34',1,1,2,'2018-11-07','2018-11-07',0,NULL,NULL,0,'3',2),(20,'发布2018_11_07_16_37_10','2018-11-07 16:37:35',1,1,2,'2018-11-07','2018-11-07',0,NULL,NULL,0,'1,2',2),(21,'发布2018_11_07_16_38_10','2018-11-07 16:39:22',1,1,1,'2018-11-07','2018-11-07',0,NULL,NULL,0,NULL,1),(22,'发布2018_11_07_16_39_36','2018-11-07 16:39:56',1,1,2,'2018-11-07','2018-11-07',0,NULL,NULL,0,NULL,1),(23,'发布2018_11_07_16_40_12','2018-11-07 16:40:30',1,1,2,'2018-11-07','2018-11-07',0,NULL,NULL,0,NULL,1);

/*Table structure for table `publish_device` */

DROP TABLE IF EXISTS `publish_device`;

CREATE TABLE `publish_device` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) unsigned NOT NULL COMMENT '发布id',
  `did` bigint(20) DEFAULT NULL COMMENT '设备id',
  `status` tinyint(1) DEFAULT '1' COMMENT '发布状态;1.正常2.下线',
  `synchronize` tinyint(1) DEFAULT '0' COMMENT '同步进度;0.未下载1.下载种2.下载完成',
  PRIMARY KEY (`id`),
  KEY `DRIVE_PD_FK` (`did`),
  KEY `DRIVICE_PB_FK` (`pid`),
  CONSTRAINT `DRIVE_PD_FK` FOREIGN KEY (`did`) REFERENCES `stb` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `DRIVICE_PB_FK` FOREIGN KEY (`pid`) REFERENCES `publish` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='发布设备;';

/*Data for the table `publish_device` */

insert  into `publish_device`(`id`,`pid`,`did`,`status`,`synchronize`) values (20,18,3,1,0),(21,19,3,1,0),(22,20,3,1,0),(23,21,3,1,0),(24,22,3,1,0),(25,23,3,1,0);

/*Table structure for table `publish_play_time` */

DROP TABLE IF EXISTS `publish_play_time`;

CREATE TABLE `publish_play_time` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `start_time` time DEFAULT NULL COMMENT '开始时间',
  `end_time` time DEFAULT NULL COMMENT '结束时间',
  `pid` bigint(20) unsigned NOT NULL COMMENT '发布id',
  PRIMARY KEY (`id`),
  KEY `TIME_PB_FK` (`pid`),
  CONSTRAINT `TIME_PB_FK` FOREIGN KEY (`pid`) REFERENCES `publish` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='播放时间段;';

/*Data for the table `publish_play_time` */

insert  into `publish_play_time`(`id`,`start_time`,`end_time`,`pid`) values (19,'16:35:31','17:35:31',18),(20,'16:36:14','16:36:14',19),(21,'16:37:10','17:37:10',20),(22,'16:39:36','16:39:36',22),(23,'16:40:12','17:40:12',23);

/*Table structure for table `publish_theme` */

DROP TABLE IF EXISTS `publish_theme`;

CREATE TABLE `publish_theme` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) unsigned DEFAULT NULL COMMENT '发布id',
  `tid` bigint(20) unsigned DEFAULT NULL COMMENT '节目id',
  PRIMARY KEY (`id`),
  KEY `THEME_PT_FK` (`tid`),
  KEY `THEME_PB_FK` (`pid`),
  CONSTRAINT `THEME_PB_FK` FOREIGN KEY (`pid`) REFERENCES `publish` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `THEME_PT_FK` FOREIGN KEY (`tid`) REFERENCES `theme` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='发布节目;';

/*Data for the table `publish_theme` */

insert  into `publish_theme`(`id`,`pid`,`tid`) values (32,18,20),(33,19,20),(34,20,20),(35,21,23),(36,22,24),(37,23,23);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
