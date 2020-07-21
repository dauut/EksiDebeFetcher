package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.dao.EntryDatabaseConnectionService;
import com.dauut.EksiDebeFetcher.dao.htmlfetcher.HtmlFetcher;
import com.dauut.EksiDebeFetcher.model.Debe;
import com.dauut.EksiDebeFetcher.utils.ConfigurationParams;
import com.dauut.EksiDebeFetcher.utils.LocalTimeHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ScheduledFetchService {

    private static final Logger logger = LogManager.getLogger(ScheduledFetchService.class);

    private final DebeListBuildService debeListBuildService;
    private final HtmlFetcher htmlFetcher;
    private final EntryDatabaseConnectionService entryController;

    private LocalDate lastSavedDate = null;

    public ScheduledFetchService(DebeListBuildService debeListBuildService, HtmlFetcher htmlFetcher,
                                 EntryDatabaseConnectionService entryController) {
        this.debeListBuildService = debeListBuildService;
        this.htmlFetcher = htmlFetcher;
        this.entryController = entryController;
    }

    @Scheduled(cron = "0 0/1 * * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease() {
        logger.info("Fetch debe list started");
        htmlFetcher.createTodayHtmlPageDoc(); // trigger to create today html page static document.
        Debe debe = debeListBuildService.buildDebe();
        if (lastSavedDate != null && lastSavedDate.equals(debe.getDate())) {
            logger.warn("Today's debe list already existed.");
        } else {
            lastSavedDate = debe.getDate();
            try {
                entryController.initRecordQueries(debe);
            } catch (InterruptedException e) {
                logger.error("Record queries error!");
                e.printStackTrace();
            }
        }
    }
}
