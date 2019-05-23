package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
@Setter @Getter
public class HistoryOrder {
    private long time;
    private String coid;
    private String execId;
    private String symbol;
    private String orderType;
    private String baseAsset;
    private String quoteAsset;
    private String side;
    private String stopPrice;
    private String orderPrice;
    private String orderQty;
    private String filledQty;
    private String avgPrice;
    private String fee;
    private String feeAsset;
    private String btmxCommission;
    private String status;
    private String notional;
    private String userId;
    private String accountId;
    private String accountCategory;
    private String errorCode;
    private String execInst;
}
