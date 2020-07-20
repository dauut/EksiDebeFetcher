package com.dauut.EksiDebeFetcher.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Debe {

    @NotNull
    private List<Entry> debeEntries;
    @NotNull
    private List<EntryAudit> debeEntriesAudit;

    @NotNull
    private LocalDate date;
    private int entryCount;

    @NotNull
    private LocalDateTime listCreationTime;

    public Debe(@NotNull List<Entry> debeEntries, @NotNull List<EntryAudit> debeEntriesAudit, @NotNull LocalDate date, int entryCount, @NotNull LocalDateTime creationTime) {
        this.debeEntries = debeEntries;
        this.debeEntriesAudit = debeEntriesAudit;
        this.date = date;
        this.entryCount = entryCount;
        this.listCreationTime = creationTime;
    }

    public @NotNull List<Entry> getDebeEntries() {
        return debeEntries;
    }

    public void setDebeEntries(@NotNull List<Entry> debeEntries) {
        this.debeEntries = debeEntries;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    public @NotNull LocalDateTime getListCreationTime() {
        return listCreationTime;
    }

    public void setListCreationTime(@NotNull LocalDateTime listCreationTime) {
        this.listCreationTime = listCreationTime;
    }

    public @NotNull List<EntryAudit> getDebeEntriesAudit() {
        return debeEntriesAudit;
    }

    public void setDebeEntriesAudit(@NotNull List<EntryAudit> debeEntriesAudit) {
        this.debeEntriesAudit = debeEntriesAudit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debe debe = (Debe) o;
        return entryCount == debe.entryCount &&
                debeEntries.equals(debe.debeEntries) &&
                debeEntriesAudit.equals(debe.debeEntriesAudit) &&
                date.equals(debe.date) &&
                listCreationTime.equals(debe.listCreationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debeEntries, debeEntriesAudit, date, entryCount, listCreationTime);
    }
}
