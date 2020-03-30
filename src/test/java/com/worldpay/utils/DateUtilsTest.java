package com.worldpay.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DateUtilsTest {

    @Test
    void shouldReturnTrueForDateTimeValueBeingSetCorrectly(){
        LocalDateTime expectedLocalDateTime=LocalDateTime.of(2020,3,29,22,1,2);
        LocalDateTime actualLocalDateTime = DateUtils.toLocalDateTime("2020-03-29 22:01:02");
        assertThat(actualLocalDateTime).isEqualToIgnoringNanos(expectedLocalDateTime);
    }
}