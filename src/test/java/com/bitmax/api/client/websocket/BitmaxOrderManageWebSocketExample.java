package com.bitmax.api.client.websocket;

import com.bitmax.api.client.BitmaxApiClientFactory;
import com.bitmax.api.client.BitmaxApiWebSocketClient;
import com.bitmax.api.client.domain.WebSocketType;
import com.bitmax.api.client.impl.BitmaxWebSocketBootStrap;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
public class BitmaxOrderManageWebSocketExample {

    public static void main(String[] args) {
        BitmaxApiWebSocketClient socketClient = BitmaxApiClientFactory.newInstance("your-api-key",
                "your-secret").newWebSocketClient();
        String symbol = "ETH-BTC";
        // 订阅order manage
        BitmaxWebSocketBootStrap bootStrap = new BitmaxWebSocketBootStrap(WebSocketType.ORDER, socketClient,
                symbol, null, 2, response -> {
            System.out.println(response);
        }, logMsg ->  {
            System.out.println(logMsg);
        });
        bootStrap.start();
    }
}
