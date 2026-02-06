package com.booking.infrastructure.controller.mapper;

import com.booking.domain.BookingBestProfitResult;
import com.booking.infrastructure.controller.dto.BookingBestProfitResponse;

public class BookingBestProfitMapper {

    private BookingBestProfitMapper() {
    }

    public static BookingBestProfitResponse toResponse(BookingBestProfitResult bookingBestProfitResult) {
        return new BookingBestProfitResponse(bookingBestProfitResult.requestIds(),
                bookingBestProfitResult.totalProfit(),
                bookingBestProfitResult.averageProfitPerNight(),
                bookingBestProfitResult.minimumProfitPerNight(),
                bookingBestProfitResult.maximumProfitPerNight());
    }
}