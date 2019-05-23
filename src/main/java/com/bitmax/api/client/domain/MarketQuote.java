package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Getter @Setter
public class MarketQuote {

    private String symbol;
    private String bidPrice;
    private String bidSize;
    private String askPrice;
    private String askSize;
}
