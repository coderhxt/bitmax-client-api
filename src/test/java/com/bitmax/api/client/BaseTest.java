package com.bitmax.api.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public class BaseTest {

    protected  <T> void toPrettyShow(T obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (Exception ignore) {
            ignore.printStackTrace();
            return;
        }
        System.out.println("\r\n" + json);
    }
}
