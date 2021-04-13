package com.github.lyrric.generator.entity.config;

import com.github.lyrric.generator.util.MyProperties;
import lombok.Data;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/8 18:51
 */
@Data
public class BaseConfig {

    /** 需要生成的表 */
    private String table;

    /** 是否生成entity，默认为true */
    private boolean entityEnable;
    /** entity生成项目，默认为src/main/java */
    private String entityProject;
    /** entity package */
    private String entityPackage;

    /** 是否生成mapper，默认为true */
    private boolean mapperEnable;
    /** mapper生成项目，默认为src/main/java */
    private String mapperProject;
    /** mapper package */
    private String mapperPackage;

    /** 是否生成xml，默认为true */
    private boolean xmlEnable;
    /** xml生成项目，默认为src/main/java */
    private String xmlProject;
    /** xml生成位置，默认为resources/mapper */
    private String xmlPackage;

    /** 是否生成service，默认为false */
    private boolean serviceEnable;
    /** service生成项目，默认为src/main/java */
    private String serviceProject;
    /** service package */
    private String servicePackage;

    /** 是否生成serviceImpl，默认为false */
    private boolean serviceImplEnable;
    /** serviceImpl生成项目，默认为src/main/java */
    private String serviceImplProject;
    /** serviceImpl package */
    private String serviceImplPackage;


    public BaseConfig(MyProperties properties) {
        this.table = properties.getString("generator.table");
        this.entityEnable = properties.getBoolean("generator.entity.enable");
        this.entityProject = properties.getString("generator.entity.project");
        this.entityPackage = properties.getString("generator.entity.package");

        this.mapperEnable = properties.getBoolean("generator.mapper.enable");
        this.mapperProject = properties.getString("generator.mapper.project");
        this.mapperPackage = properties.getString("generator.mapper.package");

        this.xmlEnable = properties.getBoolean("generator.xml.enable");
        this.xmlProject = properties.getString("generator.xml.project");
        this.xmlPackage = properties.getString("generator.xml.package");

        this.serviceEnable = properties.getBoolean("generator.service.enable");
        this.serviceProject = properties.getString("generator.service.project");
        this.servicePackage = properties.getString("generator.service.package");

        this.serviceImplEnable = properties.getBoolean("generator.serviceImpl.enable");
        this.serviceImplProject = properties.getString("generator.serviceImpl.project");
        this.serviceImplPackage = properties.getString("generator.serviceImpl.package");
    }


}
