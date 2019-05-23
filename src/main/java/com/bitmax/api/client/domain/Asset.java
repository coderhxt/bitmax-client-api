package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Setter @Getter
public class Asset {

    private String assetCode;

    private String assetName;

    private int precisionScale;

    private int nativeScale;

    private double withdrawalFee;

    private double minWithdrawalAmt;

    private String status;
}
