package com.github.lyrric.generator.enums;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * mysql字段类型  to java类型 映射
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 15:52
 */
@Getter
public enum Mysql2JavaType{

    /**
     *
     */
    integer_("int|integer|tinyint|smallint|mediumint",Integer.class),
    date_("date|time|datetime|timestamp", Date.class),
    string_("varchar|char|text|tinytext|mediumtext|longtext",String.class),
    long_("bigint",Long.class),
    decimal("decimal", BigDecimal.class),
    float_("float", Float.class),
    double_("double", Double.class),
    ;
    private final String mysqlType;

    private final Class<?> javaType;

    Mysql2JavaType(String mysqlType, Class<?> javaType) {
        this.mysqlType = mysqlType;
        this.javaType = javaType;
    }

    public static Class<?> getJavaType(String mysqlType){
        Mysql2JavaType[] values = values();
        for (Mysql2JavaType value : values) {
            if(mysqlType.matches(value.mysqlType)){
                return value.javaType;
            }
        }
        return string_.javaType;
    }


}
