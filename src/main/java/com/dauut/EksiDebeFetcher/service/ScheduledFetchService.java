package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.dao.htmlfetcher.HtmlFetcher;
import com.dauut.EksiDebeFetcher.model.Debe;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class ScheduledFetchService {

    private static final Logger logger = LogManager.getLogger(ScheduledFetchService.class);

    private final DebeListBuildService debeListBuildService;
    private final HtmlFetcher htmlFetcher;

    private Debe existedDebe = null;

    public ScheduledFetchService(DebeListBuildService debeListBuildService, HtmlFetcher htmlFetcher) {
        this.debeListBuildService = debeListBuildService;
        this.htmlFetcher = htmlFetcher;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void fetchDebeList() {
        /*
        logger.info("Fetch debe list started");
        htmlFetcher.createTodayHtmlPageDoc(); // trigger to create today html page static document.
        Debe debe = debeListBuildService.buildDebe();
        if (existedDebe != null && existedDebe.equals(debe))
            logger.warn("Today's debe list already existed.");
        else{
            existedDebe = debe;
            // todo save debe to db.
            logger.info("Debe list recorded");
        }

         */

        Instant now = Instant.now();
        ZonedDateTime istanbul =  now.atZone(ZoneId.of("Europe/Istanbul"));
        LocalDateTime dateTime = istanbul.toLocalDateTime();
        logger.info(dateTime);
    }

}
