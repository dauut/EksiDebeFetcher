package com.dauut.EksiDebeFetcher.service.htmlservices;

import com.dauut.EksiDebeFetcher.utils.ConfigurationParams;
import org.apache.logging.log4j.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTMLFetcher {

    private static final Logger logger = Logger.getLogger(String.valueOf(HTMLFetcher.class));

    public Document getDebePageDocument() {
        Document doc = null;

        try {
            doc = Jsoup.connect(ConfigurationParams.DEBE_BASE_URL).get();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "ERROR while fetching " + ConfigurationParams.DEBE_BASE_URL + "!!!");
            e.printStackTrace();
        }
        return doc;
    }

    public Elements getEntriesListSection(Document todayHtml) {
        return todayHtml.getElementsByClass(ConfigurationParams.TOPIC_LIST_HEADER);
    }

    public Elements getEntriesHeaders(Document todayHtml) {
        Elements section = getEntriesListSection(todayHtml);
        return section.get(ConfigurationParams.FIRST).getElementsByClass(ConfigurationParams.CAPTION_HEADER);
    }

    public Elements getEntryLinks(Document todayHtml) {
        Elements section = getEntriesListSection(todayHtml);
        return section.get(ConfigurationParams.FIRST).select("a");
    }

    ////FUTURE USE
    public Document getRequestedPageDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

}