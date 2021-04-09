package com.github.lyrric.exception;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 14:03
 */
public class TableNotExistException extends RuntimeException{

    private String database;

    private String tableName;

    public TableNotExistException(String database, String tableName) {
        super("could not found " + tableName + " in database " + database);
        this.database = database;
        this.tableName = tableName;
    }
}
