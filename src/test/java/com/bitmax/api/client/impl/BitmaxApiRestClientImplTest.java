package com.bitmax.api.client.impl;

import com.bitmax.api.client.BaseTest;
import com.bitmax.api.client.BitmaxApiClientFactory;
import com.bitmax.api.client.BitmaxApiRestClient;
import com.bitmax.api.client.domain.*;
import com.bitmax.api.client.util.OrderIdWorker;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BitmaxApiRestClientImplTest extends BaseTest {

    BitmaxApiRestClient bitmaxApiRestClient;

    @Before
    public void setup() {
        bitmaxApiRestClient = BitmaxApiClientFactory.newInstance("your-api-key",
                "your-secret")
                .newRestClient();
    }

    @Test
    public void listOfAllAssets() {
        List<Asset> assets = bitmaxApiRestClient.listOfAllAssets();
        super.toPrettyShow(assets);
    }

    @Test
    public void listOfAllProduct() {
        List<Product> products = bitmaxApiRestClient.listOfAllProduct();
        super.toPrettyShow(products);
    }

    @Test
    public void currentTradingFee() {
        Map<String, TradeFee> tradeFeeMap = bitmaxApiRestClient.currentTradingFee();
        super.toPrettyShow(tradeFeeMap);
    }

    @Test
    public void marketQuoteOfOneProduct() {
        String symbol = "ETH-BTC";
        MarketQuote marketQuote = bitmaxApiRestClient.marketQuoteOfOneProduct(symbol);
        super.toPrettyShow(marketQuote);
    }

    @Test
    public void marketDepthOfOneProduct() {
        String symbol = "ETH-BTC";
        int num = 10;
        MarketDepth marketDepth = bitmaxApiRestClient.marketDepthOfOneProduct(symbol, num);
        super.toPrettyShow(marketDepth);
    }

    @Test
    public void marketTrades() {
        String symbol = "ETH-BTC";
        int num = 10;
        MarketTrade marketTrade = bitmaxApiRestClient.marketTrades(symbol, num);
        super.toPrettyShow(marketTrade);
    }

    @Test
    public void rollingStatistics() {
        List<RollingStatistics> rollingStatisticsList = bitmaxApiRestClient.rollingStatistics();
        super.toPrettyShow(rollingStatisticsList);
    }

    @Test
    public void rollingStatisticsOneProduct() {
        String symbol = "ETH-BTC";
        RollingStatistics rollingStatistics = bitmaxApiRestClient.rollingStatisticsOneProduct(symbol);
        super.toPrettyShow(rollingStatistics);
    }

    @Test
    public void barHistoryData() {
        String symbol = "ETH-BTC";
        String from = "1539654780000";
        String to = "1539645600000";
        String interval = "1";
        List<BarHistory> barHistories = bitmaxApiRestClient.barHistoryData(symbol, from, to, interval);
        super.toPrettyShow(barHistories);
    }

    @Test
    public void referencePricesMargin() {
        Map<String, String> assetPriceMap = bitmaxApiRestClient.referencePricesMargin(null);
        super.toPrettyShow(assetPriceMap);
        String asset = "ETH";
        Map<String, String> assetPriceMap2 = bitmaxApiRestClient.referencePricesMargin(asset);
        super.toPrettyShow(assetPriceMap2);
    }

    @Test
    public void userInfo() {
        AccountGroup accountGroup = bitmaxApiRestClient.userInfo();
        super.toPrettyShow(accountGroup);
    }

    @Test
    public void listAllBalancesOfTheCashAccount() {
        CashAccountBalance accountBalance = bitmaxApiRestClient.listAllBalancesOfTheCashAccount(2);
        super.toPrettyShow(accountBalance);
    }

    @Test
    public void balanceOfOneAsset() {
        CashAssetBalance assetBalance = bitmaxApiRestClient.balanceOfOneAsset(2, "BTC");
        super.toPrettyShow(assetBalance);
    }


    @Test
    public void listAllBalancesOfTheMarginAccount() {
        MarginAccountBalance marginAccountBalance = bitmaxApiRestClient.listAllBalancesOfTheMarginAccount(2);
        super.toPrettyShow(marginAccountBalance);
    }

    @Test
    public void depositWithdrawHistory() {
        DepositWithdrawHistory depositWithdrawHistory = bitmaxApiRestClient.depositWithdrawHistory(2);
        super.toPrettyShow(depositWithdrawHistory);
    }

    @Test
    public void placeNewOrder() {
        OrderReq orderReq = OrderReq.builder().coid("cash05001")
                .time(System.currentTimeMillis())
                .orderPrice("7001")
//                .stopPrice("15.7")
                .orderQty("0.002")
                .orderType("limit")
                .side("buy")
//                .postOnly(true)
//                .timeInForce("GTC")
                .symbol("BTC/USDT")
                .build();
        OrderResp orderResp = bitmaxApiRestClient.placeNewOrder(2, OrderType.CASH, orderReq);
        super.toPrettyShow(orderResp);
//        OrderResp orderResp1 = bitmaxApiRestClient.placeNewOrder(2, OrderType.MARGIN, orderReq);
//        super.toPrettyShow(orderResp1);
    }


    @Test
    public void placingMultipleOrdersFromTheCashAccount() {
        OrderReq orderReq = OrderReq.builder().coid(OrderIdWorker.orderId() + "")
                .time(System.currentTimeMillis())
                .symbol("ETH/BTC")
                .orderPrice("13.5")
                .stopPrice("15.7")
                .orderQty("3.5")
                .orderType("limit")
                .side("buy")
                .postOnly(true)
                .timeInForce("GTC")
                .build();
        OrderReq orderReq1 = OrderReq.builder().coid(OrderIdWorker.orderId() + "")
                .time(System.currentTimeMillis())
                .symbol("ETH/BTC")
                .orderPrice("13.5")
                .stopPrice("15.7")
                .orderQty("3.5")
                .orderType("limit")
                .side("buy")
                .postOnly(true)
                .timeInForce("GTC")
                .build();
        BatchOrderResp batchOrderResp = bitmaxApiRestClient.placingMultipleOrdersFromTheCashAccount(2,
                BatchOrderReq.builder().multiOrder(Arrays.asList(orderReq, orderReq1)).build());
        super.toPrettyShow(batchOrderResp);
    }

    @Test
    public void cancelOrder() {
        CancelOrderReq cancelOrderReq = CancelOrderReq.builder().coid("cash123123")
                .origCoid("cash05001")
                .symbol("ETH/BTC")
                .time(System.currentTimeMillis())
                .build();
        CancelOrderResp cancelOrderResp = bitmaxApiRestClient.cancelOrder(2, OrderType.CASH, cancelOrderReq);
        super.toPrettyShow(cancelOrderResp);
//        CancelOrderResp cancelOrderResp1 = bitmaxApiRestClient.cancelOrder(2, OrderType.MARGIN, cancelOrderReq);
//        super.toPrettyShow(cancelOrderResp1);
    }


    @Test
    public void cancelMultiOrder() {
        CancelOrderReq cancelOrderReq = CancelOrderReq.builder().coid("cash123123")
                .origCoid("cash123456789")
                .symbol("ETH/BTC")
                .time(System.currentTimeMillis())
                .build();
        CancelOrderReq cancelOrderReq1 = CancelOrderReq.builder().coid("cash123123")
                .origCoid("cash123456789")
                .symbol("ETH/BTC")
                .time(System.currentTimeMillis())
                .build();
        CancelMultiOrderResp cancelMultiOrderResp = bitmaxApiRestClient.cancelMultiOrder(2,
                CancelMultiOrderReq.builder().orders(Arrays.asList(cancelOrderReq, cancelOrderReq1)).build());
        super.toPrettyShow(cancelMultiOrderResp);
    }


    @Test
    public void cancelAllOpenOrder() {
        CancelAllOpenResp cancelAllOpenResp = bitmaxApiRestClient.cancelAllOpenOrder(2, "ETH-BTC", "buy");
        super.toPrettyShow(cancelAllOpenResp);
    }

    @Test
    public void listOfAllOpenOrders() {
        AllOpenOrder allOpenOrder = bitmaxApiRestClient.listOfAllOpenOrders(2, OrderType.CASH);
        super.toPrettyShow(allOpenOrder);
//        AllOpenOrder allOpenOrder1 = bitmaxApiRestClient.listOfAllOpenOrders(2, OrderType.MARGIN);
//        super.toPrettyShow(allOpenOrder1);
    }

    @Test
    public void listHistoricalOrders() {
        HistoryOrderReq historyOrderReq = HistoryOrderReq.builder().symbol("ETH-BTC")
                .build();
        AllHistoryOrderResp allHistoryOrderResp = bitmaxApiRestClient.listHistoricalOrders(2, historyOrderReq);
        super.toPrettyShow(allHistoryOrderResp);
    }

    @Test
    public void basicOrderDataOfOneOrder() {
        BasicOrderData basicOrderData = bitmaxApiRestClient.basicOrderDataOfOneOrder(2, "cash05001");
        super.toPrettyShow(basicOrderData);
    }


    @Test
    public void fillsOfOneOrder() {
        FillsOrderResp fillsOrderResp = bitmaxApiRestClient.fillsOfOneOrder(2, "cash05001");
        super.toPrettyShow(fillsOrderResp);
    }

    @Test
    public void depositAddressOfOneAsset() {
        DepositAddressReq depositAddressReq = DepositAddressReq.builder().requestId("abc123")
                .time(System.currentTimeMillis())
                .assetCode("ETH").build();
        DepositAddressResp depositAddressResp = bitmaxApiRestClient.depositAddressOfOneAsset(2, depositAddressReq);
        super.toPrettyShow(depositAddressResp);
    }
}