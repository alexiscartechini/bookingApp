package com.booking.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingProfitStats(
        @JsonProperty("avg_night") double averageProfitPerNight,
        @JsonProperty("min_night") double minimumProfitPerNight,
        @JsonProperty("max_night") double maximumProfitPerNight) {
}