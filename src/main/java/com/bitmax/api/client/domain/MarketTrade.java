package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Getter @Setter
public class MarketTrade {

    @JsonProperty("m")
    private String messageType;

    @JsonProperty("s")
    private String symbol;

    private List<Trade> trades;
}
