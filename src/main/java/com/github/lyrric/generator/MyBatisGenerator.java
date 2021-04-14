package com.github.lyrric.generator;

import com.github.lyrric.generator.db.AbstractDatabase;
import com.github.lyrric.generator.db.MysqlDatabase;
import com.github.lyrric.generator.entity.Clazz;
import com.github.lyrric.generator.entity.Table;
import com.github.lyrric.generator.entity.config.BaseConfig;
import com.github.lyrric.generator.enums.TemplateEnum;
import com.github.lyrric.generator.util.PropertiesUtil;
import com.github.lyrric.generator.util.TableToClassUtil;
import freemarker.cache.ClassTemplateLoader;
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

    private void init(){
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setTemplateLoader(new ClassTemplateLoader(getClass(),"/ftl/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public void generate() throws IOException, SQLException, TemplateException {
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
            if(baseConfig.isEntityEnable()){
                generateEntity(clazz, root);
            }
            if(baseConfig.isMapperEnable()){
                generateMapper(clazz, root);
            }
            if(baseConfig.isXmlEnable()){
                generateXml(clazz, root);
            }
            if(baseConfig.isServiceEnable()){
                generateService(clazz, root);
            }
            if(baseConfig.isServiceImplEnable()){
                generateServiceImpl(clazz, root);
            }
        }
    }

    private void generateEntity(Clazz clazz, Map<String, Object> data) throws IOException, TemplateException {
        BaseConfig baseConfig = propertiesUtil.getBaseConfig();
        Template temp = cfg.getTemplate(TemplateEnum.ENTITY.path);
        String fileName = baseConfig.getEntityProject() + "/" + packageToPath(baseConfig.getEntityPackage()) +
                "/" + clazz.getName() + ".java";
        FileWriter fileWriter =
                new FileWriter(fileName);
        temp.process(data, fileWriter);
    }
    private void generateMapper(Clazz clazz, Map<String, Object> data) throws IOException, TemplateException {
        BaseConfig baseConfig = propertiesUtil.getBaseConfig();
        Template temp = cfg.getTemplate(TemplateEnum.MAPPER.path);
        String fileName = baseConfig.getMapperProject() + "/" + packageToPath(baseConfig.getMapperPackage()) +
                "/" + clazz.getName() + "Mapper.java";
        FileWriter fileWriter =
                new FileWriter(checkFileExist(fileName));
        temp.process(data, fileWriter);
    }
    private void generateXml(Clazz clazz, Map<String, Object> data) throws IOException, TemplateException {
        BaseConfig baseConfig = propertiesUtil.getBaseConfig();
        Template temp = cfg.getTemplate(TemplateEnum.XML.path);
        String fileName = baseConfig.getXmlProject() + "/" + packageToPath(baseConfig.getXmlPackage()) +
                "/" + clazz.getName() + "Mapper.xml";
        FileWriter fileWriter =
                new FileWriter(checkFileExist(fileName));
        temp.process(data, fileWriter);
    }

    private void generateService(Clazz clazz, Map<String, Object> data) throws IOException, TemplateException {
        BaseConfig baseConfig = propertiesUtil.getBaseConfig();
        Template temp = cfg.getTemplate(TemplateEnum.SERVICE.path);
        String fileName = baseConfig.getServiceProject() + "/" + packageToPath(baseConfig.getServicePackage()) +
                "/" + clazz.getName() + "Service.java";
        FileWriter fileWriter =
                new FileWriter(checkFileExist(fileName));
        temp.process(data, fileWriter);
    }

    private void generateServiceImpl(Clazz clazz, Map<String, Object> data) throws IOException, TemplateException {
        BaseConfig baseConfig = propertiesUtil.getBaseConfig();
        Template temp = cfg.getTemplate(TemplateEnum.SERVICE_IMPL.path);
        String fileName = baseConfig.getServiceImplProject() + "/"
                + packageToPath(baseConfig.getServiceImplPackage()) +
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
