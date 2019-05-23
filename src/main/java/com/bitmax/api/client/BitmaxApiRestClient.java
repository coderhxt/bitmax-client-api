package com.bitmax.api.client;

import com.bitmax.api.client.domain.*;

import java.util.List;
import java.util.Map;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public interface BitmaxApiRestClient {

    List<Asset> listOfAllAssets();

    List<Product> listOfAllProduct();

    Map<String, TradeFee> currentTradingFee();

    MarketQuote marketQuoteOfOneProduct(String symbol);

    MarketDepth marketDepthOfOneProduct(String symbol, int num);

    MarketTrade marketTrades(String symbol, int num);

    List<RollingStatistics> rollingStatistics();

    RollingStatistics rollingStatisticsOneProduct(String symbol);

    List<BarHistory> barHistoryData(String symbol, String from, String to, String interval);

    Map<String, String> referencePricesMargin(String asset);

    AccountGroup userInfo();

    CashAccountBalance listAllBalancesOfTheCashAccount(int accountGroup);

    CashAssetBalance balanceOfOneAsset(int accountGroup, String asset);

    MarginAccountBalance listAllBalancesOfTheMarginAccount(int accountGroup);

    DepositWithdrawHistory depositWithdrawHistory(int accountGroup);

    OrderResp placeNewOrder(int accountGroup, OrderType orderType, OrderReq orderReq);

    BatchOrderResp placingMultipleOrdersFromTheCashAccount(int accountGroup, BatchOrderReq batchOrderReq);

    CancelOrderResp cancelOrder(int accountGroup, OrderType orderType, CancelOrderReq cancelOrderReq);

    CancelMultiOrderResp cancelMultiOrder(int accountGroup, CancelMultiOrderReq cancelMultiOrderReq);

    CancelAllOpenResp cancelAllOpenOrder(int accountGroup, String symbol, String side);

    AllOpenOrder listOfAllOpenOrders(int accountGroup, OrderType orderType);

    AllHistoryOrderResp listHistoricalOrders(int accountGroup, HistoryOrderReq historyOrderReq);

    BasicOrderData basicOrderDataOfOneOrder(int accountGroup, String coid);

    FillsOrderResp fillsOfOneOrder(int accountGroup, String coid);

    DepositAddressResp depositAddressOfOneAsset(int accountGroup, DepositAddressReq depositAddressReq);
}
