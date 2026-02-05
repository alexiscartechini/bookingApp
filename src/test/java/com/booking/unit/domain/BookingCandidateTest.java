package com.booking.unit.domain;

import com.booking.domain.BookingCandidate;
import com.booking.domain.exception.InvalidBookingRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BookingCandidateTest {

    private static final String REQUEST_ID = "bookata_XY222";
    private static final LocalDate CHECK_IN = LocalDate.of(2019, 12, 28);
    private BookingCandidate bookingCandidate;

    public static Stream<Arguments> bookingRequestWithZeroValues() {
        return Stream.of(
                Arguments.of(0, 200, "nights value has to be greater than zero"),
                Arguments.of(5, 0, "selling_rate value has to be greater than zero"));
    }

    public static Stream<Arguments> bookingRequestWithNullOrEmptyValues() {
        return Stream.of(
                Arguments.of(REQUEST_ID, null, "check_in value cannot be empty"),
                Arguments.of(null, CHECK_IN, "request_id value cannot be empty"),
                Arguments.of("", CHECK_IN, "request_id value cannot be empty"));
    }

    @BeforeEach
    void setup() {
        bookingCandidate = new BookingCandidate("bookata_XY123", LocalDate.of(2020, 1, 1), 5, 200, 20);
    }

    @Test
    void shouldReturnTotalProfit() {
        assertEquals(40.0, bookingCandidate.getTotalProfit());
    }

    @Test
    void shouldReturnProfitPerNight() {
        assertEquals(8.0, bookingCandidate.getProfitPerNight());
    }

    @Test
    void shouldReturnCheckOutDate() {
        assertEquals(LocalDate.of(2020, 1, 6), bookingCandidate.getCheckOut());
    }

    @Test
    void shouldReturnTrueWhenDatesOverlaps() {
        BookingCandidate secondDate = new BookingCandidate(REQUEST_ID, LocalDate.of(2020, 1, 5), 3, 200, 20);
        assertTrue(bookingCandidate.overlaps(secondDate));
    }

    @Test
    void shouldReturnFalseWhenDatesDoNotOverlaps() {
        BookingCandidate secondDate = new BookingCandidate(REQUEST_ID, CHECK_IN, 3, 200, 20);
        assertFalse(bookingCandidate.overlaps(secondDate));
    }

    @ParameterizedTest
    @MethodSource("bookingRequestWithZeroValues")
    void shouldReturnInvalidBookingRequestExceptionWhenValuesAreZero(int nights, double sellingRate, String message) {
        InvalidBookingRequestException exception = assertThrows(InvalidBookingRequestException.class, () -> new BookingCandidate(REQUEST_ID, CHECK_IN, nights, sellingRate, 20));
        assertEquals(
                message,
                exception.getMessage()
        );
    }

    @ParameterizedTest
    @MethodSource("bookingRequestWithNullOrEmptyValues")
    void shouldReturnInvalidBookingRequestExceptionWhenValuesAreNullOrEmpty(String requestId, LocalDate checkIn, String message) {
        InvalidBookingRequestException exception = assertThrows(InvalidBookingRequestException.class, () -> new BookingCandidate(requestId, checkIn, 3, 200, 20));
        assertEquals(
                message,
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnInvalidBookingRequestExceptionWhenMarginIsNegative() {
        InvalidBookingRequestException exception = assertThrows(InvalidBookingRequestException.class, () -> new BookingCandidate(REQUEST_ID, CHECK_IN, 3, 200, -1));
        assertEquals(
                "margin value cannot be negative",
                exception.getMessage()
        );
    }
}