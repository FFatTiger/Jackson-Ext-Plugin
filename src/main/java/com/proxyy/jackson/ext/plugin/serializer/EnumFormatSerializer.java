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
 * 枚举序列化器
 *
 * @author proxyy
 * @date 2022/4/13 20:19
 */
public class EnumFormatSerializer extends BaseAbstractJacksonSerializer<Enum> {
    @Override
    public void serializeInternal(Enum value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // TODO lihuan418: 2022/4/13  
    }

    @Override
    public void serializeWithTypeInternal(Enum value, JsonGenerator gen, SerializerProvider serializerProvider, TypeSerializer typeSer) throws IOException {
        // TODO lihuan418: 2022/4/13  
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        // TODO lihuan418: 2022/4/13  
        return null;
    }
}
