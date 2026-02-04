package com.booking.domain.port;

import com.booking.domain.BookingRequest;

import java.util.List;

public interface MaximizeBookingProfitPort {
    List<BookingRequest> selectBestCombination(List<BookingRequest> bookings);
}