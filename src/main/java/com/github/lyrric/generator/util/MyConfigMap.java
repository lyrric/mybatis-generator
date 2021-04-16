package com.github.lyrric.generator.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/16 18:27
 */
public class MyConfigMap {

    /**
     * 自定义的配置
     */
    private final Map<String, Object> customMap;
    /**
     * 默认配置
     */
    private final Map<String, Object> defaultMap;

    public MyConfigMap(Map<String, Object> customMap, Map<String, Object> defaultMap) {
        this.customMap = customMap;
        this.defaultMap = defaultMap;
    }

    /**
     * 获取指定key的value
     * @param key
     * @return
     */
    public String getString(String key){
        String value = (String)customMap.get(key);
        if(StringUtils.isBlank(value)){
            return (String)defaultMap.get(key);
        }
        return value;
    }

    public Integer getInt(String key){
        String str = getString(key);
        return Integer.parseInt(str);
    }

    public Boolean getBool(String key){
        String str = getString(key);
        return Boolean.parseBoolean(str);
    }

    @SuppressWarnings("all")
    public MyConfigMap get(String key){
        Map<String, Object> customMap = (Map<String, Object>)this.customMap.get(key);
        customMap = customMap == null ? new HashMap<>() : customMap;
        Map<String, Object> defaultMap = (Map<String, Object>)this.defaultMap.get(key);
        defaultMap = defaultMap == null ? new HashMap<>() : defaultMap;
        return new MyConfigMap(customMap, defaultMap);
    }

}
