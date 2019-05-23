package com.bitmax.api.client.impl;

import com.bitmax.api.client.BitmaxApiError;
import com.bitmax.api.client.constant.BitmaxApiConstants;
import com.bitmax.api.client.exception.BitmaxApiException;
import com.bitmax.api.client.security.AuthenticationInterceptor;
import lombok.Getter;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public class BitmaxApiServiceGenerator {

    @Getter
    private static final OkHttpClient sharedClient;

    private static final Converter.Factory converterFactory = JacksonConverterFactory.create();

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(500);
        dispatcher.setMaxRequests(500);
        sharedClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .pingInterval(20, TimeUnit.SECONDS)
                .build();
    }

    private static final Converter<ResponseBody, BitmaxApiError> errorBodyConverter =
            (Converter<ResponseBody, BitmaxApiError>) converterFactory.responseBodyConverter(BitmaxApiError.class, new Annotation[0], null);

    private static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String apikey, String secret) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BitmaxApiConstants.API_BASE_URL)
                .addConverterFactory(converterFactory);

        if (StringUtils.isEmpty(apikey) || StringUtils.isEmpty(secret)) {
            retrofitBuilder.client(sharedClient);
        } else {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(apikey, secret);
            OkHttpClient adaptedClient = sharedClient.newBuilder().addInterceptor(interceptor).build();
            retrofitBuilder.client(adaptedClient);
        }
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                BitmaxApiError apiError = getBitmaxApiError(response);
                throw new BitmaxApiException(apiError);
            }
        } catch (IOException e) {
            throw new BitmaxApiException(e);
        }
    }

    private static BitmaxApiError getBitmaxApiError(Response<?> response) throws IOException, BitmaxApiException {
        return errorBodyConverter.convert(response.errorBody());
    }

    public static OkHttpClient getSharedClient() {
        return sharedClient;
    }
}
