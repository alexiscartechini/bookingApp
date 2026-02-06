package com.booking.domain;

public record BookingProfitStats(
        double averageProfitPerNight,
        double minimumProfitPerNight,
        double maximumProfitPerNight
) {
}