package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Getter @Setter
public class Trade {
    @JsonProperty("p")
    private String price;
    @JsonProperty("q")
    private String quantity;
    @JsonProperty("t")
    private String timestamp;
    @JsonProperty("bm")
    private String buyerOrMarker;
}
