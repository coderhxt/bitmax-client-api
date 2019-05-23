package com.bitmax.api.client.domain.event;

import lombok.Getter;
import okhttp3.WebSocket;

/**
 * @author xiaotian.huang
 * @date 2019-05-21
 */
@Getter
public class CountPong {

    private final WebSocket webSocket;

    private long count;

    public CountPong(WebSocket webSocket, long count) {
        this.webSocket = webSocket;
        this.count = count;
    }

    public void increase() {
        count++;
    }

    public void reset() {
        count = 1;
    }
}
