package com.booking.unit.infrastructure.controller;

import com.booking.application.usecase.MaximizeBookingProfitUseCase;
import com.booking.domain.BookingRequest;
import com.booking.infrastructure.controller.dto.BookingBestProfitResult;
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
        List<BookingRequest> bookingRequest = List.of(
                new BookingRequest("bookata_XY123", LocalDate.of(2020, 1, 1), 5, 200, 20),
                new BookingRequest("kayete_PP234", LocalDate.of(2020, 1, 4), 4, 156, 5),
                new BookingRequest("atropote_AA930", LocalDate.of(2020, 1, 4), 4, 150, 6),
                new BookingRequest("acme_AAAAA", LocalDate.of(2020, 1, 10), 4, 160, 30));

        BookingBestProfitResult expected = new BookingBestProfitResult(List.of("bookata_XY123", "acme_AAAAA"), 88, 10, 8, 12);

        when(maximizeBookingProfitUseCase.execute(bookingRequest)).thenReturn(expected);
        assertEquals(expected, maximizeController.getBestProfitCombination(bookingRequest));
    }
}