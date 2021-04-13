package com.github.lyrric.generator.exception;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/12 17:32
 */
public class MissArgumentException extends RuntimeException{
    public MissArgumentException(String key) {
        super(String.format("缺失必要配置参数：%s", key));
    }
}
