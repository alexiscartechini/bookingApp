package com.booking.domain;

import java.util.List;

public record BookingBestProfitResult(
        List<String> requestIds,
        double totalProfit,
        double averageProfitPerNight,
        double minimumProfitPerNight,
        double maximumProfitPerNight) {
}