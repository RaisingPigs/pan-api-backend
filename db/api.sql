-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: api_itf
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
-- Current Database: `api_itf`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `api_itf` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `api_itf`;

--
-- Table structure for table `sentence`
--

DROP TABLE IF EXISTS `sentence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sentence` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(1024) NOT NULL,
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型: 0-毒鸡汤, 1-土味情话,2-笑话',
  `creator_id` bigint(64) unsigned DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater_id` bigint(64) unsigned NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='句子表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sentence`
--

LOCK TABLES `sentence` WRITE;
/*!40000 ALTER TABLE `sentence` DISABLE KEYS */;
INSERT INTO `sentence` VALUES (52,'只要是石头，到哪里都不会发光的。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(53,'别减肥了，你丑不仅是因为胖。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(54,'有些人感慨：\"自己岁数不小了，还没有成熟起来。\"。其实你们已经成熟起来了，你们成熟起来就这样。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(55,'又一天过去了。今天过得怎么样，梦想是不是更远了？',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(56,'只有拥有找男女朋友的能力和很多备胎，才能真正享受单身生活。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(57,'你这么努力，忍受那么多寂寞和纠结，我们也没觉得你有多优秀。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(58,'多照照镜子，很多事情你就明白原因了。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(59,'如果你每天干的活明显多于别人，但自己很高兴还感觉得到器重，那么与其说你很有才干，不如说你的领导很会管人。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(60,'努力了这么久，但凡有点儿天赋，也该有些成功迹象了。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(61,'好的容貌和很多钱，是进入上流社交活动的通行证。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(62,'哪怕抛掉出身的因素，我觉得你也未必干得过别人。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(63,'有些女生觉得，说自己是吃货能显得可爱，其实并没有这样的效果。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(64,'这个世界没有错，谁让你长得不好看又没钱。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(65,'真正努力过的人，就会明白天赋的重要。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(66,'一些年轻人，通过高端消费来营造自己高端收入的形象。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(67,'你承受的苦难并不比他人多太多，痛苦主要来自敏感和脆弱。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(68,'我在社会中混的不好，我好恨社会。但我离不开它，',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(69,'我生活中不多的美好，全是它给的。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(70,'很多人发现自己在钱、权、女人的问题上比不过别人，于是开始试着在道德和人生境界上做文章。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(71,'你若没有可爱之处，便不会有人来爱你。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(72,'我要是有钱或者长得好看就好了。那样，即使我性格有点儿怪，也会有人愿意倾听我的想法和感受，跟我做朋友。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(73,'那些人人羡慕的精英，其实过得并不如你想象的那样好。但肯定比你是强得多。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(74,'很多时候别人对你好，并不是因为别人喜欢你，而是因为他们喜欢对人好。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(75,'懒是一个很好的托辞，说得好像勤快了，就真能干出什么大事儿一样。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(76,'当别人和你说忙，是他/她要留时间给更重要的人。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(77,'我们在困难时候最能依靠的人，也是得意时最容易忽视的人。比如备胎，父母，好心但是不优秀的朋友。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(78,'总有这样的人，该干活的时候职业不起来，玩的时候又放不开。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(79,'人生就是这样，有欢笑也有泪水。一部分人主要负责欢笑，另一部分人主要负责泪水。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(80,'等生活中真有了生老病死这样的大事，才知道自己以前的忧伤都是狗屁。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(81,'有很多人对狗很好，对人却很差。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(82,'友情越来越少，礼尚往来越来越多。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(83,'回首青春，我发现自己失去了很多宝贵的东西。但我并不难过，因为我知道，以后会失去的更多。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(84,'最靠得住的是金钱，最靠不住的是人心。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(85,'对今天解决不了的事情，也不必着急。因为明天还是解决不了。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(86,'“不去努力然后告诉自己我根本就不想要”的痛苦，比“拼命努力后失败”的痛苦，要小得多。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(87,'你努力后的成功，不能弥补你成功前的痛苦。更何况你还没成功。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(88,'假如今天生活欺骗了你，不要悲伤，不要哭泣，因为明天生活还会继续欺骗你。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(89,'要是有个地方能出卖自己的灵魂换取物质享受就好了。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(90,'坏女人爱男人的钱和权；好女人爱男人因有钱和有权，而产生的自信、宽大、精力充沛、乐观进取。还好，殊途同归。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(91,'精神追求，应当是物质追求得到满足后的自然反应，而不是在现实受挫后去寻求的安慰剂。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(92,'丑女让人注意她的内在美，长相普通的女孩让人忽视。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(93,'有些人努力了一辈子，就是从社会的四流挤入了三流。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(94,'什么样的人造就了什么样的国家，不要老觉得我们的祖国配不上你。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(95,'爱情就是，如果没有更好的选择了，我才陪你到天荒地老。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(96,'他给你的爱会消逝，他给你的TIFFANY却不会贬值。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(97,'一提到钱，大家就不是那么亲热了。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(98,'很多人不断地规划自己的人生，每天压力很大。其实不管你怎么过，都会后悔的。想想你这前几十年就明白了。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(99,'\"老师您好，请问像我这样的，没有背景的北大毕业生，应该如何定位自己？\"“社会底层群众中受教育程度最高的一批人。”',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(100,'基因是你给的，成长环境是你给的，社会阶层是你给的 ——还有脸埋怨自己孩子没出息。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(101,'我们所咒骂的那些品德败坏的人，其实不过是直接或间接地损害了我们的利益。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(102,'我的人生目标是三十岁时，在北京拥有一套自己的房子。现在我的目标已经实现一半了：我已经三十岁了。',0,1,'2024-02-14 16:18:11',1,'2024-02-14 16:18:11',0),(103,'不要抱怨，抱我。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(104,'近朱者赤，近你者甜。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(105,'我是九你是三，除了你还是你。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(106,'你知道我的缺点是什么吗? 是缺点你。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(107,'最近有谣言说我喜欢你，我要澄清一下，那不是谣言。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(108,'我怀疑你的本质是一本书，不然为什么让我越看越想睡。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(109,'我发现昨天很喜欢你，今天也很喜欢你，而且有预感明天也会喜欢你。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(110,'“我觉得你这个人不适合谈恋爱” “为什么?” “适合结婚。”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(111,'你知道你和星星有什么区别吗?星星在天上，你在我心里。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(112,'“你最近是不是又胖了?” “没有啊，为什么这么说?” “那为什么在我心里的分量越来越重了?”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(113,'这是我的手背，这是我的脚背，你是我的宝贝。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(114,'猜猜我的心在哪边?左边。错了，在你那边。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(115,'听闻先生治家有方，小女余生愿闻其详',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(116,'你闻到什么味道了吗?没有啊！怎么你一出来空气都是甜的了。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(117,'“我想买一块地。”“什么地?”“你的死心塌地。”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(118,'“你累不累啊?”“不累。”“可是你都在我脑里跑了一天了！”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(119,'“你能不能闭嘴?”“我没有说话啊”“那为什么我满脑子都是你的声音?”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(120,'“你知道我为什么感冒了吗?”“因为着凉了?”“不，因为我对你完全没有抵抗力。”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(121,'甜有100种方式，吃糖，蛋糕，还有每天98次的想你',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(122,'从今以后我只能称呼你为您了，因为，你在我心上。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(123,'莫文蔚的阴天，孙燕姿的雨天，周杰伦的晴天，都不如你和我聊天。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(124,'你有没有问到什么烧焦的味道?那是我的心在燃烧。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(125,'“你为什么要害我?”“害你什么?”“害我那么喜欢你!”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(126,'我对你的爱，就像拖拉机上山，轰轰烈烈……',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(127,'“游乐园那个，可以骑在上面的”“有音乐的叫旋转什么?”“木马。”“mua”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(128,'“面对你，我不仅善解人意，我还善解人衣。”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(129,'你喜欢喝水吗?那你已经喜欢上70%的我了。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(130,'“既然你把我的心已经弄乱了，那你打算什么时候来弄乱我的床?”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(131,'你上辈子一定是碳酸饮料吧，为什么我一看到你就能开心的冒泡。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(132,'三十晚上的鞭炮再响，都没有我想想你那么想。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(133,'我想去取一下东西，你等一下，我来娶你了。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(134,'别让我看见你，不然我见你一次，就喜欢你一次。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(135,'你知道你像什么人吗?(什么人?)我的女人。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(136,'你近视吗?(不近视啊)那你怎么看不出我喜欢你。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(137,'现在几点了?(12点)不，是我们幸福的起点。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(138,'把你的名字写在烟上吸进肺里，让你保持在离我心脏最近的地方。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(139,'我生在南方，活在南方，栽在你手里，总算是去过不一样的地方。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(140,'我想吃碗面 什么面 你的心里面。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(141,'你可以帮我个忙么? 什么忙? 帮忙快点爱上我!',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(142,'“你是哪里人” “杭州人” “不，你是我的心上人。”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(143,'你知道墙壁，眼睛，膝盖的英文怎么说么? wall,eye,knee 我也是，我爱你。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(144,'段位和你，我都想上。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(145,'我的手被划了一道口子 你也划一下 这样我们就是两口子了。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(146,'无事献殷勤，非..非常喜欢你。会当凌绝顶，一..一把抱住你。春眠不觉晓，处..处对象可好。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(147,'女孩，我十拿九稳，只差你一吻。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(148,'我可以不为别人难过，但你不是别人，你是我的人。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(149,'我还是喜欢你，像小时候吃辣条，不看日期。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(150,'我喜欢你抄过来两分钟了，不可以撤回了。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(151,'你眼瞎吗?撞我心口上了。',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(152,'“这辈子就跟我在一起吧，不行的话我再等等，还不行的话我再想想别的办法。”',1,1,'2024-02-14 16:21:30',1,'2024-02-14 16:23:21',0),(153,'同学问小明：“你有《时间简史》吗？\n小明：”我有时间捡那玩意儿干嘛？“',2,1,'2024-02-14 16:23:21',1,'2024-02-14 16:23:21',0),(154,'在公园看老大爷下象棋\n我：”大爷，你车没了”\n大爷：”不懂了吧，那叫ju”\n我：“好的，那你旁边的电动ju没了”',2,1,'2024-02-14 16:24:07',1,'2024-02-14 16:24:07',0),(155,'记得有次在走廊上碰到我男神，我赶忙把口袋里的情书塞给他\n好家伙，回到寝室一摸口袋\n情书还在，十元钱不见了。',2,1,'2024-02-14 16:24:24',1,'2024-02-14 16:24:24',0),(156,'我发朋友圈：最近在一个dao上\n好友“那个岛，我也去”\n我淡淡的回复：“穷困缭倒，要来吗？”',2,1,'2024-02-14 16:24:44',1,'2024-02-14 16:24:44',0),(157,'别人上车练车，第一句话都是小声嘀咕道“打火，踩离合，挂档，松离合，走起。”\n而我上车练车，第一句话一般都是大声先喊一句“都闪开！闪开！闪开！”',2,1,'2024-02-14 16:25:01',1,'2024-02-14 16:25:01',0),(158,'妈妈喜欢打麻将，可是后来我出生了，妈妈为了我也为了整个家庭，毅然决然地放弃了麻将，\n因为她突然发现，打我比较有趣。',2,1,'2024-02-14 16:25:15',1,'2024-02-14 16:25:15',0),(159,'两年后，前女友突然发短信来说：我们复合吧，转了一圈才发现你是最好的，我后悔了。短短几字\n倾刻间击溃我所有的伪装与防备。热泪流进挥之不去的记忆，夜夜思念的那些牵手在一起的美好\n日子又悄然浮上心头。\n我颤抖着双手回复道：活该！',2,1,'2024-02-14 16:25:27',1,'2024-02-14 16:25:27',0),(160,'本来想买件羽绒服，但是要3000多，后来仔细衡量了一下，感冒药也就几十块钱，还是买感冒药划\n算。',2,1,'2024-02-14 16:25:41',1,'2024-02-14 16:25:41',0),(161,'今天一哥们的老婆生了，打电话向我报喜。\n我本来是要问他：是男孩还是女孩的？\n结果，脑抽的问了他一句：是谁的？\n现在这哥们非要去做亲子鉴定，他老婆死活不肯。我觉得还是站在旁边保持沉默比较好。',2,1,'2024-02-14 16:26:26',1,'2024-02-14 16:26:26',0),(162,'有人告诉我，这世上再也没有比爱情更复杂的东西。\n我默默的把高中数学书甩他脸上。',2,1,'2024-02-14 16:26:26',1,'2024-02-14 16:26:26',0),(163,'有一个男生问一个女生：你喜欢什么样的男生呀？\n女生说：投缘的。\n男生听了，又问：你喜欢什么样的男生？\n女生气愤得说：投缘的！\n男生说：那你说，头扁行不行！',2,1,'2024-02-14 16:26:26',1,'2024-02-14 16:26:26',0),(164,'上初中时，数学老师对我一贪睡同学说：你是来上课的，不是来睡觉的。\n我那同学果断回了一句：你是来教书的，不是来催眠的。\n老师：……出去',2,1,'2024-02-14 16:26:26',1,'2024-02-14 16:26:26',0),(165,'“你好，我想问一下周杰伦演唱会预算100大概可以坐在什么位置？”\n“坐电视前面。”',2,1,'2024-02-14 16:26:58',1,'2024-02-14 16:26:58',0),(166,'你在阳台抽烟，你抽一半，风抽一半，你没跟风计较，可能风也有烦恼吧，你事后越想越气，于是\n抽起风来。',2,1,'2024-02-14 16:26:58',1,'2024-02-14 16:26:58',0);
/*!40000 ALTER TABLE `sentence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `open_api`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `open_api` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `open_api`;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='接口信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itf`
--

LOCK TABLES `itf` WRITE;
/*!40000 ALTER TABLE `itf` DISABLE KEYS */;
INSERT INTO `itf` VALUES (1,'随机毒鸡汤','/api/itf/sentence/chicken_soup','http://localhost:8888/api/itf/sentence/chicken_soup',0,'随机获取一句毒鸡汤','','','{\n	\"code\": 20000,\n	\"data\": \"这是一句毒鸡汤\",\n	\"msg\": \"success\"\n}','',NULL,1,1,'2024-01-16 20:20:05',1,'2024-02-14 17:37:24',0),(2,'随机土味情话','/api/itf/sentence/love_talk','http://localhost:8888/api/itf/sentence/love_talk',0,'随机获取一句土味情话','','','{\n	\"code\": 20000,\n	\"data\": \"这是一句土味情话\",\n	\"msg\": \"success\"\n}','',NULL,1,1,'2024-01-16 20:20:05',1,'2024-02-14 17:37:24',0),(3,'随机段子','/api/itf/sentence/joke','http://localhost:8888/api/itf/sentence/joke',0,'随机获取一个段子','','','{\n	\"code\": 20000,\n	\"data\": \"这是一个段子\",\n	\"msg\": \"success\"\n}','',NULL,1,1,'2024-01-16 20:20:05',1,'2024-02-14 17:37:24',0),(4,'计算bmi','/api/itf/calculate/bmi','http://localhost:8888/api/itf/calculate/bmi',0,'根据身高体重计算bmi','{  \n  \"weight\": 70,\n  \"height\": 1.80\n}','','{\n	\"code\": 20000,\n	\"data\": {\n		\"bmi\": 21.6,\n		\"level\": \"标准\",\n		\"normalBMI\": \"18.5 ~ 23.9\"\n	},\n	\"msg\": \"success\"\n}','',NULL,1,1,'2024-01-16 20:20:05',1,'2024-02-14 19:15:04',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='请求参数表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `param`
--

LOCK TABLES `param` WRITE;
/*!40000 ALTER TABLE `param` DISABLE KEYS */;
INSERT INTO `param` VALUES (14,4,0,'weight',1,'number','体重(单位kg)',0,1,'2024-02-14 17:25:57',1,'2024-02-14 17:25:57',0),(15,4,0,'height',1,'number','身高(单位m)',0,1,'2024-02-14 17:25:57',1,'2024-02-14 17:54:25',0),(16,1,0,'content',1,'string','毒鸡汤内容',2,1,'2024-02-14 17:28:15',1,'2024-02-14 17:28:15',0),(17,2,0,'content',1,'string','土味情话内容',2,1,'2024-02-14 17:28:15',1,'2024-02-14 17:28:15',0),(18,3,0,'content',1,'string','段子内容',2,1,'2024-02-14 17:28:15',1,'2024-02-14 17:28:15',0),(19,4,0,'bmi',1,'number','bmi值',2,1,'2024-02-14 17:28:15',1,'2024-02-14 17:28:15',0),(20,4,0,'level',1,'string','bmi等级',2,1,'2024-02-14 17:30:11',1,'2024-02-14 17:30:11',0),(21,4,0,'normalBMI',1,'string','正常的bmi值',2,1,'2024-02-14 17:30:11',1,'2024-02-14 17:30:11',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户接口调用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_itf`
--

LOCK TABLES `user_itf` WRITE;
/*!40000 ALTER TABLE `user_itf` DISABLE KEYS */;
INSERT INTO `user_itf` VALUES (25,2,1,1,19,0,2,'2024-02-14 17:37:50',2,'2024-02-14 17:50:23',0),(26,2,2,12,8,0,0,'2024-02-14 17:37:57',0,'2024-02-14 19:39:58',0),(27,2,4,6,4,0,0,'2024-02-14 17:51:00',0,'2024-02-14 19:14:47',0),(28,2,3,1,9,0,0,'2024-02-14 19:15:48',0,'2024-02-14 19:15:47',0);
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

-- Dump completed on 2024-02-14 19:55:38