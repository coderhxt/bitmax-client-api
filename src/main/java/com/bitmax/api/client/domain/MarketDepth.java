package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Getter @Setter
public class MarketDepth {

    @JsonProperty("m")
    private String messageType;

    @JsonProperty("ts")
    private String timestamp;

    private long seqnum;

    @JsonProperty("s")
    private String symbol;

    private String[][] asks;

    private String[][] bids;
}
