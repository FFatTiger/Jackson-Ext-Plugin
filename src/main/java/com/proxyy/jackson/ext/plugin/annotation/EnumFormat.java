package com.proxyy.jackson.ext.plugin.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proxyy.jackson.ext.plugin.eunm.SerializeType;
import com.proxyy.jackson.ext.plugin.serializer.EnumFormatSerializer;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 序列化时，将String入参与指定的枚举属性匹配，并替换为对应枚举
 * 反序列化时，将提取枚举中的指定属性返回
 *
 * @author proxyy
 * @date 2022/3/20
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Inherited
@JsonSerialize(using = EnumFormatSerializer.class)
@JsonDeserialize
public @interface EnumFormat {

    /**
     * 用于映射的枚举类属性名，该属性的值在枚举中一定唯一
     * 在反序列化时指定
     *
     * @return 格式字符串
     */
    String filedNameForMapping();

    /**
     * 用于展示的枚举类属性名
     * 在序列化时指定
     *
     * @return
     */
    String filedNameForShow();

    /**
     * 序列化类型
     *
     * @return
     */
    SerializeType serializeType() default SerializeType.ONLY_SER;
}
