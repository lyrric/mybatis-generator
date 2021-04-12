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
public class MybatisPlusConfig {
    /**
     * 是否启用mybatis-plus注解，默认为true
     */
    private boolean enable;

    public MybatisPlusConfig(MyProperties properties) {
        this.enable = properties.getBoolean("generator.mybatisPlus.enable");
    }
}
