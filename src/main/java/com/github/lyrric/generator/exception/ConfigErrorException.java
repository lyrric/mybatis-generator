package com.github.lyrric.generator.exception;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 14:03
 */
public class ConfigErrorException extends RuntimeException{

    public ConfigErrorException(String message) {
        super(message);
    }
}
