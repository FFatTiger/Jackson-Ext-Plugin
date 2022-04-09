package com.proxyy.jackson.ext.plugin.serializer;

import com.proxyy.jackson.ext.plugin.serializer.base.JacksonSerializerFilter;

/**
 * @author proxyy
 * @date 2022/3/20
 */
public interface CustomFormatSerializer<T> extends JacksonSerializerFilter {


    /**
     * 序列化基本类型实现
     *
     * @param originValue 原数据
     * @return format后的对象
     */
    T serializeContent(T originValue);
}
