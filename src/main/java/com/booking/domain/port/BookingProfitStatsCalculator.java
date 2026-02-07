package com.booking.domain.port;

import com.booking.domain.BookingCandidate;
import com.booking.domain.BookingProfitStats;

import java.util.List;

public interface BookingProfitStatsCalculator {

    BookingProfitStats calculateStats(List<BookingCandidate> bookingCandidates);
}