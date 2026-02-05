package com.booking.unit.infrastructure.controller;

import com.booking.application.usecase.MaximizeBookingProfitUseCase;
import com.booking.domain.BookingCandidate;
import com.booking.infrastructure.controller.dto.BookingBestProfitResponse;
import com.booking.infrastructure.controller.MaximizeController;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MaximizeControllerTest {

    private final MaximizeBookingProfitUseCase maximizeBookingProfitUseCase = mock(MaximizeBookingProfitUseCase.class);
    private final MaximizeController maximizeController = new MaximizeController(maximizeBookingProfitUseCase);

    @Test
    void shouldReturnBookingResult() {
        List<BookingCandidate> bookingCandidate = List.of(
                new BookingCandidate("bookata_XY123", LocalDate.of(2020, 1, 1), 5, 200, 20),
                new BookingCandidate("kayete_PP234", LocalDate.of(2020, 1, 4), 4, 156, 5),
                new BookingCandidate("atropote_AA930", LocalDate.of(2020, 1, 4), 4, 150, 6),
                new BookingCandidate("acme_AAAAA", LocalDate.of(2020, 1, 10), 4, 160, 30));

        BookingBestProfitResponse expected = new BookingBestProfitResponse(List.of("bookata_XY123", "acme_AAAAA"), 88, 10, 8, 12);

        when(maximizeBookingProfitUseCase.execute(bookingCandidate)).thenReturn(expected);
        assertEquals(expected, maximizeController.getBestProfitCombination(bookingCandidate));
    }
}