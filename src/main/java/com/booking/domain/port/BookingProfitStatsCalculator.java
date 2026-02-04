package com.booking.domain.port;

import com.booking.domain.BookingRequest;
import com.booking.infrastructure.controller.dto.BookingProfitStatsResponse;

import java.util.List;

public interface BookingProfitStatsCalculator {

    BookingProfitStatsResponse calculateStats(List<BookingRequest> bookingRequests);
}