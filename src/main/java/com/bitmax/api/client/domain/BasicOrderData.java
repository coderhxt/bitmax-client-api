package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
@Setter @Getter
public class BasicOrderData extends BaseResp {
    @JsonProperty("data")
    private OrderDetail orderData;
}
