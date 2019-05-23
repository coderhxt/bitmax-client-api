package com.bitmax.api.client;

import com.bitmax.api.client.domain.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

import static com.bitmax.api.client.constant.ApiPathConstants.*;
import static com.bitmax.api.client.constant.BitmaxApiConstants.*;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public interface BitmaxApiService {

    @GET("api/v1/assets")
    Call<List<Asset>> listOfAllAsset();

    @GET("api/v1/products")
    Call<List<Product>> listOfAllProduct();

    @GET("api/v1/fees")
    Call<Map<String, TradeFee>> currentTradingFee();

    @GET("api/v1/quote")
    Call<MarketQuote> marketQuoteOfOneProduct(@Query("symbol")String symbol);

    @GET("api/v1/depth")
    Call<MarketDepth> marketDepthOfOneProduct(@Query("symbol") String symbol, @Query("n") int num);

    @GET("api/v1/trades")
    Call<MarketTrade> marketTrades(@Query("symbol") String symbol, @Query("n") int num);

    @GET("api/v1/ticker/24hr")
    Call<List<RollingStatistics>> rollingStatistics();

    @GET("api/v1/ticker/24hr")
    Call<RollingStatistics> rollingStatisticsOneProduct(@Query("symbol") String symbol);

    @GET("api/barhist")
    Call<List<BarHistory>> barHistoryData(@Query("symbol") String symbol, @Query("from") String from,
                                    @Query("to") String to, @Query("interval") String interval);

    @GET("api/v1/margin/ref-price")
    Call<Map<String, String>> referencePricesAll();

    @GET("api/v1/margin/ref-price/{asset}")
    Call<Map<String, String>> referencePrices(@Path("asset")String asset);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_USER_INFO})
    @GET("api/v1/user/info")
    Call<AccountGroup> userInfo();

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_BALANCE})
    @GET("{accountGroup}/api/v1/balance")
    Call<CashAccountBalance> listAllBalancesOfTheCashAccount(@Path("accountGroup")int accountGroup);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_BALANCE})
    @GET("{accountGroup}/api/v1/balance/{asset}")
    Call<CashAssetBalance> balanceOfOneAsset(@Path("accountGroup")int accountGroup, @Path("asset")String asset);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_MARGIN_BALANCE})
    @GET("{accountGroup}/api/v1/margin/balance")
    Call<MarginAccountBalance> listAllBalancesOfTheMarginAccount(@Path("accountGroup")int accountGroup);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_TRANSACTION})
    @GET("{accountGroup}/api/v1/transaction")
    Call<DepositWithdrawHistory> depositWithdrawHistory(@Path("accountGroup")int accountGroup);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER, ENDPOINT_SECURITY_TYPE_COID_HEADER})
    @POST("{accountGroup}/api/v1/order")
    Call<OrderResp> placeCashOrder(@Path("accountGroup")int accountGroup,@Body OrderReq orderReq);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_MARGIN_ORDER, ENDPOINT_SECURITY_TYPE_COID_HEADER})
    @POST("{accountGroup}/api/v1/margin/order")
    Call<OrderResp> placeMarginOrder(@Path("accountGroup")int accountGroup, @Body OrderReq orderReq);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER_BATCH, ENDPOINT_SECURITY_TYPE_COID_HEADER})
    @POST("{accountGroup}/api/v1/order/batch")
    Call<BatchOrderResp> placingMultipleOrdersFromTheCashAccount(@Path("accountGroup")int accountGroup, @Body BatchOrderReq batchOrderReq);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER, ENDPOINT_SECURITY_TYPE_COID_HEADER})
    @HTTP(method = "DELETE", path = "{accountGroup}/api/v1/order", hasBody = true)
    Call<CancelOrderResp> cancelCashOrder(@Path("accountGroup")int accountGroup, @Body CancelOrderReq cancelOrderReq);


    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_MARGIN_ORDER, ENDPOINT_SECURITY_TYPE_COID_HEADER})
    @HTTP(method = "DELETE", path = "{accountGroup}/api/v1/margin/order", hasBody = true)
    Call<CancelOrderResp> cancelMarginOrder(@Path("accountGroup")int accountGroup, @Body CancelOrderReq cancelOrderReq);


    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER_BATCH, ENDPOINT_SECURITY_TYPE_COID_HEADER})
    @HTTP(method = "DELETE", path = "{accountGroup}/api/v1/order/batch", hasBody = true)
    Call<CancelMultiOrderResp> cancelMultiOrder(@Path("accountGroup")int accountGroup, @Body CancelMultiOrderReq cancelMultiOrderReq);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER_All})
    @HTTP(method = "DELETE", path = "{accountGroup}/api/v1/order/all", hasBody = true)
    Call<CancelAllOpenResp> cancelAllOpenOrder(@Path("accountGroup")int accountGroup, @Query("symbol") String symbol, @Query("side") String side);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER_OPEN})
    @GET("{accountGroup}/api/v1/order/open")
    Call<AllOpenOrder> listOfAllCashOpenOrders(@Path("accountGroup")int accountGroup);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_MARGIN_ORDER_OPEN})
    @GET("{accountGroup}/api/v1/margin/order/open")
    Call<AllOpenOrder> listOfAllMarginOpenOrders(@Path("accountGroup")int accountGroup);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER_HISTORY})
    @GET("{accountGroup}/api/v2/order/history")
    Call<AllHistoryOrderResp> listHistoricalOrders(@Path("accountGroup")int accountGroup, @Query("symbol")String symbol,
                                               @Query("category") String category, @Query("page") String page, @Query("pageSize") String pageSize,
                                               @Query("side") String side, @Query("startTime") String startTime, @Query("endTime") String endTime,
                                               @Query("status") String status);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER})
    @GET("{accountGroup}/api/v1/order/{coid}")
    Call<BasicOrderData> basicOrderDataOfOneOrder(@Path("accountGroup")int accountGroup, @Path("coid") String coid);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_ORDER_FILLS})
    @GET("{accountGroup}/api/v1/order/fills/{coid}")
    Call<FillsOrderResp> fillsOfOneOrder(@Path("accountGroup")int accountGroup, @Path("coid") String coid);

    @Headers({ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, ENDPOINT_SECURITY_TYPE_SIGNED_HEADER,
            ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER, API_PATH_DEPOSIT})
    @POST("{accountGroup}/api/v1/deposit")
    Call<DepositAddressResp> depositAddressOfOneAsset(@Path("accountGroup")int accountGroup, @Body DepositAddressReq depositAddressReq);
}