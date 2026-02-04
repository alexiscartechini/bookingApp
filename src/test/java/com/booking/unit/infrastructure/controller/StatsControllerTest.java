package com.booking.unit.infrastructure.controller;

import com.booking.domain.BookingRequest;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.infrastructure.controller.dto.BookingProfitStatsResponse;
import com.booking.infrastructure.controller.StatsController;
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
        List<BookingRequest> bookingRequest = List.of(
                new BookingRequest("bookata_XY123", LocalDate.of(2020, 1, 1), 5, 200, 20),
                new BookingRequest("kayete_PP234", LocalDate.of(2020, 1, 4), 4, 156, 5),
                new BookingRequest("atropote_AA930", LocalDate.of(2020, 1, 4), 4, 150, 6),
                new BookingRequest("acme_AAAAA", LocalDate.of(2020, 1, 10), 4, 160, 30));

        BookingProfitStatsResponse expected = new BookingProfitStatsResponse(10, 8, 12);
        when(bookingProfitStatsCalculator.calculateStats(bookingRequest)).thenReturn(expected);
        assertEquals(expected, statsController.stats(bookingRequest));
    }
}