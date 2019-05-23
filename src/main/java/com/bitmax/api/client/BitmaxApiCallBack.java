package com.bitmax.api.client;

/**
 * @author xiaotian.huang
 * @date 2019-05-21
 */
@FunctionalInterface
public interface BitmaxApiCallBack {

    void onResponse(String response);

    default void onFailure(Throwable cause){};
}
