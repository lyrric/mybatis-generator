package com.github.lyrric.generator.enums;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/12 16:59
 */
public enum TemplateEnum {
    /**
     * 文件路径映射
     */
    ENTITY("entity.ftl"),
    MAPPER("mapper.ftl"),
    XML("xml.ftl"),
    SERVICE("service.ftl"),
    SERVICE_IMPL("serviceImpl.ftl"),
    ;
    public final String path;

    TemplateEnum(String path) {
        this.path = path;
    }


}
