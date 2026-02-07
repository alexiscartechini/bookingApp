package com.booking.unit.infrastructure.controller;

import com.booking.domain.BookingCandidate;
import com.booking.domain.BookingProfitStats;
import com.booking.domain.port.BookingProfitStatsCalculator;
import com.booking.infrastructure.controller.StatsController;
import com.booking.infrastructure.controller.dto.BookingProfitStatsResponse;
import com.booking.infrastructure.controller.dto.BookingRequest;
import com.booking.infrastructure.controller.mapper.BookingRequestMapper;
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
        List<BookingRequest> bookingRequests = List.of(
                new BookingRequest("bookata_XY123", LocalDate.of(2020, 1, 1), 5, 200, 20),
                new BookingRequest("kayete_PP234", LocalDate.of(2020, 1, 4), 4, 156, 5),
                new BookingRequest("atropote_AA930", LocalDate.of(2020, 1, 4), 4, 150, 6),
                new BookingRequest("acme_AAAAA", LocalDate.of(2020, 1, 10), 4, 160, 30));

        List<BookingCandidate> bookingCandidates = bookingRequests.stream()
                .map(BookingRequestMapper::toDomain)
                .toList();

        BookingProfitStats bookingProfitStats = new BookingProfitStats(10, 8, 12);
        when(bookingProfitStatsCalculator.calculateStats(bookingCandidates)).thenReturn(bookingProfitStats);

        BookingProfitStatsResponse expected = new BookingProfitStatsResponse(10, 8, 12);
        assertEquals(expected, statsController.stats(bookingRequests));
    }
}