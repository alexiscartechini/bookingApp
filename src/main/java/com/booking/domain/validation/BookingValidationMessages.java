package com.booking.domain.validation;

public final class BookingValidationMessages {

    private BookingValidationMessages() {
    }

    public static final String REQUEST_ID_EMPTY = "request_id value cannot be empty";
    public static final String CHECK_IN_EMPTY = "check_in value cannot be empty";
    public static final String NIGHTS_INVALID = "nights value has to be greater than zero";
    public static final String SELLING_RATE_INVALID = "selling_rate value has to be greater than zero";
    public static final String MARGIN_NEGATIVE = "margin value cannot be negative";
}