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
  * 把数据库的表与实体类和实体类的属性关联起来
  * 通过操作实体类来操作数据库表

***************

### mybatis环境搭建

* 在IDEA中创建一个maven项目

* 在数据库中导入一张user表

  ![image-20200526141849455](C:\Users\泉水姐姐超级粉丝\AppData\Roaming\Typora\typora-user-images\image-20200526141849455.png)

* 设置maven打包方式`<packaging>jar</packaging>`

* 导入依赖

  ```java
  //mybatis依赖
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.5</version>
  </dependency>
  //mysql依赖
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.6</version>
  </dependency>
  //单元测试依赖
  <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.10</version>
  </dependency>
  //日志依赖
  <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.12</version>
  </dependency>
  ```

* 在**domain**包下创建**User**类，**实现Serializable接口**，属性名与数据库属性名相同

  ```java
  private Integer id;
  private String username;
  private Date birthday;
  private String sex;
  private String address;
  ```

* 在**dao**包下创建接口**IUserDao**

  ```java
  public interface IUserDao {
      List<User> findAll();
  }在resource文件夹下面
  ```

* 在resource文件夹下面创建**mybatis主配置文件 SqlMapConfig.xml**

  ```xml-dtd
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <environments default="mysql">
          <environment id="mysql">
              <transactionManager type="JDBC"></transactionManager>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/day0526-mybatis?useUnicode=true&amp;characterEncoding=utf8"/>
                  <property name="username" value="root"/>
                  <property name="password" value="542270191MSzyl"/>
              </dataSource>
          </environment>
      </environments>
  
      <mappers>
          <mapper resource="dao/IUserDao.xml"/>
      </mappers>
  </configuration>
  ```

* 在resource文件夹下面创建dao包，在创建**映射配置文件 IUserDao。xml**

  ```xml-dtd
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!-- namespace必须是全类名-->
  <mapper namespace="dao.IUserDao" >
      <!--id必须和方法名相同-->
      <select id="findAll" resultType="domain.User">
          select * from user
      </select>
  </mapper>
  ```

**************

### 环境搭建注意事项

* mybatis映射配置文件位置必须和dao接口的包目录结构相同
* 保证全类名和方法名统一就可以不写实现层只写接口

****************

### mybatis入门遇见的bug

* 提示说找不到IUserDao的映射

  * 原来是没在SqlMapConfig.xml下写

    `<mappers>
            <mapper resource="dao/IUserDao.xml"/>
    </mappers>`

* 字符集不匹配
  * 改成`jdbc:mysql://localhost:3306/day0526-mybatis?useUnicode=true&amp;characterEncoding=utf8`