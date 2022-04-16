package com.proxyy.jackson.ext.plugin.serializer.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.proxyy.jackson.ext.plugin.eunm.SerializeType;
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


    protected BaseAbstractJacksonSerializer(SerializeType serializeType) {
        this.jacksonSerializeFilter = JacksonSerializeFilter.Default.create(serializeType);
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
    @Override
    public final void serializeWithType(T value, JsonGenerator gen, SerializerProvider serializerProvider, TypeSerializer typeSer) throws IOException {
        if (jacksonSerializeFilter == null || jacksonSerializeFilter.shouldSerializeWithType(gen.getCurrentValue())) {
            gen.writeStartArray();
            gen.writeString(value.getClass().getName());
            serializeInternal(value, gen, serializerProvider);
            gen.writeEndArray();
            return;
        }

        JsonSerializer<Object> jacksonInternalSerializer = serializerProvider.findValueSerializer(value.getClass());
        Objects.requireNonNull(jacksonInternalSerializer, value.getClass().getSimpleName() + " has no corresponding serializer");
        jacksonInternalSerializer.serializeWithType(value, gen, serializerProvider, typeSer);
    }

    /**
     * simpleSerialize
     *
     * @param value              Value to serialize; can <b>not</b> be null.
     * @param gen                Generator used to output resulting Json content
     * @param serializerProvider Provider that can be used to get serializers for
     *                           serializing Objects value contains, if any.
     * @throws IOException
     */
    public abstract void serializeInternal(T value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException;


    @Override
    public final JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            return getInstance(prov, property);
        }
        return null;
    }


    /**
     * 与createContextual相同
     *
     * @param prov     Serializer provider to use for accessing config, other serializers
     * @param property Method or field that represents the property
     *                 (and is used to access value to serialize).
     *                 Should be available; but there may be cases where caller cannot provide it and
     *                 null is passed instead (in which case impls usually pass 'this' serializer as is)
     * @return Serializer to use for serializing values of specified property;
     * may be this instance or a new instance.
     * @throws JsonMappingException
     */
    public abstract JsonSerializer<?> getInstance(SerializerProvider prov, BeanProperty property) throws JsonMappingException;
}
