package com.github.lyrric.generator.entity.config;

import com.github.lyrric.generator.util.MyConfigMap;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/21 17:13
 */
@Getter
public class EntityConfig{


    protected final String top = "generator";

    protected final String type;
    /** 是否生成 */
    private final Boolean enable;
    /** 生成项目 */
    private final String project;
    /** package */
    private final String packages;
    /** 基类 */
    private final String extendClass;
    /** 忽略字段 */
    private List<String> ignoredColumns = new ArrayList<>();

    public EntityConfig(String type, MyConfigMap config) {
        this.type = type;
        this.enable = config.getBool("enable");
        this.project = config.getString("project");
        this.packages = config.getString("package");
        this.extendClass = config.getString("extendClass");
        String ignoredColumns = config.getString("ignoredColumns");
        if (StringUtils.isNotBlank(ignoredColumns)) {
            this.ignoredColumns = Arrays.asList(ignoredColumns.split(","));
        }
        check();
    }

    private void check(){

    }
}
