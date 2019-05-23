package com.bitmax.api.client.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
@Builder @Getter
public class CancelMultiOrderReq {

    private List<CancelOrderReq> orders;
}
