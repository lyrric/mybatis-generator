package com.github.lyrric.entity.config;

import com.github.lyrric.util.MyProperties;
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

    public DbConfig(MyProperties properties) {
        this.url = properties.getString("db.url");
        this.name = properties.getString("db.name");
        this.username = properties.getString("db.username");
        this.password = properties.getString("db.password");
    }
}
