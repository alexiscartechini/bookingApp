package com.booking.e2e;

import com.booking.infrastructure.controller.dto.BookingProfitStatsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingProfitStatsE2ETest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnStatsValues() {
        // given
        List<Map<String, Object>> request = List.of(
                Map.of(
                        "request_id", "bookata_XY123",
                        "check_in", "2020-01-01",
                        "nights", 5,
                        "selling_rate", 200,
                        "margin", 20
                ),
                Map.of(
                        "request_id", "kayete_PP234",
                        "check_in", "2020-01-04",
                        "nights", 4,
                        "selling_rate", 156,
                        "margin", 22
                )
        );

        // when
        ResponseEntity<BookingProfitStatsResponse> response =
                restTemplate.postForEntity("/stats", request, BookingProfitStatsResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        BookingProfitStatsResponse result = response.getBody();
        assertThat(result).isNotNull();

        assertThat(result.averageProfitPerNight()).isEqualTo(8.29);
        assertThat(result.minimumProfitPerNight()).isEqualTo(8);
        assertThat(result.maximumProfitPerNight()).isEqualTo(8.58);
    }
}