package com.dauut.EksiDebeFetcher.dao;

import com.dauut.EksiDebeFetcher.dao.datasource.DebeRepository;
import com.dauut.EksiDebeFetcher.dao.datasource.EntryRepository;
import com.dauut.EksiDebeFetcher.model.Debe;
import com.dauut.EksiDebeFetcher.model.DebeAudit;
import com.dauut.EksiDebeFetcher.utils.ConfigurationParams;
import com.dauut.EksiDebeFetcher.utils.LocalTimeHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EntryDatabaseConnectionService {

    private static final Logger logger = LogManager.getLogger(EntryDatabaseConnectionService.class);

    private final EntryRepository entryRepository;
    private final DebeRepository debeRepository;

    public EntryDatabaseConnectionService(EntryRepository entryRepository, DebeRepository debeRepository) {
        this.entryRepository = entryRepository;
        this.debeRepository = debeRepository;
    }

    public void initRecordQueries(Debe debe) throws InterruptedException {

        Thread createDebeListThread = new Thread(() -> createDebeListEntity(debe));
        Thread saveAllEntriesThread = new Thread(() -> saveAllEntries(debe));

        createDebeListThread.start();
        logger.info("Debe list query started..");
        createDebeListThread.join();
        logger.info("Debe list query finished..");
        Thread.sleep(10);
        saveAllEntriesThread.start();
        logger.info("Save all entries, started...");
        saveAllEntriesThread.join();
        logger.info("Save all entries, completed...");
    }

    private void saveAllEntries(Debe debe) {
        entryRepository.saveAll(debe.getDebeEntriesAudit());
    }

    private void createDebeListEntity(Debe debe) {
        LocalTimeHelper localTimeHelper = new LocalTimeHelper(ConfigurationParams.ISTANBUL_TIME_ZONE);
        DebeAudit debeAudit = new DebeAudit();
        debeAudit.setListDate(debe.getDate());
        debeAudit.setEntryCount(debe.getEntryCount());
        debeAudit.setListCreationTime(localTimeHelper.getZonedLocalDateTimeNow());
        debeRepository.save(debeAudit);
    }
}
