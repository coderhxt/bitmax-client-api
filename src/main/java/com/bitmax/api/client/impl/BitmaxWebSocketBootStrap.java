package com.bitmax.api.client.impl;

import com.bitmax.api.client.BitmaxApiCallBack;
import com.bitmax.api.client.BitmaxApiWebSocketClient;
import com.bitmax.api.client.BitmaxLogCallBack;
import com.bitmax.api.client.domain.WebSocketType;
import com.bitmax.api.client.domain.event.SubscribeConfig;
import com.bitmax.api.client.exception.BitmaxApiException;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.bitmax.api.client.constant.BitmaxApiConstants.localCounter;
import static com.bitmax.api.client.constant.BitmaxApiConstants.pongCounter;

/**
 * @author xiaotian.huang
 * @date 2019-05-21
 */
public class BitmaxWebSocketBootStrap {

    private BitmaxApiWebSocketClient bitmaxApiWebSocketClient;

    private String symbol;

    private SubscribeConfig config;

    private Integer accountGroup;

    private WebSocketType webSocketType;

    private BitmaxApiCallBack callBack;

    private BitmaxLogCallBack logCallBack;

    public BitmaxWebSocketBootStrap(WebSocketType webSocketType, BitmaxApiWebSocketClient bitmaxApiWebSocketClient,
                                    String symbol, SubscribeConfig config, Integer accountGroup, BitmaxApiCallBack callBack, BitmaxLogCallBack logCallBack) {
        this.webSocketType = webSocketType;
        this.bitmaxApiWebSocketClient = bitmaxApiWebSocketClient;
        this.symbol = symbol;
        this.config = config;
        this.accountGroup = accountGroup;
        this.callBack = callBack;
        this.logCallBack = logCallBack;
    }

    public void start() {
        switch (webSocketType) {
            case PUBLIC: {
                if (config == null) {
                    throw new BitmaxApiException("public websocket订阅, 订阅配置SubscribeConfig不能为null");
                }
                bitmaxApiWebSocketClient.onPublicEvent(symbol, config, callBack);
                break;
            }
            case ORDER: {
                if (accountGroup == null) {
                    throw new BitmaxApiException("order websocket订阅, 订阅配置accountGroup不能为null");
                }
                bitmaxApiWebSocketClient.onOrderManagementEvent(accountGroup, symbol, callBack);
                break;
            }
        }
        localCounter.put(buildKey(), 1L);
        checkPong();

    }

    private void restart() {
        start();
    }

    private void checkPong() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            Long localValue = localCounter.get(buildKey());
            if (localValue.longValue() == pongCounter.get(buildKey()).getCount()) {
                logCallBack.printLog(buildKey() + "一分钟没有收到pong, 服务端断开准备重连");
                pongCounter.get(buildKey()).getWebSocket().close(1000, null);
                pongCounter.get(buildKey()).reset();
                //
                restart();
                logCallBack.printLog(buildKey() + "重新订阅");
            } else {
                localValue = pongCounter.get(buildKey()).getCount();
                localCounter.put(buildKey(), localValue);
                logCallBack.printLog(buildKey() + "收到服务端pong次数: " + localValue);
            }
        }, 20, 15, TimeUnit.SECONDS);
    }

    private String buildKey() {
        return webSocketType + ":" + symbol;
    }
}
