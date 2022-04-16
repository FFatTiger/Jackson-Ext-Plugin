package com.proxyy.jackson.ext.plugin.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.proxyy.jackson.ext.plugin.annotation.EnumFormat;
import com.proxyy.jackson.ext.plugin.eunm.SerializeType;
import com.proxyy.jackson.ext.plugin.serializer.base.BaseAbstractJacksonSerializer;
import org.springframework.cglib.core.ReflectUtils;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

/**
 * 枚举序列化器
 *
 * @author proxyy
 * @date 2022/4/13 20:19
 */
@SuppressWarnings("rawtypes")
public class EnumFormatSerializer extends BaseAbstractJacksonSerializer<Enum> {

    private String filedNameForMapping;
    private String filedNameForShow;


    public EnumFormatSerializer() {
        super();
    }

    public EnumFormatSerializer(String filedNameForMapping, String filedNameForShow, SerializeType serializeType) {
        super(serializeType);
        this.filedNameForMapping = filedNameForMapping;
        this.filedNameForShow = filedNameForShow;
    }

    @Override
    public void serializeInternal(Enum value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {

        Optional<PropertyDescriptor> getter = Arrays.stream(ReflectUtils.getBeanGetters(value.getClass())).filter(p -> p.getName().equals(filedNameForShow)).findAny();

        if (getter.isPresent()) {
            try {
                Object showField = getter.get().getReadMethod().invoke(value);
                // TODO proxyy: 2022/4/16 预防getter获取到的对象不是基本类型
                gen.writeString(showField.toString());
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("getter invoke failed");
                throw JsonMappingException.from(serializerProvider, "getter invoke failed", e);
            }
        }
        throw JsonMappingException.from(serializerProvider, "Display field not found getter");
    }


    @Override
    public JsonSerializer<?> getInstance(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        EnumFormat enumFormat = property.getAnnotation(EnumFormat.class);
        if (enumFormat != null) {
            return new EnumFormatSerializer(enumFormat.filedNameForMapping(), enumFormat.filedNameForShow(), enumFormat.serializeType());
        }
        return null;
    }
}
