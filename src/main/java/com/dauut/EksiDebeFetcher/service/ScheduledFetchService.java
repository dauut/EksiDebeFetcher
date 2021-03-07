package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.dao.htmlfetcher.HtmlFetcher;
import com.dauut.EksiDebeFetcher.model.Debe;
import com.dauut.EksiDebeFetcher.utils.ConfigurationParams;
import com.dauut.EksiDebeFetcher.utils.LocalTimeHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledFetchService {

    private static final Logger logger = LogManager.getLogger(ScheduledFetchService.class);

    private final DebeListBuildService debeListBuildService;
    private final HtmlFetcher htmlFetcher;
    private final EntryDatabaseConnectionService entryController;
    private final CheckDebeService checkDebeService;


    public ScheduledFetchService(DebeListBuildService debeListBuildService, HtmlFetcher htmlFetcher,
                                 EntryDatabaseConnectionService entryController, CheckDebeService checkDebeService) {
        this.debeListBuildService = debeListBuildService;
        this.htmlFetcher = htmlFetcher;
        this.entryController = entryController;
        this.checkDebeService = checkDebeService;
    }

    @Scheduled(cron = "0 0/15 * * * ?", zone = "Europe/Istanbul") //every 15 minutes
    public void fetchDebeListFirstRelease() {
        LocalTimeHelper localTimeHelper = new LocalTimeHelper(ConfigurationParams.ISTANBUL_TIME_ZONE);
        if (!checkDebeService.entryCountsMatched(localTimeHelper.getZonedLocalDateNow())){
            logger.warn("Today's debe is missing. Fetching again... ");
            fetchData();
        }else{
            logger.info("Today's debe looks fine.");
        }

    }

    private void fetchData() {
        logger.info("Starting fetch...");
        htmlFetcher.createTodayHtmlPageDoc(); // trigger to create today html page static document.
        Debe debe = debeListBuildService.buildDebe();
        try {
            entryController.initRecordQueries(debe);
        } catch (InterruptedException e) {
            logger.error("Record queries error!");
            e.printStackTrace();
        }
    }

}
