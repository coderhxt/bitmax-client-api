package com.bitmax.api.client.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
@Builder @Getter
public class DepositAddressReq {
    private String requestId;
    private long time;
    private String assetCode;
}
