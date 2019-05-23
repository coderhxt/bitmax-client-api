package com.bitmax.api.client.constant;

import com.bitmax.api.client.domain.event.CountPong;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public class BitmaxApiConstants {

    public static final String API_BASE_URL = "https://bitmax.io";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY";

    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";

    public static final String ENDPOINT_SECURITY_TYPE_TIMESTAMP = "TIMESTAMP";

    public static final String ENDPOINT_SECURITY_TYPE_COID = "COID";

    public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";

    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER  = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

    public static final String ENDPOINT_SECURITY_TYPE_TIMESTAMP_HEADER  = ENDPOINT_SECURITY_TYPE_TIMESTAMP + ": #";

    public static final String ENDPOINT_SECURITY_TYPE_COID_HEADER  = ENDPOINT_SECURITY_TYPE_COID + ": #";

    public static final String TIMESTAMP_HEADER = "x-auth-timestamp";

    public static final String COID_HEADER = "x-auth-coid";

    public static final String API_KEY_HEADER = "x-auth-key";

    public static final String SIGNATURE_HEADER = "x-auth-signature";

    public static final String WEBSOCKET_ENTRY_POINT_PUBLIC = "wss://bitmax.io/api/public/%s";

    public static final Map<String, CountPong> pongCounter = new HashMap<>();

    public static final Map<String, Long> localCounter = new HashMap<>();

    public static final String API_PATH = "api_path";

    public static final String WEBSOCKET_ENTRY_POINT_AUTH = "wss://bitmax.io/%d/api/stream/%s";

}
