package com.booking.domain.port;

import com.booking.domain.BookingCandidate;

import java.util.List;

public interface MaximizeBookingProfitPort {
    List<BookingCandidate> selectBestCombination(List<BookingCandidate> bookings);
}