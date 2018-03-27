本次简介以常见的订单维护，来介绍下平台内类似主子表结构的配置方法。
订单包括订单头和订单明细，订单头包括简单信息：订单编号、订单状态、客户、 交付日期、订单日期、备注等。订单明细包括：订单产品、定单数量、订单单价等。 
	为了使例子完整，还需创建客户（客户名称、联系人、地址、电话）、产品（产品名称、产品描述）两个对象。
第一步：
先创建并初始化完成上述四个对象，从后台点击“数据库管理”，选择数据源并点创建表，并完成相应的初始化。下面为各自的界面：
1 创建产品表 初始化产品业务对象



2 创建客户表 初始化客户对象


3 创建订单头表


4 创建订单明细表并初始化


初始化完成后，通过后台“翻译”功能，翻译界面中文，翻译字符串见附录一。

第二步
建立业务对象之间的关联关系。
平台中业务对象关联关系分为关联和组合两个概念，关联为松散的关联引用关系，如订单和客户的关系，订单明细和产品的关系；组合为强耦合的主子关系，如订单和订单明细的关系。
定义的方法在关联字段上关联。下面依次建立各种关系：
1 建立订单和客户的关系
选择业务对象“订单”，

在右侧业务对象维护，点击按钮“关联业务对象”

依次选择字段、关联类型、关联的业务对象，

点击生成关联。
2 建立订单明细和产品的关联关系，选择订单明细业务对象，点击进入关联业务对象，选择相应字段、类型和业务对象，如下图：


3 建立订单头和订单明细的主子关系，选择业务对象“订单明细”，点击关联业务对象，依次选择如下：



第三步
配置全部完成，点击右上角“清空缓存”，然后点击项目，打开工程登录

用户名 “Tom” 密码“1”
进入系统如下：

增加一些产品的测试数据：

增加一些客户的测试数据

增加客户订单：

订单详细信息维护









附一  翻译字符串：

productname=产品名称
standprice=标准报价
productnote=产品描述
customname=客户名称
linkman=联系人
linkphone=联系电话
address=地址
orderid=订单编号
customuid=客户
orderstate=订单状态
orderdate=订单日期
deliverydate=交付日期
productuid=产品
productnum=订货数量
productprice=订货单价
objuid=标识符


附录二  例子sql语句

/*Table structure for table `test_custom` */

DROP TABLE IF EXISTS `test_custom`;

CREATE TABLE `test_custom` (
  `objuid` varchar(50) default NULL COMMENT 'objuid',
  `customname` varchar(50) default NULL COMMENT 'customname',
  `linkman` varchar(50) default NULL COMMENT 'linkman',
  `linkphone` varchar(20) default NULL COMMENT 'linkphone',
  `address` varchar(200) default NULL COMMENT 'address'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `test_orderdetail` */

DROP TABLE IF EXISTS `test_orderdetail`;

CREATE TABLE `test_orderdetail` (
  `objuid` varchar(50) default NULL COMMENT 'objuid',
  `orderuid` varchar(50) default NULL COMMENT 'orderuid',
  `productuid` varchar(50) default NULL COMMENT 'productuid',
  `productnum` int(11) default NULL COMMENT 'productnum',
  `productprice` varchar(50) default NULL COMMENT 'productprice'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `test_orderheader` */

DROP TABLE IF EXISTS `test_orderheader`;

CREATE TABLE `test_orderheader` (
  `objuid` varchar(50) default NULL COMMENT 'objuid',
  `orderid` varchar(50) default NULL COMMENT 'orderid',
  `customuid` varchar(50) default NULL COMMENT 'customuid',
  `orderstate` varchar(10) default NULL COMMENT 'orderstate',
  `orderdate` datetime default NULL COMMENT 'orderdate',
  `deliverydate` datetime default NULL COMMENT 'deliverydate',
  `note` text COMMENT 'note'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `test_product` */

DROP TABLE IF EXISTS `test_product`;

CREATE TABLE `test_product` (
  `objuid` varchar(50) default NULL COMMENT 'objuid',
  `productname` varchar(50) default NULL COMMENT 'productname',
  `standprice` varchar(50) default NULL COMMENT 'standprice',
  `productnote` text COMMENT 'productnote'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
