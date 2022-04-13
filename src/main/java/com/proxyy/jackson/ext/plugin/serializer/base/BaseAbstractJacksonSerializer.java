package com.proxyy.jackson.ext.plugin.serializer.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * jackson序列化基类
 * @author proxyy
 * @date 2022/3/20
 */
public abstract class BaseAbstractJacksonSerializer<T> extends JsonSerializer<T> implements ContextualSerializer {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private JacksonSerializeFilter jacksonSerializeFilter;

    /**
     * 空参构造用于提供给jackson创建bean
     */
    protected BaseAbstractJacksonSerializer() {
    }

    protected BaseAbstractJacksonSerializer(JacksonSerializeFilter jacksonSerializeFilter) {
        this.jacksonSerializeFilter = jacksonSerializeFilter;
    }

    @Override
    public final void serialize(T value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (jacksonSerializeFilter == null || jacksonSerializeFilter.shouldSerialize(value)) {
            serializeInternal(value, jsonGenerator, serializerProvider);
            return;
        }

        JsonSerializer<Object> jacksonInternalSerializer = serializerProvider.findValueSerializer(value.getClass());
        Objects.requireNonNull(jacksonInternalSerializer, value.getClass().getSimpleName() + " has no corresponding serializer");
        jacksonInternalSerializer.serialize(value,jsonGenerator, serializerProvider);
    }

    public abstract void serializeInternal(T value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException;

    @Override
    public final void serializeWithType(T value, JsonGenerator gen, SerializerProvider serializerProvider, TypeSerializer typeSer) throws IOException {
        if (jacksonSerializeFilter == null || jacksonSerializeFilter.shouldSerializeWithType(gen.getCurrentValue())) {
            gen.writeStartArray();
            gen.writeString(value.getClass().getName());
            serializeWithTypeInternal(value, gen, serializerProvider, typeSer);
            gen.writeEndArray();
            return;
        }

        JsonSerializer<Object> jacksonInternalSerializer = serializerProvider.findValueSerializer(value.getClass());
        Objects.requireNonNull(jacksonInternalSerializer, value.getClass().getSimpleName() + " has no corresponding serializer");
        jacksonInternalSerializer.serializeWithType(value, gen, serializerProvider, typeSer);
    }

    public abstract void serializeWithTypeInternal(T value, JsonGenerator gen, SerializerProvider serializerProvider, TypeSerializer typeSer) throws IOException;



}
