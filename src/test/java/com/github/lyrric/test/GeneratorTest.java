package com.github.lyrric.test;


import com.github.lyrric.generator.MyBatisGenerator;
import com.github.lyrric.generator.entity.config.DbConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
import com.github.lyrric.generator.util.MyConfigMap;
import freemarker.template.TemplateException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

public class GeneratorTest {

    private DbConfig dbConfig;

    private GeneratorConfig generatorConfig;


    //@Test
    public void testGenerate() throws IOException, SQLException, TemplateException {
        testInitConfig();
        new MyBatisGenerator(dbConfig, generatorConfig).generate();

    }

    //@Test
    public void testInitConfig() throws IOException {
        File file = new File("src/test/resources/generator.yaml");
        try(FileInputStream cusIs = new FileInputStream(file);
            InputStream defaultIs = getClass().getClassLoader().getResourceAsStream("generator-default.yaml")){
            Yaml yaml = new Yaml();
            Iterable<Object> cusObj = yaml.loadAll(cusIs);
            Map<String, ?> customMap = (Map<String, ?>)cusObj.iterator().next();
            Iterable<Object> defaultObj = yaml.loadAll(defaultIs);
            Map<String, ?> defaultMap = (Map<String, ?>)defaultObj.iterator().next();
            dbConfig = new DbConfig(new MyConfigMap((Map)customMap.get("db"), (Map)defaultMap.get("db")));
            generatorConfig = new GeneratorConfig(new MyConfigMap((Map)customMap.get("generator"), (Map)defaultMap.get("generator")));
        }
    }


}
