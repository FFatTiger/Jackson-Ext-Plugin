//package com.proxyy.jackson.ext.plugin.serializer;
//
//import com.fasterxml.jackson.core.JacksonException;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
//import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
//import com.proxyy.jackson.ext.plugin.serializer.base.BaseAbstractJacksonSerializer;
//
//import java.io.IOException;
//
///**
// * 自定义反序列化器
// * @author proxyy
// * @date 2022/3/20
// */
//public class CustomFormatDeserializerProcessor extends JsonDeserializer implements ContextualDeserializer {
//    @Override
//    public void serializeInternal(Object value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
//        // TODO proxyy: 2022/4/9 反序列化待实现
//        serializerProvider.findValueSerializer(value.getClass()).serialize(value, gen, serializerProvider);
//
//    }
//
//    @Override
//    public void serializeWithTypeInternal(Object value, JsonGenerator gen, SerializerProvider serializerProvider, TypeSerializer typeSer) throws IOException {
//        // TODO proxyy: 2022/4/9 反序列化待实现
//        serializerProvider.findValueSerializer(value.getClass()).serializeWithType(value, gen, serializerProvider, typeSer);
//    }
//
//    @Override
//    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
//        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
//    }
//
//    @Override
//    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
//        // TODO proxyy: 2022/4/9 反序列化待实现
//        serializerProvider.findValueSerializer(value.getClass()).serialize(value, gen, serializerProvider);
//
//    }
//
//    @Override
//    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
//        return null;
//    }
//}
