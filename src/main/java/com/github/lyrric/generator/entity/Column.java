package com.github.lyrric.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库字段信息
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/8 18:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    /**
     * 字段名称
     */
    private String name;
    /**
     * 数据库字段类型
     */
    private String dbType;
    /**
     * 主键
     */
    private String key;
    /**
     * 字段注释
     */
    private String comment;

}
