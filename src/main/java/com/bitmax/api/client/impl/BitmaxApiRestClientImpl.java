package com.bitmax.api.client.impl;

import com.bitmax.api.client.BitmaxApiRestClient;
import com.bitmax.api.client.BitmaxApiService;
import com.bitmax.api.client.DepositAddressResp;
import com.bitmax.api.client.domain.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

import static com.bitmax.api.client.impl.BitmaxApiServiceGenerator.createService;
import static com.bitmax.api.client.impl.BitmaxApiServiceGenerator.executeSync;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public class BitmaxApiRestClientImpl  implements BitmaxApiRestClient {

    private final BitmaxApiService bitmaxApiService;

    public BitmaxApiRestClientImpl(String apiKey, String secret) {
        bitmaxApiService = createService(BitmaxApiService.class, apiKey, secret);
    }

    @Override
    public List<Asset> listOfAllAssets() {
        return executeSync(bitmaxApiService.listOfAllAsset());
    }

    @Override
    public Map<String, TradeFee> currentTradingFee() {

        return executeSync(bitmaxApiService.currentTradingFee());
    }

    @Override
    public List<Product> listOfAllProduct() {
        return executeSync(bitmaxApiService.listOfAllProduct());
    }

    @Override
    public MarketQuote marketQuoteOfOneProduct(String symbol) {
        return executeSync(bitmaxApiService.marketQuoteOfOneProduct(symbol));
    }

    @Override
    public MarketDepth marketDepthOfOneProduct(String symbol, int num) {
        return executeSync(bitmaxApiService.marketDepthOfOneProduct(symbol, num));
    }

    @Override
    public MarketTrade marketTrades(String symbol, int num) {
        return executeSync(bitmaxApiService.marketTrades(symbol, num));
    }

    @Override
    public RollingStatistics rollingStatisticsOneProduct(String symbol) {
        return executeSync(bitmaxApiService.rollingStatisticsOneProduct(symbol));
    }

    @Override
    public List<RollingStatistics> rollingStatistics() {
        return executeSync(bitmaxApiService.rollingStatistics());
    }

    @Override
    public List<BarHistory> barHistoryData(String symbol, String from, String to, String interval) {
        return executeSync(bitmaxApiService.barHistoryData(symbol, from, to, interval));
    }

    @Override
    public Map<String, String> referencePricesMargin(String asset) {
        if (StringUtils.isEmpty(asset)) {
            return executeSync(bitmaxApiService.referencePricesAll());
        }
        return executeSync(bitmaxApiService.referencePrices(asset));
    }

    @Override
    public CashAccountBalance listAllBalancesOfTheCashAccount(int accountGroup) {
        return executeSync(bitmaxApiService.listAllBalancesOfTheCashAccount(accountGroup));
    }

    @Override
    public DepositWithdrawHistory depositWithdrawHistory(int accountGroup) {
        return executeSync(bitmaxApiService.depositWithdrawHistory(accountGroup));
    }

    @Override
    public MarginAccountBalance listAllBalancesOfTheMarginAccount(int accountGroup) {
        return executeSync(bitmaxApiService.listAllBalancesOfTheMarginAccount(accountGroup));
    }

    @Override
    public CashAssetBalance balanceOfOneAsset(int accountGroup, String asset) {
        return executeSync(bitmaxApiService.balanceOfOneAsset(accountGroup, asset));
    }

    @Override
    public AllHistoryOrderResp listHistoricalOrders(int accountGroup, HistoryOrderReq historyOrderReq) {
        return executeSync(bitmaxApiService.listHistoricalOrders(accountGroup, historyOrderReq.getSymbol(), historyOrderReq.getCategory(),
                historyOrderReq.getPage(), historyOrderReq.getPageSize(), historyOrderReq.getSide(), historyOrderReq.getStartTime(),
                historyOrderReq.getEndTime(), historyOrderReq.getStatus()));
    }

    @Override
    public DepositAddressResp depositAddressOfOneAsset(int accountGroup, DepositAddressReq depositAddressReq) {
        return executeSync(bitmaxApiService.depositAddressOfOneAsset(accountGroup, depositAddressReq));
    }

    @Override
    public FillsOrderResp fillsOfOneOrder(int accountGroup, String coid) {
        return executeSync(bitmaxApiService.fillsOfOneOrder(accountGroup, coid));
    }

    @Override
    public BasicOrderData basicOrderDataOfOneOrder(int accountGroup, String coid) {
        return executeSync(bitmaxApiService.basicOrderDataOfOneOrder(accountGroup, coid));
    }

    @Override
    public AllOpenOrder listOfAllOpenOrders(int accountGroup, OrderType orderType) {
        AllOpenOrder allOpenOrder = null;
        switch (orderType) {
            case CASH: {
                allOpenOrder = executeSync(bitmaxApiService.listOfAllCashOpenOrders(accountGroup));
                break;
            }
            case MARGIN: {
                allOpenOrder = executeSync(bitmaxApiService.listOfAllMarginOpenOrders(accountGroup));
                break;
            }
        }
        return allOpenOrder;
    }

    @Override
    public CancelAllOpenResp cancelAllOpenOrder(int accountGroup, String symbol, String side) {
        return executeSync(bitmaxApiService.cancelAllOpenOrder(accountGroup, symbol, side));
    }

    @Override
    public OrderResp placeNewOrder(int accountGroup, OrderType orderType, OrderReq orderReq) {
        OrderResp orderResp = null;
        switch (orderType) {
            case CASH:{
                orderResp = executeSync(bitmaxApiService.placeCashOrder(accountGroup, orderReq));
                break;
            }
            case MARGIN:{
                orderResp = executeSync(bitmaxApiService.placeMarginOrder(accountGroup, orderReq));
                break;
            }
        }
        return orderResp;
    }

    @Override
    public CancelMultiOrderResp cancelMultiOrder(int accountGroup, CancelMultiOrderReq cancelMultiOrderReq) {
        return executeSync(bitmaxApiService.cancelMultiOrder(accountGroup, cancelMultiOrderReq));
    }

    @Override
    public CancelOrderResp cancelOrder(int accountGroup, OrderType orderType, CancelOrderReq cancelOrderReq) {
        CancelOrderResp cancelOrderResp = null;
        switch (orderType) {
            case CASH: {
                cancelOrderResp = executeSync(bitmaxApiService.cancelCashOrder(accountGroup, cancelOrderReq));
                break;
            }
            case MARGIN: {
                cancelOrderResp = executeSync(bitmaxApiService.cancelMarginOrder(accountGroup, cancelOrderReq));
                break;
            }
        }
        return cancelOrderResp;
    }

    @Override
    public BatchOrderResp placingMultipleOrdersFromTheCashAccount(int accountGroup, BatchOrderReq batchOrderReq) {
        return executeSync(bitmaxApiService.placingMultipleOrdersFromTheCashAccount(accountGroup, batchOrderReq));
    }

    @Override
    public AccountGroup userInfo() {
        return executeSync(bitmaxApiService.userInfo());
    }
}
