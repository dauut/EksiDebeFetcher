package com.dauut.EksiDebeFetcher.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Entry {
    private int entryId;
    @NotNull
    private String url;
    private String date;
    private String author;
    @NotNull
    private String header;

    public Entry(int entryId, @NotNull String url, @NotNull String header, String author) {
        this.entryId = entryId;
        this.url = url;
        this.header = header;
        this.author = author;
        this.date = date;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public @NotNull String getUrl() {
        return url;
    }

    public void setUrl(@NotNull String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(@NotNull String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull String author) {
        this.author = author;
    }

    public @NotNull String getHeader() {
        return header;
    }

    public void setHeader(@NotNull String header) {
        this.header = header;
    }


}
