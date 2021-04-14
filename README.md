#### 必须指定的配置项
| 配置项 | 示例| 描述|
| :---: | :---: | :---: |
| db.url |  jdbc&#58;mysql://127.0.0.1:3306/test?characterEncoding=utf8 | 数据库连接url |
| db.username | ---- | 数据库用户名 |
| db.password | ---- | 数据库密码 |
| db.name | ---- | 要连接的数据库 |
| generator.table | comm_user | 要生成的表名，多个表名以英文逗号“,”分隔 |

#### 所有配置项
| 配置项 | 是否必填 | 默认值 |示例| 描述|
|  :---  | :---: |  :---:  | :---: | :---: |
| db.url | 是 | ---- |  jdbc&#58;mysql://127.0.0.1:3306/test | 数据库连接url |
| db.username | 是 | ---- |---- | 数据库用户名 |
| db.password | 是 | ---- |---- | 数据库密码 |
| db.name | 是 | ---- |---- | 要连接的数据库 |
| generator.table | 是 | ---- |comm_user | 要生成的表名，多个表名以英文逗号“,”分隔 |
| generator.entity.enable | 否 | true | true/false | 是否生成entity |
| generator.entity.project | 否 | src/main/java | ---- | entity生成位置，默认是单模块应用 |
| generator.entity.package | 否 | ---- | com.github.lyrric.entity | entity包名，当<br/>generator.entity.enable<br/>为true时，必须指定 |
| generator.mapper.enable | 否 | true | true/false | 是否生成mapper |
| generator.mapper.project | 否 | src/main/java | ---- | mapper生成位置，默认是单模块应用 |
| generator.mapper.package | 否 | ---- |com.github.lyrric.mapper | mapper包名，当<br/>generator.mapper.enable<br/>为true时，必须指定 |
| generator.xml.enable | 否 | true | true/false | 是否生成xml |
| generator.xml.project | 否 | src/main/java | ---- | xml生成位置，默认是单模块应用 |
| generator.xml.package | 否 | ---- | resources/mapper | xml生成路径 |
| generator.service.enable | 否 | false | true/false | 是否生成service |
| generator.service.project | 否 | src/main/java | ---- | service生成位置，默认是单模块应用 |
| generator.service.package | 否 | ---- |com.github.lyrric.service | service包名，当<br/>generator.mapper.enable<br/>为true时，必须指定 |
| generator.serviceImpl.enable | 否 | false | true/false | 是否生成serviceImpl |
| generator.serviceImpl.project | 否 | src/main/java | ---- | serviceImpl生成位置，默认是单模块应用 |
| generator.serviceImpl.package | 否 | ---- |com.github.lyrric.service.impl |serviceImpl包名，当<br/>generator.serviceImpl.enable<br/>为true时，必须指定 |
| generator.swagger.enable | 否 | true | true/false | entity是否生成swagger注解 |
| generator.lombok.enable | 否 | true | true/false | entity是否支持lombok |
| generator.lombok.data | 否 | true | true/false | entity是否生成&#64;Data注解 |
| generator.lombok.noArgsConstructor | 否 | false | true/false | entity是否生成&#64;NoArgsConstructor注解 |
| generator.lombok.allArgsConstructor | 否 | false | true/false | entity是否生成&#64;AllArgsConstructor注解 |
| generator.lombok.builder | 否 | false | true/false | entity是否生成&#64;Builder注解 |




