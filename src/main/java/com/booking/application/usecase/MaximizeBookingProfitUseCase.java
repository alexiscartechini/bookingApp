package com.booking.application.usecase;

import com.booking.domain.BookingBestProfitResult;
import com.booking.domain.BookingProfitStats;
import com.booking.domain.BookingCandidate;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.domain.port.MaximizeBookingProfitPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaximizeBookingProfitUseCase {

    private final BookingProfitStatsCalculator bookingProfitStatsCalculator;
    private final MaximizeBookingProfitPort maximizeBookingProfitPort;

    public MaximizeBookingProfitUseCase(BookingProfitStatsCalculator bookingProfitStatsCalculator, MaximizeBookingProfitPort maximizeBookingProfitPort) {
        this.bookingProfitStatsCalculator = bookingProfitStatsCalculator;
        this.maximizeBookingProfitPort = maximizeBookingProfitPort;
    }

    public BookingBestProfitResult execute(List<BookingCandidate> bookingCandidates) {
        List<BookingCandidate> bestProfitCombination = maximizeBookingProfitPort.selectBestCombination(bookingCandidates);
        BookingProfitStats bookingProfitStats = bookingProfitStatsCalculator.calculateStats(bestProfitCombination);
        List<String> requestIds = bestProfitCombination.stream().map(BookingCandidate::requestId).toList();
        double totalProfit = bestProfitCombination.stream().mapToDouble(BookingCandidate::getTotalProfit).sum();
        return new BookingBestProfitResult(requestIds, totalProfit, bookingProfitStats.averagePerNight(), bookingProfitStats.minPerNight(), bookingProfitStats.maxPerNight());
    }
}