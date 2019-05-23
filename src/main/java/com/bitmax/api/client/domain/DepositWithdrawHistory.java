package com.bitmax.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Setter @Getter
public class DepositWithdrawHistory extends Page {

    @JsonProperty("data")
    private List<DepositWithdrawDetail> record;
}
