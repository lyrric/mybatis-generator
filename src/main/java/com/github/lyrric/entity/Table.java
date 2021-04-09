package com.github.lyrric.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 数据库表信息
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/8 18:45
 */
@Data
@NoArgsConstructor
public class Table {
    /**
     * 表名称
     */
    private String name;
    /**
     * 表注释
     */
    private String comment;
    /**
     * 字段
     */
    List<Column> columns;

    public Table(String name, String comment, List<Column> columns) {
        this.name = name;
        this.comment = comment;
        this.columns = columns;
    }
}
