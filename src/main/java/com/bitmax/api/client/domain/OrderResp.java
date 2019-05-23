package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Setter @Getter
public class OrderResp extends BaseResp {
    @JsonProperty("data")
    private OrderResult orderResult;
}
