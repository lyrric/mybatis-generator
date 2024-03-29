#### 介绍
mybatis-generator是一个maven插件，它可以一键生成entity、mapper、xml、service、serviceImpl，并支持lombok、swagger  
生成的文件示例：[查看示例](https://github.com/lyrric/mybatis-generator/tree/main/example)

#### 更新日志
- 2021.07.05 v1.0.3版本
  - 1.删除了配置项db.name，改为从db.url中解析数据库名称  
  - 2.加入新特性ignoreTablePrefix，忽略table前缀（如果表名有此前缀，生成的文件会去掉其前缀）  
  - 3.修改了生成mapper时，继承mybatisPlus的BaseMapper没有泛型问题  
- 2021.07.05 v1.0.2 修改maven运行命令
#### 快速开始
1.在maven项目的pom中添加配置  
```
 <build>
       <plugins>
           <plugin>
               <groupId>com.github.lyrric</groupId>
               <artifactId>mybatis-generator-maven-plugin</artifactId>
               <version>1.0.3</version>
               <dependencies>
                   <dependency>
                       <groupId>mysql</groupId>
                       <artifactId>mysql-connector-java</artifactId>
                       <version>8.0.18</version> <!-- 你的mysql版本 -->
                   </dependency>
               </dependencies>
           </plugin>
       </plugins>
   </build>
```
2.在resources下面添加配置文件```generator.yaml```  
```
#数据库信息
db:
  url: jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true
  username: root
  password: root

#swagger
generator:
  table: comm_salary #要生成的表名，多个表名以,逗号分隔
  entity:
    package: com.github.lyrric.entity #实体类路径
  mapper:
    enable: true #是否生成mapper，默认为true
    package: com.github.lyrric.mapper #mapper路径
  xml:
    project: src/main #xml生成项目，默认为src/main/java
    package: resources/mapper #xml生成位置，默认为resources/mapper
  service:
    enable: true #是否生成service，默认为false
    package: com.github.lyrric.service #service路径，如果要生成service则必须指定
  serviceImpl:
    enable: true #是否生成serviceImpl，默认为false
    package: com.github.lyrric.service.impl #serviceImpl路径，如果要生成serviceImpl则必须指定
```
4.运行命令: ```mvn mybatis-generator:generate```
#### 所有配置项
```
#数据库信息
db:
  url: jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true
  username: root
  password: root

#swagger
generator:
  table: user #要生成的表名，多个表名以,逗号分隔
  author: mybatis-generator #生成作者
  swagger: true #实体类是否生成swagger注解，默认为true
  mybatisPlus: true #是否支持mybatisPlus，默认为true
  ignoreTablePrefix: #忽略table前缀（如果表名有此前缀，生成的文件名会去掉其前缀）
  #lombok
  lombok:
    enable: true #是否支持lombok注解
    data: true #是否生成@Data注解
    noArgsConstructor: false #是否生成@NoArgsConstructor注解
    allArgsConstructor: false #是否生成@AllArgsConstructor注解
    builder: false #是否生成@Builder注解
  #entity
  entity:
    enable: true #是否生成entity，默认为true
    extendClass:  #父类，多个以逗号分隔，默认为空(支持泛型，格式为com.BaseEntity<T>)
    ignoredColumns: id #忽略的字段，多个以逗号分隔
    package: com.github.lyrric.entity #entity package，如果要生成entity则必须指定
    project: src/main/java #entity生成项目，默认为src/main/java
  #mapper
  mapper:
    enable: true #是否生成mapper，默认为true
    extendClass: #父类，多个以逗号分隔，默认为空(支持泛型，格式为com.BaseMapper<T>)
    package: com.github.lyrric.mapper #mapper package，如果要生成mapper则必须指定
    project: src/main/java #mapper生成项目，默认为src/main/java
  #xml
  xml:
    enable: true #是否生成xml，默认为true
    project: src/main #xml生成项目，默认为src/main/java
    package: resources/mapper #xml生成位置，默认为resources/mapper
  #service
  service:
    enable: false #是否生成service，默认为false
    package: com.github.lyrric.service #service package，如果要生成service则必须指定
    project: src/main/java #service生成项目，默认为src/main/java
  #serviceImpl
  serviceImpl:
    enable: false #是否生成serviceImpl，默认为false
    package: com.github.lyrric.service.impl #serviceImpl package，如果要生成serviceImpl则必须指定
    project: src/main/java #serviceImpl生成位置，默认为src/main/java
```