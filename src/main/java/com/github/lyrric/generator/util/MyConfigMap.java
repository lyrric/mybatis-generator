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
        Object object = customMap.get(key);
        if(object != null){
            return object.toString();
        }else{
            object = defaultMap.get(key);
            return object == null ? null : object.toString();
        }
    }

    public String getFromCustom(String key){
        String value;
        if(customMap.containsKey(key)){
            value = (String) customMap.get(key);
        }else{
            value =  (String)defaultMap.get(key);
        }
        return value;
    }

    public Integer getInt(String key){
        Integer value;
        if(customMap.containsKey(key)){
            value = (Integer) customMap.get(key);
        }else{
            value =  (Integer)defaultMap.get(key);
        }
        return value;
    }

    public Boolean getBool(String key){
        Boolean value;
        if(customMap.containsKey(key)){
            value = (Boolean) customMap.get(key);
        }else{
            value =  (Boolean)defaultMap.get(key);
        }
        return value;
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
