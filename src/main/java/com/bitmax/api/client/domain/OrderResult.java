package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Getter @Setter
public class OrderResult {
    private String coid;
    private String action;
    private String success;
}
