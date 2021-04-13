package com.github.lyrric;

import com.github.lyrric.db.AbstractDatabase;
import com.github.lyrric.db.MysqlDatabase;
import com.github.lyrric.entity.Clazz;
import com.github.lyrric.entity.Table;
import com.github.lyrric.entity.config.BaseConfig;
import com.github.lyrric.enums.TemplateEnum;
import com.github.lyrric.util.PropertiesUtil;
import com.github.lyrric.util.TableToClassUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/12 16:30
 */
public class MyBatisGenerator {

    Configuration cfg;

    PropertiesUtil propertiesUtil;

    public MyBatisGenerator() throws IOException {
        propertiesUtil = new PropertiesUtil();
        init();
    }

    private void init() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/ftl"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public void startGenerate() throws IOException, SQLException, TemplateException {
        AbstractDatabase database = new MysqlDatabase(propertiesUtil.getDbConfig(), propertiesUtil.getBaseConfig().getTable());
        database.connect();
        List<Table> tables = database.getTables();
        List<Clazz> clazzList = new TableToClassUtil(tables, database).getClazzList();
        startGenerate(clazzList);
    }

    private void startGenerate(List<Clazz> clazzList) throws IOException, TemplateException {
        for (Clazz clazz : clazzList) {
            BaseConfig baseConfig = propertiesUtil.getBaseConfig();
            Map<String, Object> root = new HashMap<>();
            root.put("clazz", clazz);
            root.put("generator", baseConfig);
            root.put("lombok", propertiesUtil.getLombokConfig());
            root.put("mybatisPlus", propertiesUtil.getMybatisPlusConfig());
            root.put("swagger", propertiesUtil.getSwaggerConfig());
            generateEntity(clazz, root);
            generateMapper(clazz, root);
            generateXml(clazz, root);
        }
    }

    private void generateEntity(Clazz clazz, Map<String, Object> data) throws IOException, TemplateException {
        BaseConfig baseConfig = propertiesUtil.getBaseConfig();
        Template temp = cfg.getTemplate(TemplateEnum.ENTITY.path);
        FileWriter fileWriter =
                new FileWriter(baseConfig.getEntityProject() + "/" + packageToPath(baseConfig.getEntityPackage()) +
                        "/" + clazz.getName() + ".java");
        temp.process(data, fileWriter);
    }
    private void generateMapper(Clazz clazz, Map<String, Object> data) throws IOException, TemplateException {
        BaseConfig baseConfig = propertiesUtil.getBaseConfig();
        Template temp = cfg.getTemplate(TemplateEnum.MAPPER.path);
        FileWriter fileWriter =
                new FileWriter(baseConfig.getMapperProject() + "/" + packageToPath(baseConfig.getMapperPackage()) +
                        "/" + clazz.getName() + "Mapper.java");
        temp.process(data, fileWriter);
    }
    private void generateXml(Clazz clazz, Map<String, Object> data) throws IOException, TemplateException {
        BaseConfig baseConfig = propertiesUtil.getBaseConfig();
        Template temp = cfg.getTemplate(TemplateEnum.XML.path);
        FileWriter fileWriter =
                new FileWriter(baseConfig.getXmlProject() + "/" + packageToPath(baseConfig.getXmlPackage()) +
                        "/" + clazz.getName() + "Mapper.xml");
        temp.process(data, fileWriter);
    }

    private String packageToPath(String pack){
        return pack.replaceAll("\\.", "/");
    }



}
