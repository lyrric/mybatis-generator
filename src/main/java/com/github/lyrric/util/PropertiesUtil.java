package com.github.lyrric.util;

import com.github.lyrric.entity.config.*;
import com.github.lyrric.exception.MissArgumentException;
import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/12 16:49
 */
public class PropertiesUtil {

    private BaseConfig baseConfig;
    private DbConfig dbConfig;
    private LombokConfig lombokConfig;
    private MybatisPlusConfig mybatisPlusConfig;
    private SwaggerConfig swaggerConfig;
    private final MyProperties properties;

    public PropertiesUtil() throws IOException {
        properties = new MyProperties();
        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("generator-default.properties");
        properties.load(is);
        is = PropertiesUtil.class.getClassLoader().getResourceAsStream("generator.properties");
        properties.load(is);
    }

    public BaseConfig getBaseConfig() {
        if(baseConfig == null){
            baseConfig = new BaseConfig(properties);
        }
        return baseConfig;
    }

    public DbConfig getDbConfig() {
        if(dbConfig == null){
            dbConfig = new DbConfig(properties);
        }
        return dbConfig;
    }

    public LombokConfig getLombokConfig() {
        if(lombokConfig == null){
            lombokConfig = new LombokConfig(properties);
        }
        return lombokConfig;
    }

    public MybatisPlusConfig getMybatisPlusConfig() {
        if(mybatisPlusConfig == null){
            mybatisPlusConfig = new MybatisPlusConfig(properties);
        }
        return mybatisPlusConfig;
    }

    public SwaggerConfig getSwaggerConfig() {
        if(swaggerConfig == null){
            swaggerConfig = new SwaggerConfig(properties);
        }
        return swaggerConfig;
    }




}
