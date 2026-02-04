package com.booking.unit.service;

import com.booking.domain.BookingRequest;
import com.booking.domain.exception.InvalidStatsRequestException;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.dto.BookingProfitStats;
import com.booking.service.BookingProfitStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StatsServiceTest {

    private BookingProfitStatsCalculator bookingProfitStatsCalculator;

    @BeforeEach
    void setup() {
        bookingProfitStatsCalculator = new BookingProfitStatsService();
    }

    @Test
    void shouldReturnStatsResult() {
        List<BookingRequest> bookingRequests = List.of(
                new BookingRequest("bookata_XY123", LocalDate.of(2020, 1, 1), 1, 50, 20),
                new BookingRequest("kayete_PP234", LocalDate.of(2020, 1, 4), 1, 55, 22),
                new BookingRequest("trivoltio_ZX69", LocalDate.of(2020, 1, 7), 1, 49, 21)
        );

        BookingProfitStats result = bookingProfitStatsCalculator.calculateStats(bookingRequests);
        assertEquals(10.80, result.averageProfitPerNight());
        assertEquals(12.1, result.maximumProfitPerNight());
        assertEquals(10, result.minimumProfitPerNight());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowInvalidStatsRequestExceptionWhenBookingRequestIsEmpty(List<BookingRequest> bookingRequests) {
        assertThrows(InvalidStatsRequestException.class, () -> bookingProfitStatsCalculator.calculateStats(bookingRequests));
    }
}