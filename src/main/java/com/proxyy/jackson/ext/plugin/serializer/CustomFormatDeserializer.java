package com.proxyy.jackson.ext.plugin.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.proxyy.jackson.ext.plugin.serializer.base.BaseAbstractJacksonSerializer;

import java.io.IOException;

/**
 * 自定义反序列化器
 * @author proxyy
 * @date 2022/3/20
 */
public class CustomFormatDeserializer extends BaseAbstractJacksonSerializer {
    @Override
    public void serializeInternal(Object value, JsonGenerator gen, SerializerProvider serializerProvider) {

    }

    @Override
    public void serializeWithTypeInternal(Object value, JsonGenerator gen, SerializerProvider serializerProvider, TypeSerializer typeSer) throws IOException {

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        return null;
    }
}
