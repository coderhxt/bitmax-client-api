package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-24
 */
@Setter @Getter
public class OrderData {
    @JsonProperty("m")
    private String messageType;
    private String execId;
    private String coid;
    private String origCoid;
    private String orderType;
    @JsonProperty("s")
    private String symbol;
    @JsonProperty("t")
    private long time;
    @JsonProperty("p")
    private String orderPrice;
    @JsonProperty("sp")
    private String stopPrice;
    @JsonProperty("q")
    private String orderQty;
    private String l;
    @JsonProperty("f")
    private String filledQty;
    @JsonProperty("ap")
    private String avgPrice;
    @JsonProperty("bb")
    private String baseAssetTotalBalance;
    @JsonProperty("bpb")
    private String baseAssetPendingBalance;
    @JsonProperty("qb")
    private String quoteAssetTotalBalance;
    @JsonProperty("qpb")
    private String quoteAssetPendingBalance;
    private String fee;
    @JsonProperty("fa")
    private String feeAssset;
    private String bc;
    private String btmxBal;
    private String side;
    private String status;
    private String errorCode;
    @JsonProperty("cat")
    private String accountCategory;
    @JsonProperty("ei")
    private String executionInstruction;
}
