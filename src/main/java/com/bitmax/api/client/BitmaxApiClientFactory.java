package com.bitmax.api.client;

import com.bitmax.api.client.impl.BitmaxApiRestClientImpl;
import com.bitmax.api.client.impl.BitmaxApiWebSocketClientImpl;

import static com.bitmax.api.client.impl.BitmaxApiServiceGenerator.getSharedClient;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public class BitmaxApiClientFactory {

    private String apiKey;

    private String secret;

    private BitmaxApiClientFactory(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    public static BitmaxApiClientFactory newInstance(String apiKey, String secret) {
        return new BitmaxApiClientFactory(apiKey, secret);
    }

    public static BitmaxApiClientFactory newInstance() {
        return new BitmaxApiClientFactory(null, null);
    }

    public BitmaxApiRestClient newRestClient() {
        return new BitmaxApiRestClientImpl(apiKey, secret);
    }

    public BitmaxApiWebSocketClient newWebSocketClient() {
        return new BitmaxApiWebSocketClientImpl(getSharedClient(), apiKey, secret);
    }
}