package com.github.lyrric.generator.entity;

import lombok.Data;

/**
 * java字段信息
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 17:22
 */
@Data
public class Field {
    /**
     * 数据库字段名
     */
    private String columnName;
    /**
     * java字段名
     */
    private String name;
    /**
     * java类型
     */
    private String javaType;
    /**
     * 注释
     */
    private String comment;
    /**
     * 是否主键
     */
    private boolean primaryKey;
    /**
     * 是否自增
     */
    private boolean autoIncrement;

}
