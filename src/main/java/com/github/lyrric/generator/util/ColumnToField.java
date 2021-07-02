package com.github.lyrric.generator.util;

import com.github.lyrric.generator.entity.Column;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 17:47
 */
public interface ColumnToField {

    /**
     * 类型转换
     * @param column 列
     * @return
     */
    Class<?> convert(Column column);
}
