package com.dauut.EksiDebeFetcher.service;

import java.time.LocalDate;

public interface CheckDebeService {
    boolean entryCountsMatched(LocalDate date);

}
