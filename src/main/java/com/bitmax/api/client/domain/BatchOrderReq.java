package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Builder @Getter
public class BatchOrderReq {

    @JsonProperty("orders")
    private List<OrderReq> multiOrder;
}
