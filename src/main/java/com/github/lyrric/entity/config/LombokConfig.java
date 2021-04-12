package com.github.lyrric.entity.config;

import com.github.lyrric.util.MyProperties;
import lombok.Data;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/12 15:50
 */
@Data
public class LombokConfig {
    /**
     * 是否启用mybatis-plus注解，默认为true
     */
    private boolean enable;
    /**
     * 是否启用@Data注解，默认为true
     */
    private boolean data;
    /**
     * 是否启用@NoArgsConstructor注解，默认为false
     */
    private boolean noArgsConstructor;
    /**
     * 是否启用@AllArgsConstructor注解，默认为false
     */
    private boolean allArgsConstructor;
    /**
     * 是否启用@Builder注解，默认为false
     */
    private boolean builder;

    public LombokConfig(MyProperties properties) {
        this.enable = properties.getBoolean("generator.lombok.enable");
        this.data = properties.getBoolean("generator.lombok.data");
        this.noArgsConstructor = properties.getBoolean("generator.lombok.noArgsConstructor");
        this.allArgsConstructor = properties.getBoolean("generator.lombok.allArgsConstructor");
        this.builder = properties.getBoolean("generator.lombok.builder");
    }


}
