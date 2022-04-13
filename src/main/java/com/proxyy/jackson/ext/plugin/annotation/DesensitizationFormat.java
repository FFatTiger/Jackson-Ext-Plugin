package com.proxyy.jackson.ext.plugin.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proxyy.jackson.ext.plugin.eunm.DesensitizationType;
import com.proxyy.jackson.ext.plugin.eunm.SerializeType;
import com.proxyy.jackson.ext.plugin.serializer.DesensitizationFormatSerializer;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 敏感信息脱敏序列化，将对应的敏感属性脱敏
 *
 * @author proxyy
 * @date 2022/3/20
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Inherited
@JsonSerialize(using = DesensitizationFormatSerializer.class)
@JsonDeserialize
public @interface DesensitizationFormat {

    /**
     * 需要脱敏的属性类型
     *
     * @return 脱敏类型
     */
    DesensitizationType desensitizationType();


    /**
     * 序列化类型
     *
     * @return 序列化类型
     */
    SerializeType serializeType() default SerializeType.ONLY_SER;
}
