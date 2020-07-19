package com.dauut.EksiDebeFetcher.dao.htmlfetcher;

import com.dauut.EksiDebeFetcher.utils.ConfigurationParams;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HTMLFetcherImp implements HtmlFetcher {

    private static final Logger logger = LogManager.getLogger(HTMLFetcherImp.class);
    private static Document htmlPageDoc;

    public void createTodayHtmlPageDoc() {
        Document doc = null;

        try {
            doc = Jsoup.connect(ConfigurationParams.DEBE_BASE_URL).get();
        } catch (IOException e) {
            logger.error("ERROR while fetching " + ConfigurationParams.DEBE_BASE_URL + "!!!");
            e.printStackTrace();
        }
        htmlPageDoc = doc;
    }

    public Elements getEntriesListSection() {
        return htmlPageDoc.getElementsByClass(ConfigurationParams.TOPIC_LIST_HEADER);
    }

    public Elements getEntriesHeaders() {
        Elements section = getEntriesListSection();
        return section.get(ConfigurationParams.FIRST).getElementsByClass(ConfigurationParams.CAPTION_HEADER);
    }

    public Elements getEntryLinks() {
        Elements section = getEntriesListSection();
        return section.get(ConfigurationParams.FIRST).select("a");
    }

    ////FUTURE USE
    public Document getRequestedPageDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

}
