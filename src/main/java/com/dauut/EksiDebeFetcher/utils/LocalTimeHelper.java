package com.dauut.EksiDebeFetcher.utils;

import java.time.*;

public class LocalTimeHelper {
    String timeZone;

    public LocalTimeHelper(String timeZone) {
        this.timeZone = timeZone;
    }

    public LocalDate getZonedLocalDateNow(){
        Instant now = Instant.now();
        ZonedDateTime zonedTime = now.atZone(ZoneId.of(timeZone));
        return zonedTime.toLocalDate();
    }

    public LocalDateTime getZonedLocalDateTimeNow(){
        Instant now = Instant.now();
        ZonedDateTime zonedTime = now.atZone(ZoneId.of(timeZone));
        return zonedTime.toLocalDateTime();
    }

}
