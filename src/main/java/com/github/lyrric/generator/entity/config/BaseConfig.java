package com.github.lyrric.generator.entity.config;

import com.github.lyrric.generator.exception.MissArgumentException;
import com.github.lyrric.generator.util.MyConfigMap;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/8 18:51
 */
@Data
public class BaseConfig {

    private final String top = "generator";

    private final String type;
    /** 是否生成 */
    private Boolean enable;
    /** 生成项目 */
    private String project;
    /** package */
    private String packages;

    public BaseConfig(String type, MyConfigMap config) {
        this.type = type;
        this.enable = config.getBool("enable");
        this.project = config.getString("project");
        this.packages = config.getString("package");
        check();
    }

    private void check(){
        if(enable){
            if (StringUtils.isBlank(project)) {
                throw new MissArgumentException(top + "." + type + ".project");
            }
            if (StringUtils.isBlank(packages)) {
                throw new MissArgumentException(top + "." + type + ".packages");
            }
        }
    }
}
