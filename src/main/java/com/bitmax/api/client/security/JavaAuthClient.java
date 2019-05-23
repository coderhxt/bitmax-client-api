package com.bitmax.api.client.security;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JavaAuthClient {

    private String baseUrl;
    private String apiKey;
    private String secretKey;
    private Mac hmac;
    private byte[] hmacKey;
    private SecretKeySpec keySpec;

    public static void main(String[] args) {
        String apiKey = "123";
        String secret = "abc";
        String baseUrl = "https://bitmax.io";

        try {
            String path = "user/info";
            String coid = "cash123456789";
            JavaAuthClient client = new JavaAuthClient(baseUrl, apiKey, secret);
            client.makeHeader(path, coid);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public JavaAuthClient(String baseUrl, String apiKey, String secretKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;

        hmacKey = Base64.getDecoder().decode(secretKey);
        try {
            hmac = Mac.getInstance("HmacSHA256");
            keySpec = new SecretKeySpec(hmacKey, "HmacSHA256");
            hmac.init(keySpec);
        } catch (Exception e) {
            //
        }
    }

    public void makeHeader(String apiPath,String coid) throws Exception{
        Map<String, String> headersMap = getHeaderMap(apiPath, coid, System.currentTimeMillis());
        for (Map.Entry<String,String> entry : headersMap.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }


    private Map<String, String> getHeaderMap(String apiPath,String coid, long timestamp) throws Exception{
        Map<String, String> headers = new HashMap<>();
        headers.put("x-auth-key", apiKey);
        headers.put("x-auth-signature", generateSig(apiPath, coid, timestamp));
        headers.put("x-auth-timestamp", timestamp + "");
        if (StringUtils.isNotEmpty(coid)) {
            headers.put("x-auth-coid", coid);
        }
        return headers;
    }

    public String generateSig(String apiPath, String coid, long timestamp) throws UnsupportedEncodingException {
        String prehash = timestamp + "+" + apiPath;
        if(StringUtils.isNotEmpty(coid)){
            prehash = prehash + "+" + coid;
        }
        byte[] encoded = Base64.getEncoder().encode(hmac.doFinal(prehash.getBytes("UTF-8")));
        return new String(encoded);
    }
}
