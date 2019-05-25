package com.bitmax.api.client.impl;

import com.bitmax.api.client.domain.event.OrderUpdate;
import com.bitmax.api.client.util.ObjectMapperPlus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author xiaotian.huang
 * @date 2019-05-24
 */
public class BitmaxMsgHandler {

    private static final ObjectMapperPlus mapperPlus;

    static {
        mapperPlus = ObjectMapperPlus.newInstance();
    }

    public static void handleOrderUpdate(String msg) {
        JsonNode node = mapperPlus.findNode(msg, "m");
        if (node != null && "order".equals(node.asText())) {
            OrderUpdate orderUpdate = mapperPlus.readValue(msg, new TypeReference<OrderUpdate>(){});
            System.out.println(mapperPlus.writeValueAsString(orderUpdate));
        }
        System.out.println("msg: " + msg);
    }
}
