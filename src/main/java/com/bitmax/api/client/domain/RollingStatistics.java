package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Setter @Getter
public class RollingStatistics {
    private String symbol;
    private String interval;
    private long barStartTime;
    private String openPrice;
    private String closePrice;
    private String highPrice;
    private String lowPrice;
    private String volume;
}
