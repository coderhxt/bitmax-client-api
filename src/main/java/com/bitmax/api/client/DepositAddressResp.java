package com.bitmax.api.client;

import com.bitmax.api.client.domain.BaseResp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
@Setter @Getter
public class DepositAddressResp extends BaseResp {

    @JsonProperty("data")
    private NumericAddress numericAddress;
}
