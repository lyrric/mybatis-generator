package com.github.lyrric.generator;

import com.github.lyrric.generator.enums.TemplateEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author wangxiaodong
 */
public abstract class BaseGenerator {

    /** 需要动态引入的类，解析extendClass得出 */
    protected Set<String> dynamicImports = new HashSet<>();
    /** 组装好的extend 字符串*/
    protected String extendClassStr;

    Configuration cfg;

    public BaseGenerator(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 生成文件
     * @throws IOException
     * @throws TemplateException
     */
    public abstract void generate() throws IOException, TemplateException;

    /**
     * 处理extendClassStr
     * @param configExtendClassStr 用户配置的extendClass
     * @param fullGenericsClazzName 泛型名称(全局限定名)     */
    protected void convertExtendClassStr(String configExtendClassStr, String fullGenericsClazzName){
        if(StringUtils.isNotBlank(configExtendClassStr)){
            List<String> classNames = new ArrayList<>();
            String[] classList = configExtendClassStr.split(",");
            for (String clazz : classList) {
                //去除空格
                do{
                    clazz = clazz.replaceAll(" ", "");
                }while (clazz.contains(" "));

                if(clazz.contains("<T>")){
                    //处理泛型
                    String simpleGenericsClazzName = getClassSimpleName(fullGenericsClazzName);
                    String importClass = clazz.replace("<T>", String.format("<%s>", simpleGenericsClazzName));
                    dynamicImports.add(fullGenericsClazzName);
                    clazz = clazz.replace("<T>","");
                    classNames.add(getClassSimpleName(importClass));
                }else{
                    classNames.add(getClassSimpleName(clazz));
                }
                dynamicImports.add(clazz);
            }
            this.extendClassStr = String.join(".", classNames);
        }
    }

    /**
     * 获取简单类名
     * @param fullClassName 全局限定名
     * @return 简单类名
     */
    protected String getClassSimpleName(String fullClassName){
        fullClassName = fullClassName.trim();
        String[] split = fullClassName.split("\\.");
        return split[split.length - 1];
    }

    /**
     * 渲染模板
     * @param template
     * @param fileName
     * @param data
     * @throws IOException
     * @throws TemplateException
     */
    protected void render(TemplateEnum template, String fileName, Map<String, Object> data) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(template.path);
        FileWriter fileWriter =
                new FileWriter(checkFileExist(fileName));
        temp.process(data, fileWriter);
    }

    protected String packageToPath(String pack){
        return pack.replaceAll("\\.", "/");
    }

    /**
     * 文件是否存在
     * @param fileName 存在则返回新的文件名
     * @return 存在则返回新的文件名
     */
    protected String checkFileExist(String fileName){
        File file = new File(fileName);
        if(file.exists()){
            //文件存在，生成副本
            fileName = fileName + ".bak";
        }else{
            //创建目录
            file.getParentFile().mkdirs();
        }
        return fileName;
    }
}
