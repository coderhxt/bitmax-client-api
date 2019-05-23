package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Setter @Getter
public class DepositWithdrawDetail {
    private long time;
    private String asset;
    private String transactionType;
    private String amount;
    private String commission;
    private String networkTransactionId;
    private String status;
}
