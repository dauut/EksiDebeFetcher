package com.dauut.EksiDebeFetcher.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Entry {
    private int entryId;
    @NotNull
    private String url;
    private String creationDate;
    private String editDate;
    private int favCount;
    private Author author;
    @NotNull
    private String header;

    public Entry(int entryId, @NotNull String url, String creationDate, String editDate, int favCount,
                 Author author, @NotNull String header) {
        this.entryId = entryId;
        this.url = url;
        this.creationDate = creationDate;
        this.editDate = editDate;
        this.favCount = favCount;
        this.author = author;
        this.header = header;
    }

    public Entry(int entryId, @NotNull String url, @NotNull String header) {
        this.entryId = entryId;
        this.url = url;
        this.header = header;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NotNull String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull Author author) {
        this.author = author;
    }

    public @NotNull String getHeader() {
        return header;
    }

    public void setHeader(@NotNull String header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return entryId == entry.entryId &&
                favCount == entry.favCount &&
                url.equals(entry.url) &&
                Objects.equals(creationDate, entry.creationDate) &&
                Objects.equals(editDate, entry.editDate) &&
                Objects.equals(author, entry.author) &&
                header.equals(entry.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, url, creationDate, editDate, favCount, author, header);
    }
}
