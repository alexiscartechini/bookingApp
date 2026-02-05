package com.booking.application.service;

import com.booking.domain.BookingCandidate;
import com.booking.domain.port.MaximizeBookingProfitPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MaximizeBookingProfitService implements MaximizeBookingProfitPort {

    @Override
    public List<BookingCandidate> selectBestCombination(List<BookingCandidate> bookings) {
        List<BookingCandidate> orderedBooking = orderByCheckIn(bookings);
        List<List<BookingCandidate>> validCombinations = generateValidCombinations(orderedBooking);
        return getHigherProfitOption(validCombinations);
    }

    private List<List<BookingCandidate>> generateValidCombinations(List<BookingCandidate> bookings) {
        List<List<BookingCandidate>> combinations = new ArrayList<>();
        combinations.add(List.of());

        for (BookingCandidate booking : bookings) {
            List<List<BookingCandidate>> partialList = new ArrayList<>();

            for (List<BookingCandidate> existing : combinations) {
                if (!isOverlapped(existing, booking)) {
                    List<BookingCandidate> copy = new ArrayList<>(existing);
                    copy.add(booking);
                    partialList.add(copy);
                }
            }
            combinations.addAll(partialList);
        }
        return combinations;
    }

    private List<BookingCandidate> getHigherProfitOption(List<List<BookingCandidate>> allCombinations) {
        return allCombinations.stream().max(Comparator.comparing(this::getTotalFromList)).orElse(List.of());
    }

    private boolean isOverlapped(List<BookingCandidate> firstDateList, BookingCandidate secondDate) {
        return firstDateList.stream().anyMatch(firstDate -> (firstDate.overlaps(secondDate)));
    }

    private List<BookingCandidate> orderByCheckIn(List<BookingCandidate> bookingCandidates) {
        return bookingCandidates.stream().sorted(Comparator.comparing(BookingCandidate::checkIn)).toList();
    }

    private double getTotalFromList(List<BookingCandidate> bookingCandidateList) {
        return bookingCandidateList.stream().mapToDouble(BookingCandidate::getTotalProfit).sum();
    }
}