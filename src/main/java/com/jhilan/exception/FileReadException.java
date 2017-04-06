package com.jhilan.exception;


/**
 * Created by jhilanalkarawi on 3/28/17.
 */
public class FileReadException extends RuntimeException {
    public FileReadException(final String message, final Throwable e) {
        super(message, e);
    }
}
