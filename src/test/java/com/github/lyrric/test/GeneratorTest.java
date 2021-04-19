package com.github.lyrric.test;


import com.github.lyrric.generator.entity.config.DbConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
import com.github.lyrric.generator.enums.Mysql2JavaType;
import com.github.lyrric.generator.util.MyConfigMap;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class GeneratorTest {


    @Test
    public void test() throws IOException{
        File file = new File("src/main/resources/generator.yaml");
        try(FileInputStream cusIs = new FileInputStream(file);
            InputStream defaultIs = getClass().getClassLoader().getResourceAsStream("generator-default.yml")){
            Yaml yaml = new Yaml();//实例化解析器
            Iterable<Object> cusObj = yaml.loadAll(cusIs);
            Iterable<Object> defaultObj = yaml.loadAll(defaultIs);
            Map<String, ?> customMap = getFirst(cusObj);
            Map<String, ?> defaultMap = getFirst(defaultObj);
            DbConfig dbConfig = new DbConfig(new MyConfigMap((Map)customMap.get("db"), (Map)defaultMap.get("db")));
            GeneratorConfig generatorConfig = new GeneratorConfig(new MyConfigMap((Map)customMap.get("generator"), (Map)defaultMap.get("generator")));

        }

    }


    public Map<String, ?> getFirst(Iterable<Object> objects){
        return (Map<String, ?>)objects.iterator().next();
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
