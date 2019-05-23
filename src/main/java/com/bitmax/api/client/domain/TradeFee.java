package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Setter @Getter
public class TradeFee {
    private String mining;
    private String noMining;
    private String rebate;
}
