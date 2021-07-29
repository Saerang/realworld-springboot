package io.realworld;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTest {

    @Test
    void convertISO861() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime);
    }
}
