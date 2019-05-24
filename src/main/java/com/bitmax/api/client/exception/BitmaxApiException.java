package com.bitmax.api.client.exception;

import com.bitmax.api.client.BitmaxApiError;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author xiaotian.huang
 * @date 2019-05-20
 */
public class BitmaxApiException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -5551456274393518850L;

    @Getter
    private BitmaxApiError error;

    public BitmaxApiException(BitmaxApiError error) {
        this.error = error;
    }

    public BitmaxApiException(String message) {
        super(message);
    }

    public BitmaxApiException(Throwable cause) {
        super(cause);
    }

    public BitmaxApiException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}
