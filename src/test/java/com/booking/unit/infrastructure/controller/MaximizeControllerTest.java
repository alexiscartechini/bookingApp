package com.booking.unit.infrastructure.controller;

import com.booking.application.usecase.MaximizeBookingProfitUseCase;
import com.booking.domain.BookingBestProfitResult;
import com.booking.domain.BookingCandidate;
import com.booking.infrastructure.controller.dto.BookingBestProfitResponse;
import com.booking.infrastructure.controller.MaximizeController;
import com.booking.infrastructure.controller.dto.BookingRequest;
import com.booking.infrastructure.controller.mapper.BookingBestProfitMapper;
import com.booking.infrastructure.controller.mapper.BookingRequestMapper;
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
    void shouldReturnBookingResponse() {
        List<BookingRequest> bookingRequests = List.of(
                new BookingRequest("bookata_XY123", LocalDate.of(2020, 1, 1), 5, 200, 20),
                new BookingRequest("kayete_PP234", LocalDate.of(2020, 1, 4), 4, 156, 5),
                new BookingRequest("atropote_AA930", LocalDate.of(2020, 1, 4), 4, 150, 6),
                new BookingRequest("acme_AAAAA", LocalDate.of(2020, 1, 10), 4, 160, 30));

        List<BookingCandidate> bookingCandidates = bookingRequests.stream()
                .map(BookingRequestMapper::toDomain)
                .toList();

        BookingBestProfitResult bookingBestProfitResult = new BookingBestProfitResult(List.of("bookata_XY123", "acme_AAAAA"), 88, 10, 8, 12);
        BookingBestProfitResponse expected = BookingBestProfitMapper.toResponse(bookingBestProfitResult);
        when(maximizeBookingProfitUseCase.execute(bookingCandidates)).thenReturn(bookingBestProfitResult);
        assertEquals(expected, maximizeController.getBestProfitCombination(bookingRequests));
    }
}