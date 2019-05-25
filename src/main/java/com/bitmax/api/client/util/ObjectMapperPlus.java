package com.bitmax.api.client.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author xiaotian.huang
 * @date 2019-05-24
 */
public class ObjectMapperPlus extends ObjectMapper {

    private static final ObjectMapperPlus mapperPlus;

    static {
        mapperPlus = new ObjectMapperPlus();
    }

    private ObjectMapperPlus() {
    }

    public static ObjectMapperPlus newInstance() {
        if (mapperPlus != null) {
            return mapperPlus;
        } else {
            throw new RuntimeException("plus实例化失败");
        }
    }

    @Override
    public String writeValueAsString(Object value) {
        String json;
        try {
            json = super.writeValueAsString(value);
        } catch (IOException e) {
            throw new RuntimeException("ObjectMapper序列化异常", e);
        }
        return json;
    }

    @Override
    public <T> T readValue(String content, TypeReference valueTypeRef) {
        T t;
        try {
            t = super.readValue(content, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException("ObjectMapper反序列化异常",e);
        }
        return t;
    }

    public JsonNode findNode(String content, String key) {
        try {
            JsonNode node = super.readTree(content);
            return node.findValue(key);
        } catch (IOException e) {
            throw new RuntimeException("ObjectMapper操作抛出异常", e);
        }
    }
}
