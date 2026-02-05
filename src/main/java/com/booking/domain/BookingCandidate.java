package com.booking.domain;

import com.booking.domain.exception.InvalidBookingRequestException;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public record BookingCandidate(@JsonProperty("request_id") String requestId, @JsonProperty("check_in") LocalDate checkIn,
                               int nights, @JsonProperty("selling_rate") double sellingRate, double margin) {

    private static final double PERCENTAGE = 0.01;

    public BookingCandidate {
        if (isNull(requestId) || requestId.isBlank())
            throw new InvalidBookingRequestException("request_id value cannot be empty");
        if (isNull(checkIn)) throw new InvalidBookingRequestException("check_in value cannot be empty");
        if (nights <= 0) throw new InvalidBookingRequestException("nights value has to be greater than zero");
        if (sellingRate <= 0)
            throw new InvalidBookingRequestException("selling_rate value has to be greater than zero");
        if (margin < 0) throw new InvalidBookingRequestException(("margin value cannot be negative"));
    }

    public double getTotalProfit() {
        return (sellingRate * (margin * PERCENTAGE));
    }

    public double getProfitPerNight() {
        return getTotalProfit() / nights;
    }

    public LocalDate getCheckOut() {
        return checkIn.plusDays(nights);
    }

    public boolean overlaps(BookingCandidate secondDate) {
        return this.checkIn().isBefore(secondDate.getCheckOut())
                && secondDate.checkIn().isBefore(this.getCheckOut());
    }
}