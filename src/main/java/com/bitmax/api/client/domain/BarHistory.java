package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Setter @Getter
public class BarHistory {

    @JsonProperty("m")
    private String message;

    @JsonProperty("s")
    private String symbol;

    @JsonProperty("ba")
    private String base;

    @JsonProperty("qa")
    private String quote;

    @JsonProperty("i")
    private String interval;

    @JsonProperty("t")
    private long time;

    @JsonProperty("o")
    private String open;

    @JsonProperty("c")
    private String close;

    @JsonProperty("h")
    private String high;

    @JsonProperty("l")
    private String low;

    @JsonProperty("v")
    private String volume;
}
