package com.github.lyrric.entity.config;

import com.github.lyrric.util.MyProperties;
import lombok.Data;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/8 18:51
 */
@Data
public class BaseConfig {

    private String table;

    private String entityProject;
    private String entityPackage;

    private String mapperProject;
    private String mapperPackage;

    private String xmlProject;
    private String xmlPackage;

    private String serviceProject;
    private String servicePackage;

    private String serviceImplProject;
    private String serviceImplPackage;


    public BaseConfig(MyProperties properties) {
        this.table = properties.getString("generator.table");
        this.entityProject = properties.getString("generator.entity.project");
        this.entityPackage = properties.getString("generator.entity.package");
        this.mapperProject = properties.getString("generator.mapper.project");
        this.mapperPackage = properties.getString("generator.mapper.package");
        this.xmlProject = properties.getString("generator.xml.project");
        this.xmlPackage = properties.getString("generator.xml.package");
        this.serviceProject = properties.getString("generator.service.project");
        this.servicePackage = properties.getString("generator.service.package");
        this.serviceImplProject = properties.getString("generator.serviceImpl.project");
        this.serviceImplPackage = properties.getString("generator.serviceImpl.package");
    }


}
