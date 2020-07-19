package com.dauut.EksiDebeFetcher.dao.htmlfetcher;


import com.dauut.EksiDebeFetcher.utils.ConfigurationParams;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HtmlParseServiceImp implements HtmlParseService{

    private static final Logger logger = LogManager.getLogger(HtmlParseServiceImp.class);

    @NotNull private final HtmlFetcher htmlFetcher;

    public HtmlParseServiceImp(@NotNull HtmlFetcher htmlFetcher) {
        this.htmlFetcher = htmlFetcher;
    }

    public List<String> collectHeaders() {
        final List<String> entryHeaders = new ArrayList<>();
        Elements headerElements = htmlFetcher.getEntriesHeaders();
        fillStringList(headerElements, entryHeaders, false);
        return entryHeaders;
    }

    public List<String> collectEntryUrls() {
        final List<String> entryUrls = new ArrayList<>();
        Elements urlElements = htmlFetcher.getEntryLinks();
        fillStringList(urlElements, entryUrls, true);
        return entryUrls;
    }

    public List<Integer> collectEntryIds() {
        final List<Integer> idList = new ArrayList<>();
        Elements linksElement = htmlFetcher.getEntryLinks();
        fillIntegerList(linksElement, idList);
        return idList;
    }

    public Map<Integer, String> mapIdAndStringList(@NotNull List<String> strList,@NotNull List<Integer> idList){

        Map<Integer, String> idMap = new HashMap<>();

        if (strList.size() != idList.size()){
            logger.error("Can not mapping: Id list and entry list are not in same size!");

            return new HashMap<>();
        }

        for (int i = 0; i < strList.size(); i++){
            idMap.put(idList.get(i), strList.get(i));
        }
        logger.info( "ID:LIST Map successfully created!");
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

        logger.info(isUrl ? "Url list successfully collected!" : "Header list successfully collected!");
    }

    private void fillIntegerList(Elements linksElement, List<Integer> list) {
        for (org.jsoup.nodes.Element element : linksElement) {
            String[] urlParse = element.select("a").attr("href").split("/");
            list.add(Integer.parseInt(urlParse[2]));
        }

        logger.info("Id list collected!");
    }


}
