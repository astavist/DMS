package com.astavist.DMS.utils.ExceptionHandlers;

import java.time.LocalDateTime;

public class ExceptionResults <T> {
    private LocalDateTime timestamp;
    private String type;
    private T message;

    public ExceptionResults(String type, T message) {
        timestamp = LocalDateTime.now();
        this.type = type;
        this.message = message;
    }
}
