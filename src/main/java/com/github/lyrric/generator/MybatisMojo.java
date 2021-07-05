package com.github.lyrric.generator;

import com.github.lyrric.generator.entity.config.DbConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
import com.github.lyrric.generator.util.MyConfigMap;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/13 16:02
 */
@Mojo(name = "generate")
public class MybatisMojo extends AbstractMojo {

    private DbConfig dbConfig;

    private GeneratorConfig generatorConfig;

    @Override
    public void execute(){
        try {
            initConfig();
            new MyBatisGenerator(dbConfig, generatorConfig).generate();
        } catch (Exception e) {
            getLog().error(e);
        }
    }

    @SuppressWarnings("all")
    private void initConfig() throws IOException {
        File file = new File("src/main/resources/generator.yaml");
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
