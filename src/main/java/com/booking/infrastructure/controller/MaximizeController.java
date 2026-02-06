package com.booking.infrastructure.controller;

import com.booking.application.usecase.MaximizeBookingProfitUseCase;
import com.booking.domain.BookingBestProfitResult;
import com.booking.domain.BookingCandidate;
import com.booking.infrastructure.controller.dto.BookingBestProfitResponse;
import com.booking.infrastructure.controller.dto.BookingRequest;
import com.booking.infrastructure.controller.mapper.BookingBestProfitMapper;
import com.booking.infrastructure.controller.mapper.BookingRequestMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maximize")
public class MaximizeController {

    private final MaximizeBookingProfitUseCase maximizeBookingProfitUseCase;

    public MaximizeController(MaximizeBookingProfitUseCase maximizeBookingProfitUseCase) {
        this.maximizeBookingProfitUseCase = maximizeBookingProfitUseCase;
    }

    @PostMapping
    public BookingBestProfitResponse getBestProfitCombination(@RequestBody List<BookingRequest> bookingRequests) {
        List<BookingCandidate> bookingCandidates = bookingRequests.stream()
                .map(BookingRequestMapper::toDomain)
                .toList();
        BookingBestProfitResult bookingBestProfitResult = maximizeBookingProfitUseCase.execute(bookingCandidates);

        return BookingBestProfitMapper.toResponse(bookingBestProfitResult);
    }
}