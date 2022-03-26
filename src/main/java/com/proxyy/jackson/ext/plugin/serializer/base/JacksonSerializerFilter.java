package com.proxyy.jackson.ext.plugin.serializer.base;

/**
 * jackson 序列化过滤器
 * @author proxyy
 * @date 2022/3/20
 */
public interface JacksonSerializerFilter {

    /**
     * 是否需要执行对应过滤器
     * @param currentBean 当前属性所在类
     * @return
     */
    default boolean shouldSerialize(Object currentBean) {return true;}
}
