package com.proxyy.jackson.ext.plugin.eunm;

/**
 * 序列化类型
 *
 * @author proxyy
 * @date 2022/4/11
 */
public enum SerializeType {

    /**
     * 只进行不带类型序列化
     */
    ONLY_SER(true, false),

    /**
     * 只进行带类型序列化
     */
    ONLY_TYPE_SER(false, true),

    /**
     * ALL
     */
    ALL(true, true);


    /**
     * 是否需要使用指定注解进行序列化
     */
    private final boolean needSerialize;

    /**
     * 是否需要使用指定注解进行带类型序列化
     */
    private final boolean needSerializeWithType;

    SerializeType(boolean needSerialize, boolean needSerializeWithType) {
        this.needSerialize = needSerialize;
        this.needSerializeWithType = needSerializeWithType;
    }

    public boolean isNeedSerialize() {
        return needSerialize;
    }

    public boolean isNeedSerializeWithType() {
        return needSerializeWithType;
    }
}
