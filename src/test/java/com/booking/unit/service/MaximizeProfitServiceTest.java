package com.booking.unit.service;

import com.booking.domain.BookingRequest;
import com.booking.domain.port.MaximizeBookingProfitPort;
import com.booking.service.MaximizeBookingProfitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximizeProfitServiceTest {

    private List<BookingRequest> bookingRequests;
    private MaximizeBookingProfitPort maximizeBookingProfitPort;

    @BeforeEach
    void setup() {
        bookingRequests = new ArrayList<>();
        maximizeBookingProfitPort = new MaximizeBookingProfitService();
    }

    @Test
    void shouldReturnNotOverlappedDates() {
        bookingRequests.add(new BookingRequest("A", LocalDate.of(2020, 1, 1), 5, 200, 20));
        bookingRequests.add(new BookingRequest("B", LocalDate.of(2020, 1, 7), 4, 200, 20));
        bookingRequests.add(new BookingRequest("C", LocalDate.of(2020, 1, 1), 5, 200, 20));
        bookingRequests.add(new BookingRequest("D", LocalDate.of(2020, 1, 7), 4, 200, 20));

        assertEquals(2, maximizeBookingProfitPort.selectBestCombination(bookingRequests).size());
    }

    @Test
    void shouldReturnEmptyList() {
        assertEquals(List.of(), maximizeBookingProfitPort.selectBestCombination(bookingRequests));
    }
}