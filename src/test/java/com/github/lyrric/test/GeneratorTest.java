package com.github.lyrric.test;


import com.github.lyrric.db.AbstractDatabase;
import com.github.lyrric.entity.Clazz;
import com.github.lyrric.entity.Table;
import com.github.lyrric.entity.config.DbConfig;
import com.github.lyrric.db.MysqlDatabase;
import com.github.lyrric.enums.Mysql2JavaType;
import com.github.lyrric.util.TableToClassUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class GeneratorTest {


    @Test
    public void test() throws IOException, SQLException {
        Properties properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("generator.properties");
        properties.load(is);
        DbConfig dbConfig = new DbConfig(properties);
        AbstractDatabase database = new MysqlDatabase(dbConfig, "comm_admin");
        database.connect();
        List<Table> tables = database.getTables();
        System.out.println(tables);
        List<Clazz> clazzList = new TableToClassUtil(tables, t -> Mysql2JavaType.getJavaType(t.getDbType())).getClazzList();
        System.out.println();
    }

    @Test
    public void enumTest(){
        assert Mysql2JavaType.getJavaType("bigint")
                .equals(Mysql2JavaType.long_.getJavaType());
        assert Mysql2JavaType.getJavaType("mediumint")
                .equals(Mysql2JavaType.integer_.getJavaType());
    }
    @Test
    public void ClassTest(){
        Class<?> dateClass = Date.class;
        System.out.println(dateClass.getCanonicalName());
    }

}
