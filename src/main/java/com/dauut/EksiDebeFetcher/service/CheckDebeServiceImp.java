package com.dauut.EksiDebeFetcher.service;


import com.dauut.EksiDebeFetcher.dao.datasource.DebeRepository;
import com.dauut.EksiDebeFetcher.dao.datasource.EntryRepository;
import com.dauut.EksiDebeFetcher.model.DebeAudit;
import com.dauut.EksiDebeFetcher.model.EntryAudit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckDebeServiceImp implements CheckDebeService {
    private static final Logger logger = LogManager.getLogger(CheckDebeServiceImp.class);

    private final EntryRepository entryRepository;
    private final DebeRepository debeRepository;

    public CheckDebeServiceImp(EntryRepository entryRepository, DebeRepository debeRepository, JdbcTemplate jdbcTemplate) {
        this.entryRepository = entryRepository;
        this.debeRepository = debeRepository;
    }

    public int actualEntryCount(LocalDate date) {
        List<DebeAudit> list = debeRepository.findByListDate(date);
        if (list != null && !list.isEmpty())
            return list.get(0).getEntryCount();
        else return -1;
    }

    public int retEntryCount(LocalDate date) {
        List<EntryAudit> list = entryRepository.findByDate(date);
        if (list != null && !list.isEmpty()) return list.size();
        else return -1;
    }

    @Override
    public boolean entryCountsMatched(LocalDate date) {
        int entryCount = retEntryCount(date);
        int actualEntryCount = actualEntryCount(date);
        logger.info("Debe checked at : " + date.toString()
                + "; entry_count = " + entryCount + "; actualCount = " + actualEntryCount);
        return actualEntryCount != -1 && entryCount == actualEntryCount;
    }
}
