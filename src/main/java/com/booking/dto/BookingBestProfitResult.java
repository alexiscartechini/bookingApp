package com.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BookingBestProfitResult(
        @JsonProperty("request_ids") List<String> requestIds,
        @JsonProperty("total_profit") double totalProfit,
        @JsonProperty("avg_night") double averageProfitPerNight,
        @JsonProperty("min_night") double minimumProfitPerNight,
        @JsonProperty("max_night") double maximumProfitPerNight) {
}