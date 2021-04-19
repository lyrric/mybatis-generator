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
    private final Map<String, ?> customMap;
    /**
     * 默认配置
     */
    private final Map<String, ?> defaultMap;

    public MyConfigMap(Map<String, ?> customMap, Map<String, ?> defaultMap) {
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
        Integer value = (Integer)customMap.get(key);
        if(value == null){
            return (Integer)defaultMap.get(key);
        }
        return value;
    }

    public Boolean getBool(String key){
        Boolean value = (Boolean)customMap.get(key);
        if(value == null){
            value = (Boolean) defaultMap.get(key);
        }
        return value != null && value;
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
