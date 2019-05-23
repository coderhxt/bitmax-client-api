package com.bitmax.api.client.domain;

import lombok.*;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Builder @Getter
public class OrderReq {
    private String coid;
    private long time;
    private String symbol;
    private String orderPrice;
    private String stopPrice;
    private String orderQty;
    private String orderType;
    private String side;
    private boolean postOnly;
    private String timeInForce;
}
