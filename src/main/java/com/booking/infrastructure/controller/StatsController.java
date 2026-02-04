package com.booking.infrastructure.controller;

import com.booking.domain.BookingRequest;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.dto.BookingProfitStats;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final BookingProfitStatsCalculator bookingProfitStatsCalculator;

    public StatsController(BookingProfitStatsCalculator bookingProfitStatsCalculator) {
        this.bookingProfitStatsCalculator = bookingProfitStatsCalculator;
    }

    @PostMapping
    public BookingProfitStats stats(@RequestBody List<BookingRequest> bookingRequests) {
        return bookingProfitStatsCalculator.calculateStats(bookingRequests);
    }
}