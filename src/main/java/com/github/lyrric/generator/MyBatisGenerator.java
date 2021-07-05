package com.github.lyrric.generator;

import com.github.lyrric.generator.db.AbstractDatabase;
import com.github.lyrric.generator.db.MysqlDatabase;
import com.github.lyrric.generator.entity.Clazz;
import com.github.lyrric.generator.entity.Table;
import com.github.lyrric.generator.entity.config.DbConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
import com.github.lyrric.generator.util.TableToClassUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/12 16:30
 */
public class MyBatisGenerator {

    Configuration cfg;

    DbConfig dbConfig;

    GeneratorConfig generatorConfig;

    public MyBatisGenerator(DbConfig dbConfig, GeneratorConfig generatorConfig){
        this.dbConfig = dbConfig;
        this.generatorConfig = generatorConfig;
        init();
    }

    private void init(){
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setTemplateLoader(new ClassTemplateLoader(getClass(),"/ftl/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    }

    public void generate() throws IOException, SQLException, TemplateException {
        AbstractDatabase database = new MysqlDatabase(dbConfig, generatorConfig.getTable(),
                generatorConfig.getEntity().getIgnoredColumns(),generatorConfig.getIgnoreTablePrefix());
        database.connect();
        List<Table> tables = database.getTables();
        List<Clazz> clazzList = new TableToClassUtil(tables, database).getClazzList();
        startGenerate(clazzList);
    }

    private void startGenerate(List<Clazz> clazzList) throws IOException, TemplateException {
        for (Clazz clazz : clazzList) {
            new EntityGenerator(clazz, generatorConfig, cfg).generate();
            new MapperGenerator(clazz, generatorConfig, cfg).generate();
            new ServiceGenerator(clazz, generatorConfig, cfg).generate();
            new ServiceImplGenerator(clazz, generatorConfig, cfg).generate();
            new XmlGenerator(clazz, generatorConfig, cfg).generate();
        }
    }
}
