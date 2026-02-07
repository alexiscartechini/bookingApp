package com.booking.domain;

import com.booking.domain.exception.InvalidBookingRequestException;

import java.time.LocalDate;

import static com.booking.domain.validation.BookingValidationMessages.*;
import static java.util.Objects.isNull;

public record BookingCandidate(String requestId, LocalDate checkIn, int nights, double sellingRate, double margin) {

    private static final double PERCENTAGE = 0.01;

    public BookingCandidate {
        if (isNull(requestId) || requestId.isBlank())
            throw new InvalidBookingRequestException(REQUEST_ID_EMPTY);
        if (isNull(checkIn)) throw new InvalidBookingRequestException(CHECK_IN_EMPTY);
        if (nights <= 0) throw new InvalidBookingRequestException(NIGHTS_INVALID);
        if (sellingRate <= 0)
            throw new InvalidBookingRequestException(SELLING_RATE_INVALID);
        if (margin < 0) throw new InvalidBookingRequestException((MARGIN_NEGATIVE));
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