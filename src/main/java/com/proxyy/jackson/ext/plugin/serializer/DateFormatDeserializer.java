package com.proxyy.jackson.ext.plugin.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * 日期jackson反序列化器
 *
 * @author proxyy
 * @date 2022/3/20
 */
public class DateFormatDeserializer extends JsonDeserializer<Date> implements ContextualDeserializer {
    private String pattern;

    public DateFormatDeserializer() {
        super();
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        // TODO lihuan418: 2022/4/13  
        return null;
    }


    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        // TODO lihuan418: 2022/4/13  
        return null;
    }
}
