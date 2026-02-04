package com.booking.e2e;

import com.booking.dto.BookingBestProfitResult;
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
class MaximizeBookingE2ETest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnBestCombination() {
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
                        "margin", 5
                ),
                Map.of(
                        "request_id", "atropote_AA930",
                        "check_in", "2020-01-04",
                        "nights", 4,
                        "selling_rate", 150,
                        "margin", 6
                ),
                Map.of(
                        "request_id", "acme_AAAAA",
                        "check_in", "2020-01-10",
                        "nights", 4,
                        "selling_rate", 160,
                        "margin", 30
                )
        );

        // when
        ResponseEntity<BookingBestProfitResult> response =
                restTemplate.postForEntity("/maximize", request, BookingBestProfitResult.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        BookingBestProfitResult result = response.getBody();
        assertThat(result).isNotNull();

        assertThat(result.requestIds())
                .containsExactly("bookata_XY123", "acme_AAAAA");

        assertThat(result.totalProfit()).isEqualTo(88.0);
        assertThat(result.averageProfitPerNight()).isEqualTo(10.0);
        assertThat(result.minimumProfitPerNight()).isEqualTo(8.0);
        assertThat(result.maximumProfitPerNight()).isEqualTo(12.0);
    }
}