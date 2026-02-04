package com.booking.domain.port;

import com.booking.domain.BookingRequest;
import com.booking.infrastructure.controller.dto.BookingProfitStats;

import java.util.List;

public interface BookingProfitStatsCalculator {

    BookingProfitStats calculateStats(List<BookingRequest> bookingRequests);
}