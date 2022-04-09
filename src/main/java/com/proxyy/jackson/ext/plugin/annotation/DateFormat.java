package com.proxyy.jackson.ext.plugin.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proxyy.jackson.ext.plugin.eunm.DateFormatPattern;
import com.proxyy.jackson.ext.plugin.serializer.CustomFormatSerializer;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 日期序列化，将 {@link java.util.Date} 、 {@link java.time.LocalDate}或{@link java.time.LocalDateTime}转换为预期格式的日期字符串
 * 支持上述类型序列化为字符串，与将字符串序列化为Date
 * @author proxyy
 * @date 2022/3/20
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Inherited
@JsonSerialize
@JsonDeserialize
public @interface DateFormat {

    /**
     * 预期的日期格式
     * @return 格式字符串
     */
    String pattern();

}
