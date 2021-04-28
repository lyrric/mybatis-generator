package com.github.lyrric.generator.db;

import com.github.lyrric.generator.entity.Column;
import com.github.lyrric.generator.entity.Table;
import com.github.lyrric.generator.entity.config.DbConfig;
import com.github.lyrric.generator.util.ColumnToField;

import java.sql.SQLException;
import java.util.List;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 14:25
 */
public abstract class AbstractDatabase implements ColumnToField {

    DbConfig dbConfig;

    String tableNames;

    List<String> ignoredColumns;

    public AbstractDatabase(DbConfig dbConfig, String tableNames, List<String> ignoredColumns) {
        this.dbConfig = dbConfig;
        this.tableNames = tableNames;
        this.ignoredColumns = ignoredColumns;
    }

    /**
     * 获取所有的表信息
     * @return
     */
    public abstract List<Table> getTables();

    /**
     * 连接数据库
     * @throws SQLException
     */
    public abstract void connect() throws SQLException;

    /**
     * 获取表注释
     * @param tableName
     * @exception SQLException
     * @return
     */
    abstract String getTableComment(String tableName) throws SQLException;

    /**
     * 获取表所有字段信息
     * @param tableName
     * @return
     */
    abstract List<Column> getTableColumns(String tableName);
    /**
     * 获取所有的表名
     * @return
     */
    abstract List<String> getAllTableNames();

}
