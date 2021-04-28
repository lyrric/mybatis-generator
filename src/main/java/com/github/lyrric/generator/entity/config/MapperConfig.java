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
public class MapperConfig{

    private final String top = "generator";

    private final String type;
    /** 是否生成 */
    private final Boolean enable;
    /** 生成项目 */
    private final String project;
    /** package */
    private final String packages;
    /** 接口 */
    private String extendClass;

    /** 需要动态引入的类，解析implementClass得出 */
    private final List<String> dynamicImports = new ArrayList<>();

    public MapperConfig(String type, MyConfigMap configMap) {
        this.type = type;
        this.enable = configMap.getBool("enable");
        this.project = configMap.getString("project");
        this.packages = configMap.getString("package");
        dealExtendClass(configMap.getString("extendClass"));
    }

    /**
     * 处理依赖
     */
    private void dealExtendClass(String extendClass){
        if(StringUtils.isNotBlank(extendClass)){
            List<String> classNames = new ArrayList<>();
            String[] classList = extendClass.split(",");
            for (String clazz : classList) {
                String[] split = clazz.split("\\.");
                dynamicImports.add(clazz);
                classNames.add(split[split.length-1]);
            }
            this.extendClass = String.join(".", classNames);
        }
    }
}
