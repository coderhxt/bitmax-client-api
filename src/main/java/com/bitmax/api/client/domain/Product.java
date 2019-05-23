package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Setter @Getter
public class Product {
    private String symbol;
    private String domain;
    private String baseAsset;
    private String quoteAsset;
    private int priceScale;
    private int qtyScale;
    private int notionalScale;
    private BigDecimal minQty;
    private BigDecimal maxQty;
    private BigDecimal minNotional;
    private BigDecimal maxNotional;
    private String status;
    private String miningStatus;
    private boolean marginTradable;
}
