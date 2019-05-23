package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
@Setter @Getter
public class OrderDetail {
    private long time;
    private String coid;
    private String symbol;
    private String baseAsset;
    private String quoteAsset;
    private String side;
    private String orderPrice;
    private String stopPrice;
    private String orderQty;
    private String filled;
    private String fee;
    private String feeAsset;
    private String status;
}
