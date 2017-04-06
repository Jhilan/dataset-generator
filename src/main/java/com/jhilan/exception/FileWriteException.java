package com.jhilan.exception;

import java.io.IOException;

/**
 * Created by jhilanalkarawi on 3/31/17.
 */
public class FileWriteException extends IOException {
    public FileWriteException(final String message, final Throwable e) {
        super(message, e);
    }
}
