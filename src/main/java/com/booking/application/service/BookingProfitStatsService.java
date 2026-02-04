package com.booking.application.service;

import com.booking.domain.BookingRequest;
import com.booking.domain.exception.InvalidStatsRequestException;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.infrastructure.controller.dto.BookingProfitStatsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BookingProfitStatsService implements BookingProfitStatsCalculator {

    @Override
    public BookingProfitStatsResponse calculateStats(List<BookingRequest> bookingRequests) {

        if (isNull(bookingRequests) || bookingRequests.isEmpty()) {
            throw new InvalidStatsRequestException("Cannot calculate stats for empty booking list");
        }

        return new BookingProfitStatsResponse(getAverageProfitPerNight(bookingRequests),
                getMinimumProfitPerNight(bookingRequests),
                getMaximumProfitPerNight(bookingRequests));
    }

    private double getAverageProfitPerNight(List<BookingRequest> bookingRequests) {
        double average = bookingRequests.stream()
                .mapToDouble(BookingRequest::getProfitPerNight)
                .average()
                .orElse(0.0);
        return roundDecimals(average);
    }

    private double getMinimumProfitPerNight(List<BookingRequest> bookingRequests) {
        return bookingRequests.stream()
                .mapToDouble(BookingRequest::getProfitPerNight)
                .min()
                .orElse(0);
    }

    private double getMaximumProfitPerNight(List<BookingRequest> bookingRequests) {
        return bookingRequests.stream()
                .mapToDouble(BookingRequest::getProfitPerNight)
                .max()
                .orElse(0);
    }

    private double roundDecimals(double average) {
        return Math.round(average * 100.0) / 100.0;
    }
}