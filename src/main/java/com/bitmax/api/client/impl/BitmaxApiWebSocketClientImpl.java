package com.bitmax.api.client.impl;

import com.bitmax.api.client.BitmaxApiCallBack;
import com.bitmax.api.client.BitmaxApiWebSocketClient;
import com.bitmax.api.client.constant.BitmaxApiConstants;
import com.bitmax.api.client.domain.WebSocketType;
import com.bitmax.api.client.domain.event.SubscribeConfig;
import com.bitmax.api.client.exception.BitmaxApiException;
import com.bitmax.api.client.security.JavaAuthClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.io.UnsupportedEncodingException;

/**
 * @author xiaotian.huang
 * @date 2019-05-21
 */
public class BitmaxApiWebSocketClientImpl implements BitmaxApiWebSocketClient {

    private final OkHttpClient client;

    private final String apiKey;

    private final String secret;

    public BitmaxApiWebSocketClientImpl(OkHttpClient client, String apiKey, String secret) {
        this.client = client;
        this.apiKey = apiKey;
        this.secret = secret;
    }

    @Override
    public Closeable onOrderManagementEvent(int accountGroup, String symbol, BitmaxApiCallBack callBack) {
        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(secret)) {
            throw new BitmaxApiException("bitmax用户订单WebSocket连接失败, 身份特征不能为空");
        }
        String streamingUrl = String.format(BitmaxApiConstants.WEBSOCKET_ENTRY_POINT_AUTH, accountGroup, symbol);
        Request.Builder requestBuilder = new Request.Builder().url(streamingUrl)
                .header(BitmaxApiConstants.API_KEY_HEADER, apiKey);
        long timestamp = System.currentTimeMillis();
        String signature;
        try {
            signature = new JavaAuthClient(BitmaxApiConstants.API_BASE_URL, apiKey, secret).generateSig("api/stream", null, timestamp);
        } catch (UnsupportedEncodingException e) {
            throw new BitmaxApiException("websocket-order manage签名失败!");
        }
        requestBuilder.header(BitmaxApiConstants.SIGNATURE_HEADER, signature);
        requestBuilder.header(BitmaxApiConstants.TIMESTAMP_HEADER, timestamp+"");
        BitmaxApiWebSocketListener listener = new BitmaxApiWebSocketListener(WebSocketType.ORDER, symbol, null, callBack);
        return createWebSocket(requestBuilder.build(), listener);
    }

    @Override
    public Closeable onPublicEvent(String symbol, SubscribeConfig config, BitmaxApiCallBack callBack) {
        if (config == null) {
            throw new BitmaxApiException("bitmax公共WebSocket, 需要发送订阅配置, SubscribeConfig不能为null");
        }
        String streamingUrl = String.format(BitmaxApiConstants.WEBSOCKET_ENTRY_POINT_PUBLIC, symbol);
        Request request = new Request.Builder().url(streamingUrl).build();
        BitmaxApiWebSocketListener listener = new BitmaxApiWebSocketListener(WebSocketType.PUBLIC, symbol, config, callBack);
        return createWebSocket(request, listener);
    }

    private Closeable createWebSocket(Request request, WebSocketListener listener) {
        final WebSocket webSocket = client.newWebSocket(request, listener);
        return () -> {
            final int code = 1000;
            listener.onClosing(webSocket, code, null);
            webSocket.close(code, null);
            listener.onClosed(webSocket, code, null);
        };
    }
}
