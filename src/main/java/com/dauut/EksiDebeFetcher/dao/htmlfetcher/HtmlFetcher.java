package com.dauut.EksiDebeFetcher.dao.htmlfetcher;

import org.jsoup.select.Elements;

public interface HtmlFetcher {
    void createTodayHtmlPageDoc();
    Elements getEntriesListSection();
    Elements getEntriesHeaders();
    Elements getEntryLinks();
}
