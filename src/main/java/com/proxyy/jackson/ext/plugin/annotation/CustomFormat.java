package com.proxyy.jackson.ext.plugin.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义序列化
 *
 * @author proxyy
 * @date 2022/3/20
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Inherited
@JsonSerialize
@JsonDeserialize
public @interface CustomFormat {

    boolean toNumber() default false;

    boolean needSerialize() default true;

    boolean needSerializeWithType() default true;


}
