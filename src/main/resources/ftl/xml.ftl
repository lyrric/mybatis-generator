<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${generator.mapperPackage}.${clazz.name}Mapper">

	<resultMap id="BaseResultMap" type="${generator.entityPackage}.${clazz.name}">
	<#list clazz.fields as field>
		<id column="${field.columnName}" property="${field.name}" />
	</#list>
	</resultMap>
	<sql id="Base_Column_List">
		${clazz.fieldNames()}
	</sql>
	
</mapper>