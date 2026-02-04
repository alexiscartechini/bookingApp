package com.booking.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingProfitStatsResponse(
        @JsonProperty("avg_night") double avgNight,
        @JsonProperty("min_night") double minNight,
        @JsonProperty("max_night") double maxNight) {
}