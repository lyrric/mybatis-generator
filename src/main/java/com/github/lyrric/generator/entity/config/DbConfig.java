package com.github.lyrric.generator.entity.config;

import com.github.lyrric.generator.exception.ConfigErrorException;
import com.github.lyrric.generator.exception.MissArgumentException;
import com.github.lyrric.generator.util.MyConfigMap;
import com.github.lyrric.generator.util.MyProperties;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 14:21
 */
@Data
public class DbConfig {

    private String url;

    private String username;

    private String password;

    public DbConfig(MyConfigMap map) {
        this.url = map.getString("url");
        this.username = map.getString("username");
        this.password = map.getString("password");
        check();
    }


    private void check(){
        if(StringUtils.isBlank(url)){
            throw new MissArgumentException("db.url");
        }
        if(StringUtils.isBlank(username)){
            throw new MissArgumentException("db.username");
        }
    }
}
