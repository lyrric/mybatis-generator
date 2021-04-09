package com.github.lyrric.util;

import com.github.lyrric.entity.Clazz;
import com.github.lyrric.entity.Column;
import com.github.lyrric.entity.Field;
import com.github.lyrric.entity.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 17:40
 */
public class TableToClassUtil {

    private List<Table> tables;

    private ColumnToField columnToField;

    public TableToClassUtil(List<Table> tables, ColumnToField columnToField) {
        this.tables = tables;
        this.columnToField = columnToField;
    }

    public List<Clazz> getClazzList(){
        List<Clazz> clazzList = new ArrayList<>();
        for (Table table : tables) {
            Clazz clazz = new Clazz();
            clazzList.add(clazz);
            clazz.setTableName(table.getName());
            clazz.setComment(table.getComment());
            clazz.setName(tableNameToClassName(table.getName()));
            //需要动态import的行
            List<String> dynamicImport = new ArrayList<>();
            clazz.setDynamicImport(dynamicImport);
            //字段
            List<Field> fields = new ArrayList<>();
            clazz.setFields(fields);
            //处理列
            List<Column> columns = table.getColumns();
            for (Column column : columns) {
                Field field = new Field();
                field.setColumnName(column.getName());
                field.setComment(column.getComment());
                //判断主键的方式，可能不太完善
                field.setPrimaryKey(StringUtils.isNoneEmpty(column.getKey()));
                field.setName(columnNameToFieldName(column.getName()));
                Class<?> fieldClass = columnToField.convert(column);
                // like java.util.Date
                String canonicalName = fieldClass.getCanonicalName();
                if(!canonicalName.startsWith("java.lang") && !dynamicImport.contains(canonicalName)){
                    //如果不是基础封装数据，需要引入对应的包
                    dynamicImport.add(canonicalName);
                }
                field.setJavaType(getTypeName(canonicalName));
                fields.add(field);
            }
        }
        return clazzList;
    }

    /**
     * 将表名格式化为类名
     * 默认表名格式是以_分割的
     * @param tableName
     * @return
     */
    private String tableNameToClassName(String tableName){
        StringBuilder className = new StringBuilder();
        String[] split = tableName.split("_");
        for (String s : split) {
            className.append(StringUtils.capitalize(s));
        }
        return className.toString();
    }
    /**
     * 将字段格式化为列名
     * 默认列名格式是以_分割的
     * @param columnName
     * @return
     */
    private String columnNameToFieldName(String columnName){
        StringBuilder fieldName = new StringBuilder();
        String[] split = columnName.split("_");
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                fieldName.append(split[i]);
            }else{
                fieldName.append(StringUtils.capitalize(split[i]));
            }
        }
        return fieldName.toString();
    }

    /**
     *
     * @param className java.util.Date
     * @return Date
     */
    private String getTypeName(String className){
        String[] split = className.split("\\.");
        return split[split.length - 1];
    }

}
