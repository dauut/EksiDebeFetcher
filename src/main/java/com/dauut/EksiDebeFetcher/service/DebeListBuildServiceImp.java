package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.dao.htmlfetcher.HtmlParseService;
import com.dauut.EksiDebeFetcher.model.Debe;
import com.dauut.EksiDebeFetcher.model.Entry;
import com.dauut.EksiDebeFetcher.model.EntryAudit;
import com.dauut.EksiDebeFetcher.utils.ConfigurationParams;
import com.dauut.EksiDebeFetcher.utils.LocalTimeHelper;
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

        logger.info("Debe lists collected. headerList: " + headers.size() + "; urls: " + urls.size() +
                "; idList: " + idList.size());

        buildEntryList(headers, urls, idList, entryList);
        return entryList;
    }

    private void buildEntryList(List<String> headers, List<String> urls, List<Integer> idList,
                                List<Entry> entryList) {
        int size = headers.size();

        for (int i = 0; i < size; i++) {
            String header = headers.get(i);
            String url = urls.get(i);
            int id = idList.get(i);
            entryList.add(new Entry(id, url, header,"Generic"));
        }
        logger.info("Debe built: list size: " + entryList.size());
    }


    @Override
    public Debe buildDebe() {

        LocalTimeHelper timeHelper = new LocalTimeHelper(ConfigurationParams.ISTANBUL_TIME_ZONE);

        LocalDateTime localTime = timeHelper.getZonedLocalDateTimeNow();
        LocalDate debeDate = timeHelper.getZonedLocalDateNow();

        List<Entry> entryList = fetchEntryList();
        List<EntryAudit> debeEntriesAudit = fetchEntryAudits(debeDate, entryList);
        int entryCount = entryList.size();

        logger.info("Debe list built. Debe Date: " + debeDate + "; entry count: " + entryCount + "; ");

        return new Debe(entryList, debeEntriesAudit, debeDate, entryCount, localTime);
    }

    private List<EntryAudit> fetchEntryAudits(LocalDate debeDate, List<Entry> entryList) {
        List<EntryAudit> entryAuditList = new ArrayList<>();

        for (Entry e : entryList) {
            EntryAudit audit = new EntryAudit();
            audit.setDate(debeDate);
            audit.setEntryId(e.getEntryId());
            audit.setHeader(e.getHeader());
            audit.setUrl(e.getUrl());
            audit.setAuthor(e.getAuthor());
            entryAuditList.add(audit);
        }

        return entryAuditList;
    }
}