package com.bitmax.api.client.impl;

import com.bitmax.api.client.BitmaxApiCallBack;
import com.bitmax.api.client.constant.BitmaxApiConstants;
import com.bitmax.api.client.domain.WebSocketType;
import com.bitmax.api.client.domain.event.CountPong;
import com.bitmax.api.client.domain.event.SubscribeConfig;
import com.bitmax.api.client.exception.BitmaxApiException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.IOException;

import static com.bitmax.api.client.constant.BitmaxApiConstants.pongCounter;

/**
 * @author xiaotian.huang
 * @date 2019-05-21
 */
public class BitmaxApiWebSocketListener extends WebSocketListener {

    private BitmaxApiCallBack callBack;

    private String symbol;

    private WebSocketType webSocketType;

    private String subscribeMessage;

    private boolean isSubed;

    private static final ObjectMapper mapper = new ObjectMapper();

    private boolean closing = false;

//    private final ObjectReader objectReader;

    public BitmaxApiWebSocketListener(WebSocketType webSocketType, String symbol, SubscribeConfig config, BitmaxApiCallBack callBack) {
        this.webSocketType = webSocketType;
        this.symbol = symbol;
        this.callBack = callBack;
        String json;
        try {
            json = mapper.writeValueAsString(config);
        } catch (IOException e) {
            throw new BitmaxApiException("订阅信息体序列化失败", e);
        }
        this.subscribeMessage = json;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        if (WebSocketType.PUBLIC == webSocketType && !isSubed) {
            webSocket.send(subscribeMessage);
            isSubed = true;
        }
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(text);
        } catch (IOException e) {
            throw new BitmaxApiException("响应解析出错");
        }
        JsonNode messageType = jsonNode.get("m");
        if ("pong".equals(messageType.asText())) {
            CountPong countPong = pongCounter.get(buildKey());
            if (countPong == null) {
                CountPong counter = new CountPong(webSocket, 1);
                pongCounter.put(buildKey(), counter);
            } else {
                countPong.increase();
            }
            return;
        }
        callBack.onResponse(text);
    }

    private String buildKey() {
        return webSocketType.name() + ":" + symbol;
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        closing = true;
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        if (!closing) {
            System.out.println("连接失败, 稍后重试!");
            callBack.onFailure(t);
        }
    }
}
