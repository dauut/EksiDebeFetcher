package com.dauut.EksiDebeFetcher.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mockito;
import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduledFetchServiceTest {

    @SpyBean
    private ScheduledFetchService fetchService;

    @Test
    public void fetchDebeList_shouldWorkScheduled() {
        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> verify(fetchService, atLeast(1)).fetchDebeList());
    }
}