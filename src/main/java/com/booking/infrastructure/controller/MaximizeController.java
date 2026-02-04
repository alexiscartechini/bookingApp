package com.booking.infrastructure.controller;

import com.booking.application.usecase.MaximizeBookingProfitUseCase;
import com.booking.domain.BookingRequest;
import com.booking.infrastructure.controller.dto.BookingBestProfitResult;
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
    public BookingBestProfitResult getBestProfitCombination(@RequestBody List<BookingRequest> bookingRequests) {
        return maximizeBookingProfitUseCase.execute(bookingRequests);
    }
}