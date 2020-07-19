package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.model.Entry;

import java.util.List;
import java.util.Map;

public interface EntriesFetchService {

    List<Entry> fetchEntryList();
    Map<Integer, String> getIdUrlMap();
}
