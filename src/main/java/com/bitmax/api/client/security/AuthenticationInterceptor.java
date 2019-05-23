package com.bitmax.api.client.security;

import com.bitmax.api.client.constant.BitmaxApiConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bitmax.api.client.constant.BitmaxApiConstants.*;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public class AuthenticationInterceptor implements Interceptor {

    private final ObjectMapper mapper = new ObjectMapper();

    private final String coidField = "coid";

    private final String apiKey;

    private final String secret;

    public AuthenticationInterceptor(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();
        boolean isApiKeyRequired = original.header(BitmaxApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY) != null;
        boolean isSignatureRequired = original.header(ENDPOINT_SECURITY_TYPE_SIGNED) != null;
        boolean isTimestampRequired = original.header(ENDPOINT_SECURITY_TYPE_TIMESTAMP) != null;
        boolean isCoidRequired = original.header(ENDPOINT_SECURITY_TYPE_COID) != null;
        boolean isApiPathFlag = original.header(API_PATH) != null;
        newRequestBuilder.removeHeader(ENDPOINT_SECURITY_TYPE_APIKEY)
                .removeHeader(ENDPOINT_SECURITY_TYPE_SIGNED)
                .removeHeader(ENDPOINT_SECURITY_TYPE_TIMESTAMP)
                .removeHeader(ENDPOINT_SECURITY_TYPE_COID)
                .removeHeader(API_PATH);
        long timestamp = System.currentTimeMillis();
        String apiPath = null;
        if (isApiPathFlag) {
            apiPath = original.header(API_PATH);
        }
        String coid = null;
        if (isCoidRequired) {
            String body = bodyToString(original);
            List<String> coidList = mapper.readTree(body).findValuesAsText(coidField);
            if (coidList != null && coidList.size() > 0) {
                coid = coidList.stream().collect(Collectors.joining("+"));
            }
            newRequestBuilder.addHeader(BitmaxApiConstants.COID_HEADER, coid);
        }
        Optional<String> signature = calSignature(apiPath, coid, timestamp);
        if (isApiKeyRequired) {
            newRequestBuilder.addHeader(BitmaxApiConstants.API_KEY_HEADER, apiKey);
        }
        if (isSignatureRequired) {
            newRequestBuilder.addHeader(BitmaxApiConstants.SIGNATURE_HEADER, signature.orElse("none"));
        }
        if (isTimestampRequired) {
            newRequestBuilder.addHeader(BitmaxApiConstants.TIMESTAMP_HEADER, timestamp + "");
        }
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    private static String bodyToString(final Request request){
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private Optional<String> calSignature(String apiPath,String coid, long timestamp) throws UnsupportedEncodingException {
        String signature = new JavaAuthClient(API_BASE_URL, apiKey, secret).generateSig(apiPath, coid, timestamp);
        return Optional.ofNullable(signature);
    }
}
