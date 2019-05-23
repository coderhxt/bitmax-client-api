package com.bitmax.api.client.websocket;

import com.bitmax.api.client.BitmaxApiClientFactory;
import com.bitmax.api.client.BitmaxApiWebSocketClient;
import com.bitmax.api.client.domain.WebSocketType;
import com.bitmax.api.client.domain.event.SubscribeConfig;
import com.bitmax.api.client.impl.BitmaxWebSocketBootStrap;

/**
 * @author xiaotian.huang
 * @date 2019-05-21
 */
public class BitmaxPublicWebSocketExample {

    public static void main(String[] args) {
        BitmaxApiWebSocketClient bitmaxApiWebSocketClient = BitmaxApiClientFactory.newInstance().newWebSocketClient();
        String symbol = "ETH-BTC";
        SubscribeConfig config = new SubscribeConfig(20, 20, false, false);
        // 订阅 public
        BitmaxWebSocketBootStrap bootStrap = new BitmaxWebSocketBootStrap(WebSocketType.PUBLIC, bitmaxApiWebSocketClient,
                symbol, config, null, response -> {
            System.out.println(response);
        }, logMsg -> {
            System.out.println(logMsg);
        });
        bootStrap.start();
    }
}
