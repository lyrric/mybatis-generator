package com.github.lyrric.util;

import com.github.lyrric.entity.Column;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/9 17:47
 */
public interface ColumnToField {

    /**
     * 类型转换
     * @param column
     * @return
     */
    Class<?> convert(Column column);
}
