package com.github.lyrric.util;

import com.github.lyrric.exception.MissArgumentException;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/12 17:37
 */
public class MyProperties extends Properties {

    public MyProperties() {
        super();
    }
    /**
     * 获取String类型数据
     * 获取不到抛出错误
     * @param key
     * @return
     */
    public String getString(String key){
        String value = super.getProperty(key);
        if(StringUtils.isNotEmpty(value)){
            return value;
        }
        throw new MissArgumentException(key);
    }

    /**
     * 获取boolean类型数据
     * @param key
     * @return
     */
    public boolean getBoolean(String key){
        return Boolean.parseBoolean(getString(key));
    }

}
