package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.model.Debe;
import com.dauut.EksiDebeFetcher.model.Entry;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledFetchService {

    private static final Logger logger = LogManager.getLogger(ScheduledFetchService.class);

    private final DebeListBuildService debeListBuildService;

    public ScheduledFetchService(DebeListBuildService debeListBuildService) {
        this.debeListBuildService = debeListBuildService;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void fetchDebeList() {
        logger.info("Fetch debe list started");
        Debe debe = debeListBuildService.buildDebe();
        for (Entry e: debe.getDebeEntries()){
            logger.debug(e.getEntryId());
            logger.debug(e.getHeader());
        }
        logger.info("Debe list printed.");
    }

}
