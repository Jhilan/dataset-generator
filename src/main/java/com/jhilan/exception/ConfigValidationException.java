package com.jhilan.exception;

/**
 * Created by jhilanalkarawi on 3/28/17.
 */
public class ConfigValidationException extends RuntimeException {
    public ConfigValidationException(final String message) {
        super(message);
    }

    public ConfigValidationException(final String message, Throwable e) {
        super(message, e);
    }
}
