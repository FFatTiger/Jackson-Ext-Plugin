package com.proxyy.jackson.ext.plugin.serializer.base;

import com.proxyy.jackson.ext.plugin.eunm.SerializeType;

/**
 * jackson 序列化过滤器
 *
 * @author proxyy
 * @date 2022/3/20
 */
public interface JacksonSerializeFilter {

    /**
     * 是否需要执行对应过滤器
     *
     * @param currentBean 当前属性所在类
     * @return boolean
     */
    default boolean shouldSerialize(Object currentBean) {
        return true;
    }


    /**
     * 是否需要执行对应过滤器
     *
     * @param currentBean 当前属性所在类
     * @return boolean
     */
    default boolean shouldSerializeWithType(Object currentBean) {
        return true;
    }


    class Default implements JacksonSerializeFilter {
        public static JacksonSerializeFilter create(final boolean needSerialize, final boolean needSerializeWithType) {
            return new JacksonSerializeFilter() {
                @Override
                public boolean shouldSerialize(Object currentBean) {
                    return needSerialize;
                }

                @Override
                public boolean shouldSerializeWithType(Object currentBean) {
                    return needSerializeWithType;
                }
            };
        }


        public static JacksonSerializeFilter create(SerializeType serializeType) {
            return create(serializeType.isNeedSerialize(), serializeType.isNeedSerializeWithType());
        }
    }
}
