package com.booking.unit.application.service;

import com.booking.domain.BookingCandidate;
import com.booking.domain.port.MaximizeBookingProfitPort;
import com.booking.application.service.MaximizeBookingProfitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximizeBookingProfitServiceTest {

    private List<BookingCandidate> bookingCandidates;
    private MaximizeBookingProfitPort maximizeBookingProfitPort;

    @BeforeEach
    void setup() {
        bookingCandidates = new ArrayList<>();
        maximizeBookingProfitPort = new MaximizeBookingProfitService();
    }

    @Test
    void shouldReturnNotOverlappedDates() {
        bookingCandidates.add(new BookingCandidate("A", LocalDate.of(2020, 1, 1), 5, 200, 20));
        bookingCandidates.add(new BookingCandidate("B", LocalDate.of(2020, 1, 7), 4, 200, 20));
        bookingCandidates.add(new BookingCandidate("C", LocalDate.of(2020, 1, 1), 5, 200, 20));
        bookingCandidates.add(new BookingCandidate("D", LocalDate.of(2020, 1, 7), 4, 200, 20));

        assertEquals(2, maximizeBookingProfitPort.selectBestCombination(bookingCandidates).size());
    }

    @Test
    void shouldReturnEmptyList() {
        assertEquals(List.of(), maximizeBookingProfitPort.selectBestCombination(bookingCandidates));
    }
}