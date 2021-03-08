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

    /* not a very good solution but it works for now
    * Todo it should be fixed as every 15 minutes between 7:35 am and 11:00 PM
    * */
    @Scheduled(cron = "01 31 07 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease1() {
        fetchDebeListFirstRelease();
    }

    @Scheduled(cron = "01 35 07 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease2() {
        fetchDebeListFirstRelease();
    }

    @Scheduled(cron = "01 00 08 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease3() {
        fetchDebeListFirstRelease();
    }

    @Scheduled(cron = "01 00 09 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease4() {
        fetchDebeListFirstRelease();
    }

    @Scheduled(cron = "01 00 17 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease5() {
        fetchDebeListFirstRelease();
    }

    @Scheduled(cron = "01 00 23 * * *", zone="Europe/Istanbul")
    public void fetchDebeListFirstRelease6() {
        fetchDebeListFirstRelease();
    }

//    @Scheduled(cron = "0 0/1 * * * ?", zone = "Europe/Istanbul") //every 15 minutes
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
