package com.proxyy.jackson.ext.plugin.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.proxyy.jackson.ext.plugin.serializer.base.BaseAbstractJacksonSerializer;

import java.io.IOException;

/**
 * 敏感信息脱敏处理
 *
 * @author proxyy
 * @date 2022/4/13 20:09
 */
public class DesensitizationFormatSerializer extends BaseAbstractJacksonSerializer<String> {

    @Override
    public void serializeInternal(String value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // TODO proxyy: 2022/4/13  
    }


    @Override
    public JsonSerializer<?> getInstance(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        // TODO proxyy: 2022/4/13  
        return null;
    }
}
