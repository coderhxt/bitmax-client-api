package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Setter @Getter
public class BaseResp {

    private int code;

    private String status;

    private String email;

    private String message;
}
