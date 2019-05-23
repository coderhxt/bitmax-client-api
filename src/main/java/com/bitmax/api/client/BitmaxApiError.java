package com.bitmax.api.client;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
@Getter@Setter
public class BitmaxApiError {
    private int code;
    private String msg;
}
