package com.bitmax.api.client.util;

import com.bitmax.api.client.domain.OrderType;

/**
 * @author xiaotian.huang
 * @date 2019-05-22
 */
public class OrderIdWorker {

    private static final IdWorker idWorker;

    static {
        idWorker = new IdWorker(1,1);
    }

    public static String cashOrderId() {
        return OrderType.CASH.name() + idWorker.nextId();
    }

    public static String marginOrderId() {
        return OrderType.MARGIN.name() + idWorker.nextId();
    }

    public static long orderId() {
        return idWorker.nextId();
    }
}
