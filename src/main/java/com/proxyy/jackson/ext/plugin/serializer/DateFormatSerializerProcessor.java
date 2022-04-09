package com.proxyy.jackson.ext.plugin.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.proxyy.jackson.ext.plugin.annotation.CustomFormat;
import com.proxyy.jackson.ext.plugin.serializer.base.BaseAbstractJacksonSerializer;
import com.proxyy.jackson.ext.plugin.serializer.base.JacksonSerializerFilter;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 自定义jackson序列化器
 * @author proxyy
 * @date 2022/3/20
 */
@SuppressWarnings("rawtypes")
public class DateFormatSerializerProcessor extends BaseAbstractJacksonSerializer {
    private boolean toNumber;

    CustomFormatSerializer<Object> customFormatSerializer;
    public DateFormatSerializerProcessor() {
        super();
    }

    public DateFormatSerializerProcessor(JacksonSerializerFilter jacksonSerializerFilter, boolean toNumber) {
        super(jacksonSerializerFilter);
        this.toNumber = toNumber;
    }

    @Override
    public void serializeInternal(Object value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        serializeValue(value, gen);
    }

    @Override
    public void serializeWithTypeInternal(Object value, JsonGenerator gen, SerializerProvider serializerProvider, TypeSerializer typeSer) throws IOException {
        serializeValue(value, gen);
    }

    private void serializeValue(Object value, JsonGenerator generator) throws IOException {
        value = this.customFormatSerializer.serializeContent(value);

        if (toNumber) {
            generator.writeNumber(value instanceof BigDecimal ? ((BigDecimal) value).toPlainString() : value.toString());
            return;
        }
        generator.writeString(value instanceof BigDecimal ? ((BigDecimal) value).toPlainString() : value.toString());
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            CustomFormat customFormat = beanProperty.getAnnotation(CustomFormat.class);

            if (customFormat != null) {
                try {
                    return new DateFormatSerializerProcessor(
                            customFormat.serializer().newInstance(),
                            customFormat.toNumber()
                    );
                } catch (InstantiationException | IllegalAccessException e) {
                    log.error("serializer has no null-argument constructor：", e);
                    return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
                }
            }
        }

        return serializerProvider.findNullValueSerializer(beanProperty);
    }




}