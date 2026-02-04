package com.booking.service;

import com.booking.domain.BookingRequest;
import com.booking.domain.port.MaximizeBookingProfitPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MaximizeBookingProfitService implements MaximizeBookingProfitPort {

    @Override
    public List<BookingRequest> selectBestCombination(List<BookingRequest> bookings) {
        List<BookingRequest> orderedBooking = orderByCheckIn(bookings);
        List<List<BookingRequest>> validCombinations = generateValidCombinations(orderedBooking);
        return getHigherProfitOption(validCombinations);
    }

    private List<List<BookingRequest>> generateValidCombinations(List<BookingRequest> bookings) {
        List<List<BookingRequest>> combinations = new ArrayList<>();
        combinations.add(List.of());

        for (BookingRequest booking : bookings) {
            List<List<BookingRequest>> partialList = new ArrayList<>();

            for (List<BookingRequest> existing : combinations) {
                if (!isOverlapped(existing, booking)) {
                    List<BookingRequest> copy = new ArrayList<>(existing);
                    copy.add(booking);
                    partialList.add(copy);
                }
            }
            combinations.addAll(partialList);
        }
        return combinations;
    }

    private List<BookingRequest> getHigherProfitOption(List<List<BookingRequest>> allCombinations) {
        return allCombinations.stream().max(Comparator.comparing(this::getTotalFromList)).orElse(List.of());
    }

    private boolean isOverlapped(List<BookingRequest> firstDateList, BookingRequest secondDate) {
        return firstDateList.stream().anyMatch(firstDate -> (firstDate.overlaps(secondDate)));
    }

    private List<BookingRequest> orderByCheckIn(List<BookingRequest> bookingRequests) {
        return bookingRequests.stream().sorted(Comparator.comparing(BookingRequest::checkIn)).toList();
    }

    private double getTotalFromList(List<BookingRequest> bookingRequestList) {
        return bookingRequestList.stream().mapToDouble(BookingRequest::getTotalProfit).sum();
    }
}