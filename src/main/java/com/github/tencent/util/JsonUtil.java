package com.github.tencent.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * json util.
 *
 * @author changjiangtang
 * @since 2020-09-15 11:20:15
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        try {
            StringWriter writer = new StringWriter();
            OBJECT_MAPPER.writeValue(writer, obj);
            return writer.toString();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            InputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
            return OBJECT_MAPPER.readValue(is, clazz);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public static <T> Object parseObject(String json, TypeReference<T> typeRef) {
        try {
            InputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
            return OBJECT_MAPPER.readValue(is, typeRef);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
