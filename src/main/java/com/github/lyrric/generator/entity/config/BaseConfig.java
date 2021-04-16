package com.github.lyrric.generator.entity.config;

import com.github.lyrric.generator.util.MyConfigMap;
import lombok.Data;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/8 18:51
 */
@Data
public class BaseConfig {
    /** 是否生成 */
    private boolean enable;
    /** 生成项目 */
    private String project;
    /** package */
    private String packages;

    public BaseConfig(MyConfigMap config) {

    }
}
