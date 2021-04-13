package com.github.lyrric.test;


import com.github.lyrric.generator.enums.Mysql2JavaType;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class GeneratorTest {


    @Test
    public void test() throws IOException, SQLException, TemplateException {

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
