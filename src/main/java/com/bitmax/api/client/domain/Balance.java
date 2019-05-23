package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Setter @Getter
public class Balance {

    private String assetCode;
    private String assetName;
    private String totalAmount;
    private String availableAmount;
    private String inOrderAmount;
}
