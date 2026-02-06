package com.booking.unit.application.service;

import com.booking.domain.BookingProfitStats;
import com.booking.domain.BookingCandidate;
import com.booking.domain.exception.InvalidStatsRequestException;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.application.service.BookingProfitStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookingProfitStatsServiceTest {

    private BookingProfitStatsCalculator bookingProfitStatsCalculator;

    @BeforeEach
    void setup() {
        bookingProfitStatsCalculator = new BookingProfitStatsService();
    }

    @Test
    void shouldReturnStatsResult() {
        List<BookingCandidate> bookingCandidates = List.of(
                new BookingCandidate("bookata_XY123", LocalDate.of(2020, 1, 1), 1, 50, 20),
                new BookingCandidate("kayete_PP234", LocalDate.of(2020, 1, 4), 1, 55, 22),
                new BookingCandidate("trivoltio_ZX69", LocalDate.of(2020, 1, 7), 1, 49, 21)
        );

        BookingProfitStats result = bookingProfitStatsCalculator.calculateStats(bookingCandidates);
        assertEquals(10.80, result.averageProfitPerNight());
        assertEquals(12.1, result.maximumProfitPerNight());
        assertEquals(10, result.minimumProfitPerNight());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowInvalidStatsRequestExceptionWhenBookingRequestIsEmpty(List<BookingCandidate> bookingCandidates) {
        assertThrows(InvalidStatsRequestException.class, () -> bookingProfitStatsCalculator.calculateStats(bookingCandidates));
    }
}