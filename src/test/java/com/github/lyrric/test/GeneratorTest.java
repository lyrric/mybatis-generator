package com.github.lyrric.test;


import com.github.lyrric.MyBatisGenerator;
import com.github.lyrric.db.AbstractDatabase;
import com.github.lyrric.entity.Clazz;
import com.github.lyrric.entity.Table;
import com.github.lyrric.entity.config.DbConfig;
import com.github.lyrric.db.MysqlDatabase;
import com.github.lyrric.enums.Mysql2JavaType;
import com.github.lyrric.util.MyProperties;
import com.github.lyrric.util.PropertiesUtil;
import com.github.lyrric.util.TableToClassUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class GeneratorTest {


    @Test
    public void test() throws IOException, SQLException, TemplateException {
       new MyBatisGenerator().generate();
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
