package com.bitmax.api.client.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Builder @Getter
public class CancelOrderReq {
    private String coid;
    private String origCoid;
    private long time;
    private String symbol;
}
