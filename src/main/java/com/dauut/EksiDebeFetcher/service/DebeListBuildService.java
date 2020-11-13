package com.dauut.EksiDebeFetcher.service;

import com.dauut.EksiDebeFetcher.model.Debe;
import com.dauut.EksiDebeFetcher.model.Entry;

import java.util.List;
import java.util.Map;

public interface DebeListBuildService {
    List<Entry> fetchEntryList();
    Debe buildDebe();
}
