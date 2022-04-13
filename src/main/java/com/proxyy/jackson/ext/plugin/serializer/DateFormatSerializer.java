package com.proxyy.jackson.ext.plugin.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.proxyy.jackson.ext.plugin.annotation.DateFormat;
import com.proxyy.jackson.ext.plugin.eunm.SerializeType;
import com.proxyy.jackson.ext.plugin.serializer.base.BaseAbstractJacksonSerializer;
import com.proxyy.jackson.ext.plugin.serializer.base.JacksonSerializeFilter;
import com.proxyy.jackson.ext.plugin.util.DateUtil;

import java.io.IOException;
import java.util.Date;

/**
 * 日期jackson序列化器
 *
 * @author proxyy
 * @date 2022/3/20
 */
public class DateFormatSerializer extends BaseAbstractJacksonSerializer<Date> {
    private String pattern;

    public DateFormatSerializer() {
        super();
    }

    public DateFormatSerializer(String pattern, final SerializeType serializeType) {
        super(new JacksonSerializeFilter() {
            @Override
            public boolean shouldSerialize(Object currentBean) {
                return serializeType.isNeedSerialize();
            }

            @Override
            public boolean shouldSerializeWithType(Object currentBean) {
                return serializeType.isNeedSerializeWithType();
            }
        });
        this.pattern = pattern;
    }

    @Override
    public void serializeInternal(Date value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeString(DateUtil.parseDateToStr(value, pattern));
    }

    @Override
    public void serializeWithTypeInternal(Date value, JsonGenerator gen, SerializerProvider serializerProvider, TypeSerializer typeSer) throws IOException {
        gen.writeString(DateUtil.parseDateToStr(value, pattern));
    }


    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null && beanProperty.getType().getRawClass().equals(Date.class)) {

            DateFormat dateFormat = beanProperty.getAnnotation(DateFormat.class);
            if (dateFormat != null) {
                return new DateFormatSerializer(
                        dateFormat.pattern(),
                        dateFormat.serializeType()
                );
            }
        }

        return serializerProvider.findNullValueSerializer(beanProperty);
    }


}
