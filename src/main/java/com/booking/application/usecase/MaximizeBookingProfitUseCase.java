package com.booking.application.usecase;

import com.booking.domain.BookingRequest;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.domain.port.MaximizeBookingProfitPort;
import com.booking.infrastructure.controller.dto.BookingBestProfitResponse;
import com.booking.infrastructure.controller.dto.BookingProfitStatsResponse;
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

    public BookingBestProfitResponse execute(List<BookingRequest> bookingRequests) {
        List<BookingRequest> bestProfitCombination = maximizeBookingProfitPort.selectBestCombination(bookingRequests);
        BookingProfitStatsResponse bookingProfitStatsResponse = bookingProfitStatsCalculator.calculateStats(bestProfitCombination);
        List<String> requestIds = bestProfitCombination.stream().map(BookingRequest::requestId).toList();
        double totalProfit = bestProfitCombination.stream().mapToDouble(BookingRequest::getTotalProfit).sum();
        return new BookingBestProfitResponse(requestIds, totalProfit, bookingProfitStatsResponse.avgNight(), bookingProfitStatsResponse.minNight(), bookingProfitStatsResponse.maxNight());
    }
}