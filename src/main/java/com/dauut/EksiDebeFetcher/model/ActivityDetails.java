package com.dauut.EksiDebeFetcher.model;

import java.util.List;

public class ActivityDetails {

    private int totalEntryCount;
    private int totalEntryCountInLastMonth;
    private int totalEntryCountInLastWeek;
    private List<Entry> topTenFavoriteEntries;
    private List<Entry> topTenLikedEntries;
    private List<Entry> lastTenFaves;

    public ActivityDetails(int totalEntryCount, int totalEntryCountInLastMonth, int totalEntryCountInLastWeek,
                           List<Entry> topTenFavoriteEntries, List<Entry> topTenLikedEntries,
                           List<Entry> lastTenFaves) {
        this.totalEntryCount = totalEntryCount;
        this.totalEntryCountInLastMonth = totalEntryCountInLastMonth;
        this.totalEntryCountInLastWeek = totalEntryCountInLastWeek;
        this.topTenFavoriteEntries = topTenFavoriteEntries;
        this.topTenLikedEntries = topTenLikedEntries;
        this.lastTenFaves = lastTenFaves;
    }

    public int getTotalEntryCount() {
        return totalEntryCount;
    }

    public void setTotalEntryCount(int totalEntryCount) {
        this.totalEntryCount = totalEntryCount;
    }

    public int getTotalEntryCountInLastMonth() {
        return totalEntryCountInLastMonth;
    }

    public void setTotalEntryCountInLastMonth(int totalEntryCountInLastMonth) {
        this.totalEntryCountInLastMonth = totalEntryCountInLastMonth;
    }

    public int getTotalEntryCountInLastWeek() {
        return totalEntryCountInLastWeek;
    }

    public void setTotalEntryCountInLastWeek(int totalEntryCountInLastWeek) {
        this.totalEntryCountInLastWeek = totalEntryCountInLastWeek;
    }

    public List<Entry> getTopTenFavoriteEntries() {
        return topTenFavoriteEntries;
    }

    public void setTopTenFavoriteEntries(List<Entry> topTenFavoriteEntries) {
        this.topTenFavoriteEntries = topTenFavoriteEntries;
    }

    public List<Entry> getTopTenLikedEntries() {
        return topTenLikedEntries;
    }

    public void setTopTenLikedEntries(List<Entry> topTenLikedEntries) {
        this.topTenLikedEntries = topTenLikedEntries;
    }

    public List<Entry> getLastTenFaves() {
        return lastTenFaves;
    }

    public void setLastTenFaves(List<Entry> lastTenFaves) {
        this.lastTenFaves = lastTenFaves;
    }
}