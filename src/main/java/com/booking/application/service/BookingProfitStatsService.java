package com.booking.application.service;

import com.booking.domain.BookingProfitStats;
import com.booking.domain.BookingCandidate;
import com.booking.domain.exception.InvalidStatsRequestException;
import com.booking.domain.port.BookingProfitStatsCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BookingProfitStatsService implements BookingProfitStatsCalculator {

    @Override
    public BookingProfitStats calculateStats(List<BookingCandidate> bookingCandidates) {

        if (isNull(bookingCandidates) || bookingCandidates.isEmpty()) {
            throw new InvalidStatsRequestException("Cannot calculate stats for empty booking list");
        }

        return new BookingProfitStats(getAverageProfitPerNight(bookingCandidates),
                getMinimumProfitPerNight(bookingCandidates),
                getMaximumProfitPerNight(bookingCandidates));
    }

    private double getAverageProfitPerNight(List<BookingCandidate> bookingCandidates) {
        double average = bookingCandidates.stream()
                .mapToDouble(BookingCandidate::getProfitPerNight)
                .average()
                .orElse(0.0);
        return roundDecimals(average);
    }

    private double getMinimumProfitPerNight(List<BookingCandidate> bookingCandidates) {
        return bookingCandidates.stream()
                .mapToDouble(BookingCandidate::getProfitPerNight)
                .min()
                .orElse(0);
    }

    private double getMaximumProfitPerNight(List<BookingCandidate> bookingCandidates) {
        return bookingCandidates.stream()
                .mapToDouble(BookingCandidate::getProfitPerNight)
                .max()
                .orElse(0);
    }

    private double roundDecimals(double average) {
        return Math.round(average * 100.0) / 100.0;
    }
}