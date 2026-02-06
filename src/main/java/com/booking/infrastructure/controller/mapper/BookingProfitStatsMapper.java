package com.booking.infrastructure.controller.mapper;

import com.booking.domain.BookingProfitStats;
import com.booking.infrastructure.controller.dto.BookingProfitStatsResponse;

public class BookingProfitStatsMapper {

    private BookingProfitStatsMapper() {
    }

    public static BookingProfitStatsResponse toResponse(BookingProfitStats bookingProfitStats){
        return  new BookingProfitStatsResponse(
                bookingProfitStats.averageProfitPerNight(),
                bookingProfitStats.minimumProfitPerNight(),
                bookingProfitStats.maximumProfitPerNight()
        );
    }
}