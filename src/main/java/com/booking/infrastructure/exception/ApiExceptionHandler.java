package com.booking.infrastructure.exception;

import com.booking.domain.exception.InvalidBookingRequestException;
import com.booking.domain.exception.InvalidStatsRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidBookingRequestException.class)
    public ResponseEntity<String> handleInvalidBooking(InvalidBookingRequestException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(InvalidStatsRequestException.class)
    public ResponseEntity<String> handleInvalidStats(InvalidStatsRequestException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}