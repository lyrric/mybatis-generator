package com.github.lyrric.entity.config;

import lombok.Data;

import java.util.Properties;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 14:21
 */
@Data
public class DbConfig {

    private String url;

    private String name;

    private String username;

    private String password;

    public DbConfig(Properties properties) {
        this.url = properties.getProperty("db.url");
        this.name = properties.getProperty("db.name");
        this.username = properties.getProperty("db.username");
        this.password = properties.getProperty("db.password");
    }
}
