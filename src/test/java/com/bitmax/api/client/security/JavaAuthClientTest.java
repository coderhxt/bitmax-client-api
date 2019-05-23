package com.bitmax.api.client.security;

import org.junit.Test;

public class JavaAuthClientTest {

    @Test
    public void testSign() throws Exception {
        String baseUrl = "https://bitmax.io";
        String apiKey = "api-key";
        String secret = "secret";
        new JavaAuthClient(baseUrl, apiKey, secret).makeHeader("deposit", null);
    }
}