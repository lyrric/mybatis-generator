package com.github.lyrric.test;


import com.github.lyrric.generator.entity.config.DbConfig;
import com.github.lyrric.generator.enums.Mysql2JavaType;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class GeneratorTest {


    @Test
    public void test() throws IOException, SQLException, TemplateException {
        File file = new File("src/main/resources/generator-full.yaml");
        try(FileInputStream fileInputStream = new FileInputStream(file)){
            Yaml yaml = new Yaml();//实例化解析器
           /* Iterable<Object> objects = yaml.loadAll(fileInputStream);
            objects.forEach(o->{
                System.out.println();
            });*/
            Map<String, Object> map = (Map<String, Object>)yaml.loadAs(fileInputStream, Map.class);
            System.out.println();
        }

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
