package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.dao.htmlfetcher.HtmlFetcher;
import com.dauut.EksiDebeFetcher.model.Debe;
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


    /* PRODUCTION TEST PURPOSES */
    /*
    @Scheduled(cron = "01 31 07 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease1() {
        fetchData();
    }

    @Scheduled(cron = "01 35 07 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease2() {
        fetchData();
    }

    @Scheduled(cron = "01 00 08 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease3() {
        fetchData();
    }

    @Scheduled(cron = "01 00 09 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease4() {
        fetchData();
    }

    @Scheduled(cron = "01 00 17 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease5() {
        fetchData();
    }
     */

    @Scheduled(cron = "*/30 * * * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease5() {
        fetchData();
    }

    private void fetchData() {
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
