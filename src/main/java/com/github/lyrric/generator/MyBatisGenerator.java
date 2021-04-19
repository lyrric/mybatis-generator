package com.github.lyrric.generator;

import com.github.lyrric.generator.db.AbstractDatabase;
import com.github.lyrric.generator.db.MysqlDatabase;
import com.github.lyrric.generator.entity.Clazz;
import com.github.lyrric.generator.entity.Table;
import com.github.lyrric.generator.entity.config.BaseConfig;
import com.github.lyrric.generator.entity.config.DbConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
import com.github.lyrric.generator.enums.TemplateEnum;
import com.github.lyrric.generator.util.MyConfigMap;
import com.github.lyrric.generator.util.TableToClassUtil;
import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
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

    DbConfig dbConfig;

    GeneratorConfig generatorConfig;

    public MyBatisGenerator() throws IOException {
        init();
        initConfig();
    }

    private void init(){
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setTemplateLoader(new ClassTemplateLoader(getClass(),"/ftl/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

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


    public void generate() throws IOException, SQLException, TemplateException {
        AbstractDatabase database = new MysqlDatabase(dbConfig, generatorConfig.getTable());
        database.connect();
        List<Table> tables = database.getTables();
        List<Clazz> clazzList = new TableToClassUtil(tables, database).getClazzList();
        startGenerate(clazzList);
    }

    private void startGenerate(List<Clazz> clazzList) throws IOException, TemplateException {
        for (Clazz clazz : clazzList) {
            Map<String, Object> root = new HashMap<>();
            root.put("clazz", clazz);
            root.put("generator", generatorConfig);
            root.put("entity", generatorConfig.getEntity());
            root.put("lombok", generatorConfig.getLombok());
            root.put("mapper", generatorConfig.getMapper());
            root.put("xml", generatorConfig.getXml());
            root.put("service", generatorConfig.getService());
            root.put("serviceImpl", generatorConfig.getServiceImpl());
            root.put("mybatisPlus", generatorConfig.getMybatisPlus());
            root.put("swagger", generatorConfig.getSwagger());

            generateEntity(clazz, root, generatorConfig.getEntity());
            generateMapper(clazz, root, generatorConfig.getMapper());
            generateXml(clazz, root, generatorConfig.getXml());
            generateService(clazz, root, generatorConfig.getService());
            generateServiceImpl(clazz, root, generatorConfig.getServiceImpl());
        }
    }

    private void generateEntity(Clazz clazz, Map<String, Object> data, BaseConfig entity) throws IOException, TemplateException {
        if(!entity.getEnable()){
            return ;
        }
        Template temp = cfg.getTemplate(TemplateEnum.ENTITY.path);
        String fileName = entity.getProject() + "/" + packageToPath(entity.getPackages()) +
                "/" + clazz.getName() + ".java";
        FileWriter fileWriter =
                new FileWriter(fileName);
        temp.process(data, fileWriter);
    }
    private void generateMapper(Clazz clazz, Map<String, Object> data, BaseConfig mapper) throws IOException, TemplateException {
        if(!mapper.getEnable()){
            return ;
        }
        Template temp = cfg.getTemplate(TemplateEnum.MAPPER.path);
        String fileName = mapper.getProject() + "/" + packageToPath(mapper.getPackages()) +
                "/" + clazz.getName() + "Mapper.java";
        FileWriter fileWriter =
                new FileWriter(checkFileExist(fileName));
        temp.process(data, fileWriter);
    }
    private void generateXml(Clazz clazz, Map<String, Object> data, BaseConfig xml) throws IOException, TemplateException {
        if(!xml.getEnable()){
            return ;
        }
        Template temp = cfg.getTemplate(TemplateEnum.XML.path);
        String fileName = xml.getProject() + "/" + packageToPath(xml.getPackages()) +
                "/" + clazz.getName() + "Mapper.xml";
        FileWriter fileWriter =
                new FileWriter(checkFileExist(fileName));
        temp.process(data, fileWriter);
    }

    private void generateService(Clazz clazz, Map<String, Object> data, BaseConfig service) throws IOException, TemplateException {
        if(!service.getEnable()){
            return ;
        }
        Template temp = cfg.getTemplate(TemplateEnum.SERVICE.path);
        String fileName = service.getProject() + "/" + packageToPath(service.getPackages()) +
                "/" + clazz.getName() + "Service.java";
        FileWriter fileWriter =
                new FileWriter(checkFileExist(fileName));
        temp.process(data, fileWriter);
    }

    private void generateServiceImpl(Clazz clazz, Map<String, Object> data, BaseConfig serviceImpl) throws IOException, TemplateException {
        if(!serviceImpl.getEnable()){
            return ;
        }
        Template temp = cfg.getTemplate(TemplateEnum.SERVICE_IMPL.path);
        String fileName = serviceImpl.getProject() + "/"
                + packageToPath(serviceImpl.getPackages()) +
                "/" + clazz.getName() + "ServiceImpl.java";
        FileWriter fileWriter =
                new FileWriter(checkFileExist(fileName));
        temp.process(data, fileWriter);
    }

    private String packageToPath(String pack){
        return pack.replaceAll("\\.", "/");
    }

    /**
     * 文件是否存在
     * @param fileName 存在则返回新的文件名
     * @return
     */
    private String checkFileExist(String fileName){
        File file = new File(fileName);
        if(file.exists()){
            //文件存在，生成副本
            fileName = fileName + ".bak";
        }
        return fileName;
    }

}
