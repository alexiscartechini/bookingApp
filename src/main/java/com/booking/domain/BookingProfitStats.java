package com.booking.domain;

public record BookingProfitStats(
        double averagePerNight,
        double minPerNight,
        double maxPerNight
) {
}