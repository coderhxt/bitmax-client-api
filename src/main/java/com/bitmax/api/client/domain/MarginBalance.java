package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Getter @Setter
public class MarginBalance {
    private String assetCode;
    private String totalAmount;
    private String availableAmount;
    private String borrowedAmount;
    private String interest;
    private String maxSellable;
    private String interestRate;
    private String maxTransferable;
}
