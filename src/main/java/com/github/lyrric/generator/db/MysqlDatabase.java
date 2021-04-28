package com.github.lyrric.generator.db;

import com.github.lyrric.generator.entity.Column;
import com.github.lyrric.generator.entity.Table;
import com.github.lyrric.generator.entity.config.DbConfig;
import com.github.lyrric.generator.enums.Mysql2JavaType;
import com.github.lyrric.generator.exception.TableNotExistException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/8 18:48
 */
public class MysqlDatabase extends AbstractDatabase{

    private Connection connection;

    public MysqlDatabase(DbConfig dbConfig, String tableNames,  List<String> ignoredColumns) {
        super(dbConfig, tableNames, ignoredColumns);
    }

    @Override
    public List<Table> getTables(){
        List<Table> tables = new ArrayList<>();
        List<String> tableNames = new ArrayList<>();
        if("*".equals(this.tableNames)){
            //全部表
            tableNames.addAll(getAllTableNames());
        }else if(this.tableNames.contains(",")){
            //多个表
            String[] split = this.tableNames.split(",");
            tableNames.addAll(Arrays.asList(split));
        }else{
            //单张表
            tableNames.add(this.tableNames);
        }
        for (String tableName : tableNames) {
            String tableComment = getTableComment(tableName);
            List<Column> columns = getTableColumns(tableName);
            Table table = new Table(tableName, tableComment, columns);
            tables.add(table);
        }
        return tables;
    }

    @Override
    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
    }

    @Override
    protected String getTableComment(String tableName) {
        try (Statement statement = connection.createStatement()){
            String sql = String.format("select table_comment from information_schema.`TABLES` where TABLE_SCHEMA = '%s' and table_name = '%s'", dbConfig.getName(), tableName);
            statement.execute(sql);
            try(ResultSet resultSet = statement.getResultSet()){
                if(resultSet.next()){
                    return resultSet.getString(1);
                }else{
                    throw new TableNotExistException(dbConfig.getName(), tableName);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    List<Column> getTableColumns(String tableName) {
        ArrayList<Column> columns = new ArrayList<>();
        String sql = String.format("SELECT COLUMN_NAME, DATA_TYPE, COLUMN_KEY, " +
                "COLUMN_COMMENT FROM information_schema.`COLUMNS` " +
                "WHERE TABLE_SCHEMA = '%s' AND table_name = '%s' order by ORDINAL_POSITION asc", dbConfig.getName(), tableName);
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            try(ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()){
                    String columnName = resultSet.getString("COLUMN_NAME");
                    if(ignoredColumns.contains(columnName)){
                        continue;
                    }
                    columns.add(Column.builder()
                            .name(columnName)
                            .dbType(resultSet.getString("DATA_TYPE"))
                            .key(resultSet.getString("COLUMN_KEY"))
                            .comment(resultSet.getString("COLUMN_COMMENT"))
                            .build());
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return columns;
    }

    @Override
    List<String> getAllTableNames() {
        List<String> tableNames = new ArrayList<>();
        String sql = String.format("select table_name from information_schema.`TABLES` where TABLE_SCHEMA = '%s' ", dbConfig.getName());
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            try(ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()){
                    tableNames.add(resultSet.getString(1));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tableNames;
    }

    @Override
    public Class<?> convert(Column column) {
        return Mysql2JavaType.getJavaType(column.getDbType());
    }
}
