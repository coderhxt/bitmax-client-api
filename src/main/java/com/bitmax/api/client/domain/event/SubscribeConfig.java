package com.bitmax.api.client.domain.event;

import lombok.Getter;

/**
 * @author xiaotian.huang
 * @date 2019-05-21
 */
@Getter
public class SubscribeConfig {

    private final String messageType = "subscribe";

    private int marketDepthLevel;

    private int recentTradeMaxCount;

    private boolean skipSummary;

    private boolean skipBars;

    public SubscribeConfig(int marketDepthLevel, int recentTradeMaxCount, boolean skipSummary, boolean skipBars) {
        this.marketDepthLevel = marketDepthLevel;
        this.recentTradeMaxCount = recentTradeMaxCount;
        this.skipSummary = skipSummary;
        this.skipBars = skipBars;
    }
}
