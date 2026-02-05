package com.booking.unit.infrastructure.controller;

import com.booking.domain.BookingProfitStats;
import com.booking.domain.BookingCandidate;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.infrastructure.controller.StatsController;
import com.booking.infrastructure.controller.dto.BookingProfitStatsResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatsControllerTest {

    private final BookingProfitStatsCalculator bookingProfitStatsCalculator = mock(BookingProfitStatsCalculator.class);
    private final StatsController statsController = new StatsController(bookingProfitStatsCalculator);

    @Test
    void shouldReturnStatsResult() {
        List<BookingCandidate> bookingCandidate = List.of(
                new BookingCandidate("bookata_XY123", LocalDate.of(2020, 1, 1), 5, 200, 20),
                new BookingCandidate("kayete_PP234", LocalDate.of(2020, 1, 4), 4, 156, 5),
                new BookingCandidate("atropote_AA930", LocalDate.of(2020, 1, 4), 4, 150, 6),
                new BookingCandidate("acme_AAAAA", LocalDate.of(2020, 1, 10), 4, 160, 30));

        BookingProfitStats bookingProfitStats = new BookingProfitStats(10, 8, 12);
        when(bookingProfitStatsCalculator.calculateStats(bookingCandidate)).thenReturn(bookingProfitStats);

        BookingProfitStatsResponse expected = new BookingProfitStatsResponse(10,8,12);
        assertEquals(expected, statsController.stats(bookingCandidate));
    }
}