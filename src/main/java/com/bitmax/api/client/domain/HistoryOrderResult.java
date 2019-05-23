package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019-05-23
 */
@Setter @Getter
public class HistoryOrderResult extends Page{

    @JsonProperty("data")
    private List<HistoryOrder> historyOrderList;
}
