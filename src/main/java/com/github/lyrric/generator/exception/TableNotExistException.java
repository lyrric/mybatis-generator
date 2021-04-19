package com.github.lyrric.generator.exception;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 14:03
 */
public class TableNotExistException extends RuntimeException{

    public TableNotExistException(String database, String tableName) {
        super("could not found: " + tableName + " in database :" + database);
    }
}
