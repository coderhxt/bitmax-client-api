package com.bitmax.api.client.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
@Builder @Getter
public class HistoryOrderReq {
    private String symbol;
    private String category;
    private String orderType;
    private String page;
    private String pageSize;
    private String side;
    private String startTime;
    private String endTime;
    private String status;
}
