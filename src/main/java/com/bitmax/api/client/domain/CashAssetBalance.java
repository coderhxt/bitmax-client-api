package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Getter @Setter
public class CashAssetBalance extends BaseResp{

    @JsonProperty("data")
    private Balance balance;
}
