package com.github.lyrric.generator.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 生成的类信息
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 17:20
 */
@Data
@NoArgsConstructor
public class Clazz {
    /**
     * 类名
     */
    private String name;
    /**
     * 注释
     */
    private String comment;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段信息
     */
    private List<Field> fields;
    /**
     * 动态引入的包
     */
    private List<String> dynamicImport;

    public String fieldNames(){
        return fields.stream().map(Field::getColumnName).collect(Collectors.joining(","));
    }
}
