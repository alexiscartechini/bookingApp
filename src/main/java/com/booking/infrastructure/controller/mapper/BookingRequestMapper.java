package com.booking.infrastructure.controller.mapper;

import com.booking.domain.BookingCandidate;
import com.booking.infrastructure.controller.dto.BookingRequest;

public class BookingRequestMapper {

    private BookingRequestMapper() {
    }

    public static BookingCandidate toDomain(BookingRequest bookingRequest) {
        return new BookingCandidate(bookingRequest.requestId(),
                bookingRequest.checkIn(),
                bookingRequest.nights(),
                bookingRequest.sellingRate(),
                bookingRequest.margin());
    }
}