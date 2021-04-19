package com.github.lyrric.generator.entity.config;

import com.github.lyrric.generator.exception.MissArgumentException;
import com.github.lyrric.generator.util.MyConfigMap;
import com.github.lyrric.generator.util.MyProperties;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/8 18:51
 */
@Data
public class GeneratorConfig {

    /** 要生成的表名，多个表名以,逗号分隔 */
    private String table;
    /** 生成作者 */
    private String author;
    /** 实体类是否生成swagger注解，默认为true */
    private Boolean swagger;
    /** 是否支持mybatisPlus，默认为true */
    private Boolean mybatisPlus;

    private LombokConfig lombok;

    private BaseConfig entity;
    private BaseConfig mapper;
    private BaseConfig xml;
    private BaseConfig service;
    private BaseConfig serviceImpl;


    public GeneratorConfig(MyConfigMap config) {
        this.table = config.getString("table");
        this.author = config.getString("author");
        this.swagger = config.getBool("swagger");
        this.mybatisPlus = config.getBool("mybatisPlus");
        check();
        lombok = new LombokConfig("lombok", config.get("lombok"));
        entity = new BaseConfig("entity", config.get("entity"));
        mapper = new BaseConfig("mapper", config.get("mapper"));
        xml = new BaseConfig("xml", config.get("xml"));
        service = new BaseConfig("service", config.get("service"));
        serviceImpl = new BaseConfig("serviceImpl", config.get("serviceImpl"));
    }

    private void check(){
        if(StringUtils.isEmpty(table)){
            throw new MissArgumentException("generator.table");
        }
    }
}
