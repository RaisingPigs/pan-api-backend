-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: open_api
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `itf`
--

DROP TABLE IF EXISTS `itf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itf` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(256) NOT NULL COMMENT '接口名称',
  `path` varchar(256) NOT NULL COMMENT '路径',
  `url` varchar(1024) NOT NULL COMMENT '接口地址',
  `method` tinyint(1) NOT NULL DEFAULT '0' COMMENT '请求类型: 0-get 1-post 2-put 3-delete',
  `description` text COMMENT '接口描述',
  `query_param_example` text COMMENT '路径参数',
  `body_param_example` text COMMENT '请求参数, json类型',
  `resp_example` text COMMENT '响应结果示例',
  `req_header` text COMMENT '请求头',
  `resp_header` text COMMENT '响应头',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '接口状态: 0-关闭 1-开启',
  `creator_id` bigint(64) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_id` bigint(64) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除: 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `interface_info_id_uindex` (`id`),
  UNIQUE KEY `interface_info_path_uindex` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='接口信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itf`
--

LOCK TABLES `itf` WRITE;
/*!40000 ALTER TABLE `itf` DISABLE KEYS */;
INSERT INTO `itf` VALUES (1,'获取用户名','/api/itf/name/get','http://localhost:8888/api/itf/name/get',0,'获取用户名','{\"name\": \"张三\"}','','{\n	\"code\": 20000,\n	\"data\": \"GET: 你的name是张三\",\n	\"msg\": \"success\"\n}','',NULL,1,1,'2024-01-16 20:20:05',1,'2024-02-08 10:22:12',0),(2,'获取用户名','/api/itf/name/post','http://localhost:8888/api/itf/name/post',1,'获取用户名','{\"name\": \"qwe\"}','{\"username\": \"rty\"}',NULL,'',NULL,1,1,'2024-01-16 20:20:05',1,'2024-02-06 15:13:35',0);
/*!40000 ALTER TABLE `itf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `param`
--

DROP TABLE IF EXISTS `param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `param` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `itf_id` bigint(64) unsigned NOT NULL COMMENT 'interface的id',
  `parent_id` bigint(64) unsigned NOT NULL COMMENT '父id',
  `name` varchar(32) NOT NULL COMMENT '参数名',
  `required` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否必填, 0否 1是',
  `data_type` varchar(32) NOT NULL COMMENT '数据类型',
  `description` text NOT NULL COMMENT '描述',
  `param_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '参数类型, 0-路径参数 1-body参数 2-返回值',
  `creator_id` bigint(64) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_id` bigint(64) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除: 0-未删除 1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='请求参数表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `param`
--

LOCK TABLES `param` WRITE;
/*!40000 ALTER TABLE `param` DISABLE KEYS */;
INSERT INTO `param` VALUES (1,1,0,'name',1,'string','姓名',0,1,'2024-02-07 08:17:42',1,'2024-02-07 08:17:41',0),(2,2,0,'name',1,'string','姓名',0,1,'2024-02-07 08:19:17',1,'2024-02-07 08:19:16',0),(3,2,0,'username',1,'string','用户名',1,1,'2024-02-07 08:20:39',1,'2024-02-07 09:59:25',0),(4,2,0,'gender',1,'number','角色',1,1,'2024-02-07 08:21:32',1,'2024-02-07 09:59:25',0),(5,2,0,'grades',1,'array','成绩',1,1,'2024-02-07 08:28:58',1,'2024-02-07 09:59:25',0),(6,2,5,'course',1,'string','科目',1,1,'2024-02-07 08:33:35',1,'2024-02-07 08:33:35',0),(7,2,5,'score',1,'number','分数',1,1,'2024-02-07 08:34:19',1,'2024-02-07 08:34:19',0),(9,2,0,'name',1,'string','姓名',2,1,'2024-02-07 10:07:24',1,'2024-02-07 10:07:24',0),(10,2,0,'user',1,'object','用户',2,1,'2024-02-07 10:07:50',1,'2024-02-07 10:07:50',0),(11,2,10,'course',1,'string','科目',2,1,'2024-02-07 10:08:38',1,'2024-02-07 10:10:14',0),(12,2,10,'score',1,'number','分数',2,1,'2024-02-07 10:08:55',1,'2024-02-07 10:10:14',0),(13,1,0,'data',1,'string','返回的字符串',2,1,'2024-02-07 10:20:36',1,'2024-02-08 10:24:32',0);
/*!40000 ALTER TABLE `param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(256) NOT NULL COMMENT '用户昵称',
  `username` varchar(256) NOT NULL COMMENT '账号',
  `password` varchar(512) NOT NULL COMMENT '密码',
  `avatar` varchar(1024) NOT NULL COMMENT '用户头像',
  `gender` tinyint(1) NOT NULL COMMENT '性别',
  `role` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户角色：0-管理员 1-普通用户',
  `access_key` varchar(1024) NOT NULL COMMENT 'ak',
  `secret_key` varchar(512) NOT NULL,
  `creator_id` bigint(64) unsigned DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_id` bigint(64) unsigned NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_account` (`username`),
  UNIQUE KEY `user_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'管理员','admin','b42b203d8d0e87b132879b9ee2de0130','default',0,0,'a37b8b01b5feab7e82334b33ea95962f','f9fd43572515f4427db1b2066aa8da68',1,'2024-01-31 18:13:50',1,'2024-02-05 11:54:45',0),(2,'pan普通用户','111111','df1bdd638fe145905ac269e07d7be92b','default',0,1,'5830ba491f46b86f90a3b4b988b5dbad','9ed3d662a0c945aa958d0a902e8dc3a4',1,'2024-02-02 23:34:39',1,'2024-02-06 08:42:45',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_itf`
--

DROP TABLE IF EXISTS `user_itf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_itf` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(64) unsigned NOT NULL COMMENT '用户id',
  `itf_id` bigint(64) unsigned NOT NULL COMMENT '接口id',
  `invoke_count` int(11) NOT NULL DEFAULT '0' COMMENT '总调用次数',
  `left_count` int(11) NOT NULL DEFAULT '10' COMMENT '剩余调用次数',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0-正常 1-禁用(用户违反规则后禁用)',
  `creator_id` bigint(64) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_id` bigint(64) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_id_interface_info_id` (`user_id`,`itf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户接口调用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_itf`
--

LOCK TABLES `user_itf` WRITE;
/*!40000 ALTER TABLE `user_itf` DISABLE KEYS */;
INSERT INTO `user_itf` VALUES (2,1,1,0,100,0,1,'2024-01-19 18:00:07',1,'2024-02-08 17:31:54',0),(4,1,2,0,100,0,1,'2024-01-19 18:23:14',1,'2024-02-08 17:31:54',0),(6,2,1,0,100,0,1,'2024-01-24 01:36:43',1,'2024-02-08 17:31:54',0),(7,2,2,0,100,0,1,'2024-01-24 01:54:05',1,'2024-02-08 17:31:54',0);
/*!40000 ALTER TABLE `user_itf` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-08 17:32:43
