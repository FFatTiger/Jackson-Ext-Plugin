package com.proxyy.jackson.ext.plugin.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proxyy.jackson.ext.plugin.serializer.CustomFormatSerializer;
import com.proxyy.jackson.ext.plugin.serializer.CustomFormatter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义序列化，通过实现 {@link CustomFormatter}，赋值至 {@link #serializer()}，以实现对属性值的自定义序列化
 *
 * @author proxyy
 * @date 2022/3/20
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Inherited
@JsonSerialize(using = CustomFormatSerializer.class)
//@JsonDeserialize(using = CustomFormatDeserializerProcessor.class)
@SuppressWarnings("rawtypes")
public @interface TemplateFormat {

    /**
     * 是否要将结果转换为数值型，默认字符型
     *
     * @return boolean
     */
    boolean toNumber() default false;


    /**
     * 自定义序列化类
     *
     * @return 自定义序列化器class
     */
    Class<? extends CustomFormatter> serializer();

}
