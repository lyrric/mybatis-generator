package com.github.lyrric.generator.entity.config;

import com.github.lyrric.generator.util.MyProperties;
import lombok.Data;

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
