package com.booking.domain.exception;

public class InvalidStatsRequestException extends RuntimeException {

    public InvalidStatsRequestException(String message) {
        super(message);
    }
}