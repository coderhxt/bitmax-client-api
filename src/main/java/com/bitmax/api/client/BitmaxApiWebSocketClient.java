package com.bitmax.api.client;

import com.bitmax.api.client.domain.event.SubscribeConfig;

import java.io.Closeable;

/**
 * @author xiaotian.huang
 * @date 2019-05-21
 */
public interface BitmaxApiWebSocketClient {

    Closeable onPublicEvent(String symbol, SubscribeConfig config, BitmaxApiCallBack callBack);

    Closeable onOrderManagementEvent(int accountGroup, String symbol, BitmaxApiCallBack callBack);
}
