# mybatis-learning
学习mybatis

### 三层框架和SSM的对应关系

* 框架：软件开发的一套解决方案，一个半成品软件。
* 三层架构
  * 表现层：展示数据   **Spring MVC**
  * 业务层：处理业务需求的
  * 持久层：负责数据库交互的  **mybatis**

**********************

### jdbc操作数据库问题分析

* jdbc技术   **规范**
  * Connection
  * PreparedStatement
  * ResultSet

* Spring的JdbcTemplate：spring对jdbc的简单封装   **工具类**
* Apach的DBUtils：也是对jdbc的简单封装  **工具类**
* 上面三种都不够细致方便，因此有了**mybatis**框架

****************************

### mybatis概述

* java的持久层框架
* 封装了jdbc
* 使开发者只关注SQL语句本身
* 通过xml或注解配置Statement
* **ORM**：对象关系映射