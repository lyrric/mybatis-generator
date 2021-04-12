package com.github.lyrric.entity.config;

import com.github.lyrric.util.MyProperties;
import lombok.Data;

import java.util.Properties;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/12 15:42
 */
@Data
public class SwaggerConfig {

    /**
     * 是否启用swagger注解
     */
    private boolean enable;

    public SwaggerConfig(MyProperties properties) {
        this.enable = properties.getBoolean("generator.swagger.enable");
    }
}
