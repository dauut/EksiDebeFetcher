package com.dauut.EksiDebeFetcher.service.htmlservices;


import com.dauut.EksiDebeFetcher.utils.ConfigurationParams;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HtmlParser {

    @NotNull
    HTMLFetcher htmlFetcher;

    @NotNull
    Document htmlPageDoc;

    public HtmlParser(@NotNull HTMLFetcher htmlFetcher, @NotNull Document htmlPageDoc) {
        this.htmlFetcher = htmlFetcher;
        this.htmlPageDoc = htmlPageDoc;
    }
    private static final Logger logger = Logger.getLogger(String.valueOf(HtmlParser.class));

    public List<String> collectHeaders() throws IOException {
        final List<String> entryHeaders = new ArrayList<>();
        Elements headerElements = htmlFetcher.getEntriesHeaders(htmlPageDoc);
        fillStringList(headerElements, entryHeaders, false);
        return entryHeaders;
    }

    public List<String> collectEntryUrls() throws IOException {
        final List<String> entryUrls = new ArrayList<>();
        Elements urlElements = htmlFetcher.getEntryLinks(htmlPageDoc);
        fillStringList(urlElements, entryUrls, true);
        return entryUrls;
    }

    public List<Integer> collectEntryIds() throws IOException {
        final List<Integer> idList = new ArrayList<>();
        Elements linksElement = htmlFetcher.getEntryLinks(htmlPageDoc);
        fillIntegerList(linksElement, idList);
        return idList;
    }

    public Map<Integer, String> mapIdAndStringList(@NotNull List<String> strList,@NotNull List<Integer> idList){

        Map<Integer, String> idMap = new HashMap<>();

        if (strList.size() != idList.size()){
            logger.log(Level.SEVERE,"Can not mapping: Id list and entry list are not in same size!");
            return new HashMap<>();
        }

        for (int i = 0; i < strList.size(); i++){
            idMap.put(idList.get(i), strList.get(i));
        }
        logger.log(Level.INFO, ("ID:LIST Map successfully created!"));
        return idMap;
    }

    private void fillStringList(Elements elements, List<String> list, boolean isUrl) {
        for (org.jsoup.nodes.Element element : elements) {
            if (isUrl) {
                list.add(ConfigurationParams.BASE_URL + element.select("a").attr("href"));
            } else {
                list.add(element.text());
            }
        }

        logger.log(Level.INFO, (isUrl ? "Url list successfully collected!" : "Header list successfully collected!"));
    }

    private void fillIntegerList(Elements linksElement, List<Integer> list) {
        for (org.jsoup.nodes.Element element : linksElement) {
            String[] urlParse = element.select("a").attr("href").split("/");
            list.add(Integer.parseInt(urlParse[2]));
        }

        logger.log(Level.INFO, "Id list collected!");
    }


}
