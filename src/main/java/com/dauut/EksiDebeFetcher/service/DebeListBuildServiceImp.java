package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.dao.htmlfetcher.HtmlParseService;
import com.dauut.EksiDebeFetcher.model.Debe;
import com.dauut.EksiDebeFetcher.model.Entry;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DebeListBuildServiceImp implements DebeListBuildService {

    private static final Logger logger = LogManager.getLogger(DebeListBuildServiceImp.class);
    private static final String ISTANBUL_TIME_ZONE = "Europe/Istanbul";

    @NotNull
    private final HtmlParseService htmlParseService;

    public DebeListBuildServiceImp(@NotNull HtmlParseService htmlParseService) {
        this.htmlParseService = htmlParseService;
    }

    @Override
    // can private, decide later
    public List<Entry> fetchEntryList() {
        List<Entry> entryList = new ArrayList<>();
        List<String> headers = htmlParseService.collectHeaders();
        List<String> urls = htmlParseService.collectEntryUrls();
        List<Integer> idList = htmlParseService.collectEntryIds();
        logger.info("Debe lists collected. headerList: " + headers.size() + "; urls: " + urls.size()+
                "; idList: " + idList.size() );
        buildEntryList(headers, urls, idList, entryList);
        return entryList;
    }

    private void buildEntryList(List<String> headers, List<String> urls, List<Integer> idList, List<Entry> entryList) {
        int size = headers.size();

        for (int i = 0; i < size; i++) {
            String header = headers.get(i);
            String url = urls.get(i);
            int id = idList.get(i);
            entryList.add(new Entry(id, url, header));
        }
        logger.info("Debe built: list size: " + entryList.size());
    }

    @Override
    public Map<Integer, String> getIdUrlMap() {
        return null;
    }

    @Override
    public Debe buildDebe() {

        List<Entry> entryList = fetchEntryList();
        LocalDate today = LocalDate.now();
        int entryCount = entryList.size();

        Instant now = Instant.now();
        ZonedDateTime istanbulDateTime = now.atZone(ZoneId.of(ISTANBUL_TIME_ZONE));
        LocalDateTime istanbulLocaltime = istanbulDateTime.toLocalDateTime();

        return new Debe(entryList,today,entryCount, istanbulLocaltime);
    }
}