package com.bitmax.api.client.security;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JavaAuthClientTest {

    @Test
    public void testSign() throws Exception {
        String baseUrl = "https://bitmax.io";
        String apiKey = "your-api-key";
        String secret = "your-secret";
        new JavaAuthClient(baseUrl, apiKey, secret).makeHeader("order/fills", null);
    }
}