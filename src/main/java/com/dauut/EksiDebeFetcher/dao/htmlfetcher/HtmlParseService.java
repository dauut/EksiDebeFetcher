package com.dauut.EksiDebeFetcher.dao.htmlfetcher;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface HtmlParseService {
    List<String> collectHeaders();
    List<String> collectEntryUrls();
    List<Integer> collectEntryIds();
    List<String> collectAuthors();
    Map<Integer, String> mapIdAndStringList(@NotNull List<String> strList, @NotNull List<Integer> idList);
}
