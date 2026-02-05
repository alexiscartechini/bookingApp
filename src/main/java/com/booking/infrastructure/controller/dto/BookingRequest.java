package com.booking.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record BookingRequest(@JsonProperty("request_id") String requestId,
                             @JsonProperty("check_in") LocalDate checkIn,
                             int nights,
                             @JsonProperty("selling_rate") double sellingRate,
                             double margin) {
}