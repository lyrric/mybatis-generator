package com.github.lyrric.generator.entity.config;

import com.github.lyrric.generator.util.MyConfigMap;
import com.github.lyrric.generator.util.MyProperties;
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
    private Boolean enable;
    /**
     * 是否启用@Data注解，默认为true
     */
    private Boolean data;
    /**
     * 是否启用@NoArgsConstructor注解，默认为false
     */
    private Boolean noArgsConstructor;
    /**
     * 是否启用@AllArgsConstructor注解，默认为false
     */
    private Boolean allArgsConstructor;
    /**
     * 是否启用@Builder注解，默认为false
     */
    private Boolean builder;

    public LombokConfig(String type, MyConfigMap configMap) {
        this.enable = configMap.getBool("enable");
        this.data = configMap.getBool("data");
        this.noArgsConstructor = configMap.getBool("noArgsConstructor");
        this.allArgsConstructor = configMap.getBool("allArgsConstructor");
        this.builder = configMap.getBool("builder");
    }


}
