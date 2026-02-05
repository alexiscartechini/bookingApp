package com.booking.domain.port;

import com.booking.domain.BookingProfitStats;
import com.booking.domain.BookingCandidate;

import java.util.List;

public interface BookingProfitStatsCalculator {

    BookingProfitStats calculateStats(List<BookingCandidate> bookingCandidates);
}