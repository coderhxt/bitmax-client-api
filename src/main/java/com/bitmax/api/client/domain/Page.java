package com.bitmax.api.client.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
@Setter @Getter
public class Page {
    private int page;
    private int pageSize;
    private boolean hasNext;
    private long limit;
}
